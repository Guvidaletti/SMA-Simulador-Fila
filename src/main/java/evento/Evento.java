package evento;

public class Evento {
  /**
   * Tipo do evento
   */
  private TipoEvento tipo;

  /**
   * número randômico utilizado
   */
  private double rand;

  public Evento(TipoEvento tipo) {
    this.tipo = tipo;
  }

  public void setRand(double rand) {
    this.rand = rand;
  }

  public TipoEvento getTipo() {
    return tipo;
  }

  public double getRand() {
    return rand;
  }
}
