package simulador;

import config.FilaConfig;
import evento.Evento;
import evento.TipoEvento;
import geradorNumeros.GeradorNumeros;

import javax.naming.LimitExceededException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Fila {

  private final DecimalFormat decimalFormat = new DecimalFormat(" 0.####");

  private String id;

  private FilaConfig config;

  private int tempoAtual = 0;

  private int estadoAtual = 0;

  private int perda = 0;

  private ArrayList<Double> tempos = new ArrayList<>();

  private Escalonador escalonador;

  private ArrayList<ConexaoFila> conexoes = new ArrayList<>();

  public Fila(String id, FilaConfig config, Escalonador escalonador) {
    this.escalonador = escalonador;

    if (config.getCapacidade() != null && config.getCapacidade() <= 0) {
      throw new IllegalArgumentException("A capacidade deve ser maior que zero");
    }

    if (config.getServidores() <= 0) {
      throw new IllegalArgumentException("O número de servidores deve ser maior que zero");
    }

    this.id = id;
    this.config = config;

    if (config.getCapacidade() != null) {
      this.tempos = new ArrayList<Double>(config.getCapacidade() + 1);

      for (int i = 0; i < config.getCapacidade() + 1; i++) {
        this.tempos.add(0.0);
      }
    }
  }

  public void addConexaoFila(Fila fila, double probabilidade) {
    conexoes.add(new ConexaoFila(fila, probabilidade));
  }

  public boolean estaVazia() {
    return estadoAtual == 0;
  }

  public boolean estaCheia() {
    return config.getCapacidade() != null && estadoAtual >= config.getCapacidade();
  }

  private void acumulaTempo(double tempo) {
    if (tempos.size() >= estadoAtual) {
      double tempoIndexAtual = tempos.get(estadoAtual);
      tempos.set(estadoAtual, tempoIndexAtual + tempo);
    }
  }

  public void chegada(Evento ev) {
    acumulaTempo(ev.getTempo());
    try {
      if (this.config.getCapacidade() == null || estadoAtual < this.config.getCapacidade()) {
        estadoAtual++;

        if (estadoAtual <= this.config.getServidores()) {
          double t = GeradorNumeros.nextRandomNormalized(config.getMinServico(), config.getMaxServico());
          if (this.conexoes.isEmpty()) {
            // Se for a última da simulação:
            escalonador.addEvento(new Evento(TipoEvento.SAIDA, t, this), tempoAtual + t);
          } else {
            // Por enquanto só vou pegar uma conexão
            escalonador.addEvento(new Evento(TipoEvento.PASSAGEM, t, this.conexoes.get(0).getDestino()), tempoAtual + t);
          }
        }
      } else {
        this.perda++;
      }
      double t = GeradorNumeros.nextRandomNormalized(config.getMinChegada(), config.getMaxChegada());
      escalonador.addEvento(new Evento(TipoEvento.CHEGADA, t, this), tempoAtual + t);
    } catch (LimitExceededException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public void saida(Evento ev) {
    acumulaTempo(ev.getTempo());
    estadoAtual--;

    try {
      if (this.estadoAtual >= this.config.getServidores()) {
        double t = GeradorNumeros.nextRandomNormalized(config.getMinServico(), config.getMaxServico());
        escalonador.addEvento(new Evento(TipoEvento.SAIDA, t, this), tempoAtual + t);
      }
    } catch (LimitExceededException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public void passagem(Evento ev) {
    acumulaTempo(ev.getTempo());
//  Fila1.out()
//  Se fila1.status >= Fila1.Servers() {
//    Escalonador.Add(TempoGlobal + Passagem(5,6)) // tempo de saída da f1
//  }
//  se fila2.status() < Fila2.Capacidade() {
//    Fila2++
//    se Fila2.status() <= Fila2.Servers() {
//      Escalonador.Add(TempoGlobal + Saida(2,4)) // tempo de saída da f2
//    }
//  } else {
//    Fila2.Perda++
//  }
  }

  public double getTempoAcumulado() {
    return tempos.stream().reduce(0.0, Double::sum);
  }

  @Override
  public String toString() {
    return "simulador.Fila '" + id + "' {" + "\n\tconfig=" + config + ",\n\testadoAtual=" + estadoAtual + ",\n\tperda=" + perda + "\n}";
  }

  public String temposToString() {
    StringBuilder sb = new StringBuilder();
    sb.append("=".repeat(50));
    sb.append("\nClientes\t Tempo\t\t\t\t Probabilidade\n");

    double acc = getTempoAcumulado();

    for (int i = 0; i < tempos.size(); i++) {
      sb.append(i);
      sb.append("\t\t\t");
      sb.append(decimalFormat.format(tempos.get(i)));
      sb.append("ms\t\t");
      sb.append(decimalFormat.format(tempos.get(i) / acc * 100));
      sb.append(" %\n");
    }

    sb.append("=".repeat(50));
    sb.append("\nTempo acumulado: ");
    sb.append(decimalFormat.format(getTempoAcumulado()));
    sb.append("ms\n");
    sb.append("=".repeat(50));
    sb.append("\nPerda: ");
    sb.append(perda);
    sb.append("\n");
    sb.append("=".repeat(50));
    return sb.toString();
  }
}
