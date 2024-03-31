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

  public Evento(TipoEvento tipo, double rand) {
    this.tipo = tipo;
    this.rand = rand;
  }

  public TipoEvento getTipo() {
    return tipo;
  }

  public double getRand() {
    return rand;
  }
}
