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
import config.SimuladorConfig;
import geradorNumeros.GeradorNumeros;

public class Main {

  public static void main(String[] args) {
    System.out.println("=".repeat(30));
//    Configuração do simulador
    System.out.println("Carregando configurações...");
    SimuladorConfig config = ConfigLoader.load();

    if (config == null) {
      throw new RuntimeException("Erro ao carregar configurações.");
    }

    GeradorNumeros.setSeed(config.getSementes()[0]);

    System.out.println("Configurações carregadas.");
    System.out.println("=".repeat(30));
//    Fim da configuração

    System.out.println("=".repeat(30));

//      Para cada fila:
    config.getFilas().forEach((id, value) -> {
      Fila f = new Fila(id, value);
      long nums = config.getNumeros();
      while (nums > 0) {
        nums--;
        System.out.println(GeradorNumeros.nextRandom());
      }

      System.out.println("Iniciando fila: " + id);
      System.out.println(f.temposToString());
      System.out.println("=".repeat(30));
    });
  }
}