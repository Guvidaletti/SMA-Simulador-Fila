package simulador;

public class ConexaoFila implements Comparable<ConexaoFila> {
  private double probabilidade;
  private Fila destino;

  public ConexaoFila(Fila destino, double probabilidade) {
    this.probabilidade = probabilidade;
    this.destino = destino;
  }

  public double getProbabilidade() {
    return probabilidade;
  }

  public Fila getDestino() {
    return destino;
  }

  @Override
  public int compareTo(ConexaoFila other) {
    return Double.compare(this.probabilidade, other.probabilidade);
  }
}
