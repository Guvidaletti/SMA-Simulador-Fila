import evento.Evento;

import java.util.TreeMap;

public class Escalonador {
  private TreeMap<Double, Evento> eventosNaoOcorridos = new TreeMap<>();
  private TreeMap<Double, Evento> eventosJaOcorridos = new TreeMap<>();

  public void addEvento(Evento evento, double tempo) {
    eventosNaoOcorridos.put(tempo, evento);
  }

  public Evento getNovoEvento() {
    double tempo = eventosNaoOcorridos.firstKey();
    Evento evento = eventosNaoOcorridos.get(tempo);
    eventosNaoOcorridos.remove(tempo);
    eventosJaOcorridos.put(tempo, evento);
    return evento;
  }

}
