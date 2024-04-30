package evento;

import simulador.Fila;

public class Evento {
  /**
   * Tipo do evento
   */
  private TipoEvento tipo;

  /**
   * tempo do evento
   */
  private double tempo;

  private Fila fila;

  public Evento(TipoEvento tipo, double tempo, Fila fila) {
    this.tipo = tipo;
    this.tempo = tempo;
    this.fila = fila;
  }

  public Fila getFila() {
    return fila;
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
