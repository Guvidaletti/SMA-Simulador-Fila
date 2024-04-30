package simulador;

public class ConexaoFila {
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
}
