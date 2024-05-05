package simulador;

import config.FilaConfig;
import geradorNumeros.GeradorNumeros;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Fila {

  private String id;

  private FilaConfig config;

  private int estadoAtual = 0;

  private int perda = 0;

  private ArrayList<ConexaoFila> conexoes = new ArrayList<>();

  public Fila(String id, FilaConfig config) {
    if (config.getCapacidade() != null && config.getCapacidade() <= 0) {
      throw new IllegalArgumentException("A capacidade deve ser maior que zero");
    }

    if (config.getServidores() <= 0) {
      throw new IllegalArgumentException("O número de servidores deve ser maior que zero");
    }

    this.id = id;
    this.config = config;
  }

  public void addConexaoFila(Fila fila, double probabilidade) {
    conexoes.add(new ConexaoFila(fila, probabilidade));
    conexoes = conexoes.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
  }

  public Fila getConexaoAleatoria() {
    double rand = GeradorNumeros.nextRandomNormalized(0, 1);
    double soma = 0.0;

    for (ConexaoFila conexao : conexoes) {
      soma += conexao.getProbabilidade();

      if (rand < soma) {
        return conexao.getDestino();
      }
    }
    return null;
  }

  public String getId() {
    return id;
  }

  public ArrayList<ConexaoFila> getConexoes() {
    return conexoes;
  }

  public FilaConfig getConfig() {
    return config;
  }

  public int getEstadoAtual() {
    return estadoAtual;
  }

  public void entra() {
    estadoAtual++;
  }

  public void sai() {
    if (estadoAtual > 0) {
      estadoAtual--;
    }
  }

  public void addPerda() {
    perda++;
  }

  public int getPerda() {
    return perda;
  }

  @Override
  public String toString() {
    return "Fila '" + id + "' {" + "\n\tconfig=" + config + ",\n\testadoAtual=" + estadoAtual + ",\n\tperda=" + perda + "\n}";
  }
}
