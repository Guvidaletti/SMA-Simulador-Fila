import config.FilaConfig;
import evento.Evento;

import java.util.Arrays;

public class Fila {

  private String id;

  private FilaConfig config;

  private int estadoAtual = 0;
  private int perda = 0;

  private double[] tempos;


  public Fila(String id, FilaConfig config) {
    if (config.getCapacidade() <= 0) {
      throw new IllegalArgumentException("A capacidade deve ser maior que zero");
    }
    if (config.getServidores() <= 0) {
      throw new IllegalArgumentException("O número de servidores deve ser maior que zero");
    }

    this.id = id;
    this.config = config;

    this.tempos = new double[config.getCapacidade() + 1];
  }

  public boolean estaVazia() {
    return estadoAtual == 0;
  }

  public boolean estaCheia() {
    return config.getCapacidade() != null && estadoAtual == config.getCapacidade();
  }

  public void chegada(Evento ev) {
//    Acumula tempo (ev)
    if (this.config.getCapacidade() == null || this.estadoAtual < this.config.getCapacidade()) {
//    Fila.in()
      if (this.estadoAtual < this.config.getServidores()) {
//      Escalonador.Add(TG + SAIDA(5,6))
      }
    } else {
      this.perda++;
    }
//    Escalonador.Add(TG + CHEGADA(1,3))
  }

  public void saida(Evento ev) {
//    Acumula tempo (ev)
//    FIla.out()
    if (this.estadoAtual > this.config.getServidores()) {
//      Escalonador.Add(TG + SAIDA(5,6))
    }
  }

  @Override
  public String toString() {
    return "Fila '" + id + "' {" +
        ",\n\tconfig=" + config +
        ",\n\testadoAtual=" + estadoAtual +
        ",\n\tperda=" + perda +
        "\n}";
  }

  public String temposToString() {
    StringBuilder sb = new StringBuilder();
    sb.append("=".repeat(30));
    sb.append("\nClientes\tTempo\n");
    for (int i = 0; i < tempos.length; i++) {
      sb.append(i);
      sb.append("\t\t\t");
      sb.append(tempos[i]);
      sb.append("ms\n");
    }
    sb.append("=".repeat(30));
    sb.append("\nTempo acumulado: ");
    sb.append(Arrays.stream(tempos).sum());
    sb.append("ms\n");
    sb.append("=".repeat(30));
    return sb.toString();
  }
}
