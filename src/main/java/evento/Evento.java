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

  private String idFila;

  public Evento(TipoEvento tipo) {
    this.tipo = tipo;
  }

  public Evento(TipoEvento tipo, String idFila, double tempo) {
    this.tipo = tipo;
    this.tempo = tempo;
    this.idFila = idFila;
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

  public String getIdFila() {
    return idFila;
  }
}
