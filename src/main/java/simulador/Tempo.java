package simulador;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tempo {
  private static final DecimalFormat decimalFormat = new DecimalFormat(" 0.####");

  private static double tempoAcumulado = 0d;

  private static final Map<String, ArrayList<Double>> tempoDasFilas = new HashMap<>();

  public static String getTempoAcumuladoToString() {
    return decimalFormat.format(tempoAcumulado);
  }

  public static double acumulaTempo(String idFila, double tempo, ArrayList<Fila> filas) {
    double novoTempoAcumulado = tempoAcumulado;

    for (Fila f : filas) {
      ArrayList<Double> filaTempos = tempoDasFilas.get(f.getId());
      int estadoAtual = f.getEstadoAtual();

      if (filaTempos == null) {
        filaTempos = new ArrayList<>();
        filaTempos.add(0.0);
      }

      if (estadoAtual >= filaTempos.size()) {
        for (int i = filaTempos.size(); i <= estadoAtual; i++) {
          filaTempos.add(0d);
        }
      }
      double tempoAtualEstadoFila = filaTempos.get(estadoAtual);
      double duracao = tempo - tempoAcumulado;

      filaTempos.set(estadoAtual, tempoAtualEstadoFila + duracao);
      tempoDasFilas.put(f.getId(), filaTempos);

      if (f.getId().equals(idFila)) {
        novoTempoAcumulado += duracao;
      }
    }

    tempoAcumulado = novoTempoAcumulado;
    return tempoAcumulado;
  }

  public static String temposToString(String idFila) {
    if (!tempoDasFilas.containsKey(idFila)) {
      return "Fila não encontrada";
    }

    StringBuilder sb = new StringBuilder();
    sb.append("=".repeat(50));
    sb.append("\nClientes\t Tempo\t\t\t\t Probabilidade\n");
    ArrayList<Double> filaTempos = tempoDasFilas.get(idFila);

    for (int i = 0; i < filaTempos.size(); i++) {
      sb.append(i);
      sb.append("\t\t\t");
      sb.append(decimalFormat.format(filaTempos.get(i)));
      sb.append("ms\t\t");
      if (tempoAcumulado != 0) {
        sb.append(decimalFormat.format(filaTempos.get(i) / tempoAcumulado * 100));
        sb.append(" %\n");
      } else {
        sb.append("0 %\n");
      }
    }

    return sb.toString();
  }

}
