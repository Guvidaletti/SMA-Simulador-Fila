import evento.Evento;

import java.util.HashMap;
import java.util.TreeMap;

public class Escalonador {
  private TreeMap<Double, Evento> eventosNaoOcorridos = new TreeMap<>();
  private HashMap<Double, Evento> eventosJaOcorridos = new HashMap<>();

  public void addEvento(Evento evento, double tempo) {
    eventosNaoOcorridos.put(tempo, evento);
  }

  public boolean hasNext() {
    return !eventosNaoOcorridos.isEmpty();
  }

  public Evento getProximoEvento() {
    double tempo = eventosNaoOcorridos.firstKey();
    Evento evento = eventosNaoOcorridos.get(tempo);
    eventosNaoOcorridos.remove(tempo);
    eventosJaOcorridos.put(tempo, evento);
    return evento;
  }

  @Override
  public String toString() {
    return "Escalonador{" +
        "\n\teventosNaoOcorridos=" + eventosNaoOcorridos +
        ",\n\teventosJaOcorridos=" + eventosJaOcorridos +
        "\n}";
  }
}
