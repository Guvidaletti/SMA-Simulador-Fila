// A: distribuição da chegada de clientes
// B: distribuição do tempo de atendimento
// C: número de servidores

// K: capacidade do sistema
// N: tamanho da população
// D: política de atendimento

// UA1 -> temos que entregar o
// G/G/1/5, chegadas entre 2...5, atendimento entre 3...5
// G/G/2/5, chegadas entre 2...5, atendimento entre 3…5

import config.ConfigLoader;
import config.FilaConfig;
import config.SimuladorConfig;

public class Main {

  public static void main(String[] args) {
//    Configuração do simulador
    System.out.println("Carregando configurações...");
    SimuladorConfig config = ConfigLoader.load();
    if (config == null) {
      throw new RuntimeException("Erro ao carregar configurações");
    }

    GeradorNumeros.setSeed(config.getSementes()[0]);

    System.out.println("Configurações carregadas.");
//    Fim da configuração
    config.getFilas().forEach((id, value) -> {
      Fila f = new Fila(id, value);
      System.out.println(f);
      System.out.println(f.temposToString());
    });


//    long count = config.getNumeros();
//    while (count > 0) {
//      count--;
//      System.out.println(GeradorNumeros.nextRandom());
//    }

//    Fila fila = new Fila(1);
//    System.out.println(fila.estaVazia());
//    System.out.println(fila.estaCheia());
//
//    int count = 5;
//
//    while (count > 0) {
//      count--;
//      System.out.println(GeradorNumeros.nextRandom());
//    }
//
//    long TempoFinal = System.currentTimeMillis();
//    System.out.println("Tempo de execução: " + (TempoFinal - TempoInicial) + "ms");
  }
}