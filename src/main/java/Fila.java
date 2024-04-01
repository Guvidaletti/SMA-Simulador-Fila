import config.FilaConfig;
import evento.Evento;
import geradorNumeros.GeradorNumeros;

import java.util.ArrayList;

public class Fila {

  private String id;

  private FilaConfig config;

  private int tempoAtual = 0;

  private int estadoAtual = 0;

  private int perda = 0;

  private ArrayList<Double> tempos = new ArrayList<>();

  private Escalonador escalonador = new Escalonador();

  public Fila(String id, FilaConfig config) {
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

  public boolean estaVazia() {
    return estadoAtual == 0;
  }

  public boolean estaCheia() {
    return config.getCapacidade() != null && estadoAtual >= config.getCapacidade();
  }

  public void chegada(Evento ev) {
//    Acumula tempo (ev)
    if (this.config.getCapacidade() == null || this.estadoAtual < this.config.getCapacidade()) {
      estadoAtual++;

      if (this.estadoAtual < this.config.getServidores()) {
        escalonador.addEvento(ev, tempoAtual + GeradorNumeros.nextRandomNormalized(config.getMinServico(), config.getMaxServico()));
      }
    } else {
      this.perda++;
    }
    escalonador.addEvento(ev, tempoAtual + GeradorNumeros.nextRandomNormalized(config.getMinChegada(), config.getMaxChegada()));
  }

  public void saida(Evento ev) {
//    Acumula tempo (ev)
    estadoAtual--;

    if (this.estadoAtual > this.config.getServidores()) {
      escalonador.addEvento(ev, tempoAtual + GeradorNumeros.nextRandomNormalized(config.getMinServico(), config.getMaxServico()));
    }
  }

  public double getTempoAcumulado() {
//    return tempos.stream().mapToDouble(Double::doubleValue).sum()
    return tempos.stream().reduce(0.0, Double::sum);
  }

  @Override
  public String toString() {
    return "Fila '" + id + "' {" + "\n\tconfig=" + config + ",\n\testadoAtual=" + estadoAtual + ",\n\tperda=" + perda + "\n}";
  }

  public String temposToString() {
    StringBuilder sb = new StringBuilder();
    sb.append("=".repeat(30));
    sb.append("\nClientes\tTempo\n");

    for (int i = 0; i < tempos.size(); i++) {
      sb.append(i);
      sb.append("\t\t\t");
      sb.append(tempos.get(i));
      sb.append("ms\n");
    }

    sb.append("=".repeat(30));
    sb.append("\nTempo acumulado: ");
    sb.append(getTempoAcumulado());
    sb.append("ms\n");
    sb.append("=".repeat(30));
    return sb.toString();
  }
}
