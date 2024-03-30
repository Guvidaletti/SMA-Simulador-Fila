public class Fila {
  //    private Semaphore semaphore;
  private Integer capacidade;
  private int estadoAtual = 0;
  private int perda = 0;

  public Fila() {
  }

  public Fila(int capacidade) {
    if (capacidade <= 0) {
      throw new IllegalArgumentException("A capacidade deve ser maior que zero");
    }
    this.capacidade = capacidade;
  }

  public boolean estaVazia() {
    return estadoAtual == 0;
  }

  public boolean estaCheia() {
    return capacidade != null && estadoAtual == capacidade;
  }

  public void chegada() {}

  public void saida() {}
}
