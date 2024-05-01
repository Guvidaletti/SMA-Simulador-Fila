package evento;

import config.FilaConfig;
import geradorNumeros.GeradorNumeros;
import simulador.Escalonador;
import simulador.Fila;
import simulador.Tempo;

import javax.naming.LimitExceededException;

public class Operacoes {

  public static void chegada(Evento ev, Escalonador escalonador) {
    try {
      Fila f = ev.getDestino();
      FilaConfig filaConfig = f.getConfig();

      double tempoAcumulado = Tempo.acumulaTempo(f.getId(), f.getEstadoAtual(), ev.getTempo());

      if (f.getConfig().getCapacidade() == null || f.getEstadoAtual() < f.getConfig().getCapacidade()) {
        f.entra();

        if (f.getEstadoAtual() <= filaConfig.getServidores()) {
          Fila destino = f.getConexaoAleatoria();

          double duracao = GeradorNumeros.nextRandomNormalized(filaConfig.getMinServico(), filaConfig.getMaxServico());
          double tempoQueAconteceOEvento = tempoAcumulado + duracao;

          TipoEvento tipo = destino == null ? TipoEvento.SAIDA : TipoEvento.PASSAGEM;
          escalonador.addEvento(new Evento(tipo, tempoQueAconteceOEvento, f, destino), tempoQueAconteceOEvento);
        }
      } else {
        f.addPerda();
      }
//      Agendar uma nova Chegada
      double duracao = GeradorNumeros.nextRandomNormalized(filaConfig.getMinChegada(), filaConfig.getMaxChegada());
      double tempoQueAconteceOEvento = tempoAcumulado + duracao;

      escalonador.addEvento(new Evento(TipoEvento.CHEGADA, tempoQueAconteceOEvento, null, f), tempoQueAconteceOEvento);

    } catch (LimitExceededException ex) {
//      System.err.println(ex.getMessage());

    }
  }

  public static void saida(Evento ev, Escalonador escalonador) {
    try {
      Fila f = ev.getOrigem();
      FilaConfig filaConfig = f.getConfig();

      double tempoAcumulado = Tempo.acumulaTempo(f.getId(), f.getEstadoAtual(), ev.getTempo());
      f.sai();

      if (f.getEstadoAtual() >= filaConfig.getServidores()) {
        double duracao = GeradorNumeros.nextRandomNormalized(filaConfig.getMinServico(), filaConfig.getMaxServico());
        double tempoQueAconteceOEvento = tempoAcumulado + duracao;

        Fila destino = f.getConexaoAleatoria();
        TipoEvento tipo = destino == null ? TipoEvento.SAIDA : TipoEvento.PASSAGEM;
        escalonador.addEvento(new Evento(tipo, tempoQueAconteceOEvento, f, destino), tempoQueAconteceOEvento);
      }
    } catch (LimitExceededException ex) {
//      System.err.println(ex.getMessage());
    }
  }

  public static void passagem(Evento ev, Escalonador escalonador) {
    try {
      Fila origem = ev.getOrigem();
      FilaConfig origemConfig = origem.getConfig();

      Fila destino = ev.getDestino();
      FilaConfig destinoConfig = destino.getConfig();

      double tempoAcumulado = Tempo.acumulaTempo(origem.getId(), origem.getEstadoAtual(), ev.getTempo());
      origem.sai();

      if (origem.getEstadoAtual() >= origemConfig.getServidores()) {
        Fila novoDestino = origem.getConexaoAleatoria();
        TipoEvento tipoNovoEvento = novoDestino == null ? TipoEvento.SAIDA : TipoEvento.PASSAGEM;

        double duracao = GeradorNumeros.nextRandomNormalized(origemConfig.getMinServico(), origemConfig.getMaxServico());
        double tempoQueAconteceOEvento = tempoAcumulado + duracao;

        escalonador.addEvento(new Evento(tipoNovoEvento, tempoQueAconteceOEvento, origem, novoDestino), tempoQueAconteceOEvento);
      }

      if (destinoConfig.getCapacidade() == null || destino.getEstadoAtual() < destinoConfig.getCapacidade()) {
        destino.entra();
//        TEM UM ERRO NO ALGORITMO! (acho que falta o acúmulo)

        if (destino.getEstadoAtual() <= destinoConfig.getServidores()) {
          double duracao = GeradorNumeros.nextRandomNormalized(destinoConfig.getMinServico(), destinoConfig.getMaxServico());
          double tempoQueAconteceOEvento = tempoAcumulado + duracao;

          escalonador.addEvento(new Evento(TipoEvento.SAIDA, tempoQueAconteceOEvento, destino, null), tempoQueAconteceOEvento);
        }
      } else {
        destino.addPerda();
      }
    } catch (LimitExceededException ex) {
//      System.err.println(ex.getMessage());
    }
  }
}