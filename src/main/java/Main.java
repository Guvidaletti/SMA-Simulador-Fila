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
import evento.TipoEvento;
import geradorNumeros.GeradorNumeros;
import simulador.Escalonador;
import simulador.Fila;

import java.util.HashMap;

public class Main {

  public static void main(String[] args) {
    Escalonador e = new Escalonador();
    System.out.println("=".repeat(30));

    // Configuração do simulador
    System.out.println("Carregando configurações...");
    SimuladorConfig config = ConfigLoader.load();

    if (config == null) {
      throw new RuntimeException("Erro ao carregar configurações.");
    }

    GeradorNumeros.setSeed(config.getSementes()[0]);
    GeradorNumeros.setLimite(config.getNumeros());
    System.out.println("Configurações carregadas.");
    System.out.println("=".repeat(30));
    // Fim da configuração

    HashMap<String, Fila> filas = new HashMap<>();

    // Instanciando todas as filas
    config.getFilas().forEach((id, filaConfig) -> {
      filas.put(id, new Fila(id, filaConfig, e));
    });

    // Configurando a rede:
    for (RedeConfig c : config.getRede()) {
      System.out.println(c.toString());
      filas.get(c.getOrigem()).addConexaoFila(filas.get(c.getDestino()), c.getProbabilidade());
    }

//    Configurando todas as chegadas:
    config.getChegadas().forEach((filaId, tempoChegada) -> {
      Fila f = filas.get(filaId);
      if (f != null) {
        e.addEvento(new Evento(TipoEvento.CHEGADA, filaId, tempoChegada), tempoChegada);
      } else {
        System.err.println("Fila com ID '" + filaId + "' não encontrada.");
      }
    });

//    Iniciando a simulação:
    while (e.hasNext()) {
      Evento prox = e.getProximoEvento();
      Fila f = prox.getFila();

      switch (prox.getTipo()) {
        case CHEGADA -> f.chegada(prox);
        case SAIDA -> f.saida(prox);
        case PASSAGEM -> f.passagem(prox);
      }

    System.out.println("Resultados da simulação:");
    filas.forEach((id, fila) -> {
      System.out.println("=".repeat(30));
      System.out.println("Fila: " + id);
      System.out.println(fila.temposToString());
    });
  }
}