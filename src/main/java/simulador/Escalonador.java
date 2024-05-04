package simulador;

import evento.Evento;

import java.util.ArrayList;
import java.util.TreeMap;

public class Escalonador {
  private static TreeMap<Double, Evento> eventosNaoOcorridos = new TreeMap<>();
  private static ArrayList<Evento> eventosJaOcorridos = new ArrayList<>();

  public static void addEvento(Evento evento, double tempo) {
    eventosNaoOcorridos.put(tempo, evento);
  }

  public static boolean hasNext() {
    return !eventosNaoOcorridos.isEmpty();
  }

  public static Evento getProximoEvento() {
    if (!hasNext()) {
      return null;
    }
    double tempo = eventosNaoOcorridos.firstKey();
    Evento evento = eventosNaoOcorridos.get(tempo);
    eventosNaoOcorridos.remove(tempo);
    eventosJaOcorridos.add(evento);
    return evento;
  }

  public static String toStaticString() {
    return "simulador.Escalonador{" + "\n\teventosNaoOcorridos=" + eventosNaoOcorridos + ",\n\teventosJaOcorridos=" + eventosJaOcorridos + "\n}";
  }
}
