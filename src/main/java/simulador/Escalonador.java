package simulador;

import evento.Evento;

import java.util.ArrayList;
import java.util.TreeMap;

public class Escalonador {
  private TreeMap<Double, Evento> eventosNaoOcorridos = new TreeMap<>();
  private ArrayList<Evento> eventosJaOcorridos = new ArrayList<>();

  public void addEvento(Evento evento, double tempo) {
    eventosNaoOcorridos.put(tempo, evento);
  }

  public boolean hasNext() {
    return !eventosNaoOcorridos.isEmpty();
  }

  public Evento getProximoEvento() {
    if (!hasNext()) {
      return null;
    }
    double tempo = eventosNaoOcorridos.firstKey();
    Evento evento = eventosNaoOcorridos.get(tempo);
    eventosNaoOcorridos.remove(tempo);
    eventosJaOcorridos.add(evento);
    return evento;
  }

  @Override
  public String toString() {
    return "simulador.Escalonador{" +
        "\n\teventosNaoOcorridos=" + eventosNaoOcorridos +
        ",\n\teventosJaOcorridos=" + eventosJaOcorridos +
        "\n}";
  }
}
