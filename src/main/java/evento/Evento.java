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

  private Fila origem;

  private Fila destino;

  public Evento(TipoEvento tipo, double tempo, Fila origem, Fila destino) {
    this.tipo = tipo;
    this.tempo = tempo;
    this.origem = origem;
    this.destino = destino;
  }

  public Fila getOrigem() {
    return origem;
  }

  public Fila getDestino() {
    return destino;
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
