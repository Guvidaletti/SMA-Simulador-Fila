package evento;

public class Evento {
  /**
   * Tipo do evento
   */
  private TipoEvento tipo;

  /**
   * tempo do evento
   */
  private double tempo;

  public Evento(TipoEvento tipo) {
    this.tipo = tipo;
  }

  public Evento(TipoEvento tipo, double tempo) {
    this.tipo = tipo;
    this.tempo = tempo;
  }

  public void setTempo(double tempo) {
    this.tempo = tempo;
  }

  public TipoEvento getTipo() {
    return tipo;
  }

  public double getTempo() {
    return tempo;
  }
}
