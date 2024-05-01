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
import config.RedeConfig;
import config.SimuladorConfig;
import evento.Evento;
import evento.Operacoes;
import evento.TipoEvento;
import geradorNumeros.GeradorNumeros;
import simulador.Escalonador;
import simulador.Fila;
import simulador.Tempo;

import java.util.HashMap;

public class Main {

  public static void main(String[] args) {
    Escalonador escalonador = new Escalonador();
    System.out.println("=".repeat(50));

    // Configuração do simulador
    System.out.println("Carregando configurações...");
    SimuladorConfig config = ConfigLoader.load(args[0]);

    if (config == null) {
      throw new RuntimeException("Erro ao carregar configurações.");
    }

    GeradorNumeros.setSeed(config.getSementes()[0]);
    GeradorNumeros.setLimite(config.getNumeros());
    System.out.println("Configurações carregadas.");
    System.out.println("=".repeat(50));

    HashMap<String, Fila> filas = new HashMap<>();

    // Instanciando todas as filas
    config.getFilas().forEach((id, filaConfig) -> {
      filas.put(id, new Fila(id, filaConfig));
    });

    // Configurando a rede:
    if (config.getRede() != null) {
      for (RedeConfig c : config.getRede()) {
        filas.get(c.getOrigem()).addConexaoFila(filas.get(c.getDestino()), c.getProbabilidade());
      }
    }

//    Configurando todas as chegadas:
    config.getChegadas().forEach((filaId, tempoChegada) -> {
      Fila f = filas.get(filaId);
      if (f != null) {
        escalonador.addEvento(new Evento(TipoEvento.CHEGADA, tempoChegada, null, f), tempoChegada);
      } else {
        System.err.println("Fila com ID '" + filaId + "' não encontrada.");
      }
    });

//    Iniciando a simulação:
    while (escalonador.hasNext()) {
      Evento prox = escalonador.getProximoEvento();


      switch (prox.getTipo()) {
        case CHEGADA -> Operacoes.chegada(prox, escalonador);
        case SAIDA -> Operacoes.saida(prox, escalonador);
        case PASSAGEM -> Operacoes.passagem(prox, escalonador);
      }
    }

    System.out.println("Resultados da simulação:");

    filas.forEach((id, fila) -> {
      System.out.println("=".repeat(50));
      System.out.println("Fila: " + id + " (G/G/" + fila.getConfig().getServidores() + "/" + fila.getConfig().getCapacidade() + ")");
      System.out.println("Chegadas: " + fila.getConfig().getMinChegada() + "..." + fila.getConfig().getMaxChegada());
      System.out.println("Atendimento: " + fila.getConfig().getMinServico() + "..." + fila.getConfig().getMaxServico());
      System.out.print(Tempo.temposToString(id));
      System.out.println("=".repeat(50));
      System.out.println("Perda: " + fila.getPerda() + " clientes");
    });

    System.out.println("=".repeat(50));
    System.out.println("Tempo total de simulação: " + Tempo.getTempoAcumuladoToString() + "ms");
    System.out.println("=".repeat(50));
  }
}