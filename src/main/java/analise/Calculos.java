package analise;

import simulador.Fila;
import simulador.Tempo;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Calculos {
  public static ArrayList<Double> calcularProbabilidades(Fila fila) {
    double total = Tempo.getTempoAcumulado();

    return Tempo.getTempoDasFilas().get(fila.getId()).stream().map(t -> t / total).collect(Collectors.toCollection(ArrayList::new));
  }

  public static double calcularPopulacao(Fila fila) {
    double sum = 0;
    ArrayList<Double> probablidades = calcularProbabilidades(fila);

//  o certo seria calcular com acapacidade, mas é praticamente a mesma informação
    for (int i = 1; i < probablidades.size(); i++) {
      // N = ∑[π(i) * i]
      sum += (probablidades.get(i)) * i;
    }

    return sum;
  }

  public static double calcularTaxaDeAtendimentoPorHora(Fila fila) {
    double min = fila.getConfig().getMinServico();
    double max = fila.getConfig().getMaxServico();

    // taxa de atendimento por minuto: Média entre min e max;
    double average = (min + max) / 2;

    // taxa de atendimento por hora: 60 / taxa de atendimento por minuto
    return 60 / average;
  }

  public static double calcularTaxaDeAtendimento(Fila fila, int i) {
    // µ(i) = min(i,c) * µ
    Integer capacidade = Objects.requireNonNullElse(fila.getConfig().getCapacidade(), Integer.MAX_VALUE);
    return Math.min(i, capacidade) * calcularTaxaDeAtendimentoPorHora(fila);
  }

  public static double calcularVazao(Fila fila) {
    double sum = 0;
    ArrayList<Double> probabilidades = calcularProbabilidades(fila);

    //  o certo seria calcular com a capacidade, mas é praticamente a mesma informação
    for (int i = 1; i < probabilidades.size(); i++) {
      // D = ∑[π(i) * µ(i)]
      sum += probabilidades.get(i) * calcularTaxaDeAtendimento(fila, i);
    }

    return sum;
  }

  public static double calcularUtilizacao(Fila fila) {
    double sum = 0;
    ArrayList<Double> probabilidades = calcularProbabilidades(fila);
    // U = ∑[π(i) * ( min(i, C) / C ) ]
    Integer capacidade = Objects.<Integer>requireNonNullElse(fila.getConfig().getCapacidade(), Integer.MAX_VALUE);
    for (int i = 1; i < probabilidades.size(); i++) {
      sum += probabilidades.get(i) * ((double) Math.min(i, capacidade) / capacidade);
    }

    return sum;
  }

  public static double calcularTempoDeResposta(Fila fila) {
    // W = N / D
    return calcularPopulacao(fila) / calcularVazao(fila);
  }

}
