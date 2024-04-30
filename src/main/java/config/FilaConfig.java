package config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class FilaConfig {
  @JsonProperty("servidores")
  private int servidores;

  @JsonProperty(value = "capacidade", required = false)
  private Integer capacidade;

  @JsonProperty("minChegada")
  private double minChegada;

  @JsonProperty("maxChegada")
  private double maxChegada;

  @JsonProperty("minServico")
  private double minServico;

  @JsonProperty("maxServico")
  private double maxServico;

  public int getServidores() {
    return servidores;
  }

  public void setServidores(int servidores) {
    this.servidores = servidores;
  }

  public Integer getCapacidade() {
    return capacidade;
  }

  public void setCapacidade(Integer capacidade) {
    this.capacidade = capacidade;
  }

  public double getMinChegada() {
    return minChegada;
  }

  public void setMinChegada(double minChegada) {
    this.minChegada = minChegada;
  }

  public double getMaxChegada() {
    return maxChegada;
  }

  public void setMaxChegada(double maxChegada) {
    this.maxChegada = maxChegada;
  }

  public double getMinServico() {
    return minServico;
  }

  public void setMinServico(double minServico) {
    this.minServico = minServico;
  }

  public double getMaxServico() {
    return maxServico;
  }

  public void setMaxServico(double maxServico) {
    this.maxServico = maxServico;
  }

  @Override
  public String toString() {
    return "FilaConfig = {" +
        "\n\tservidores=" + servidores +
        ",\n\tcapacidade=" + capacidade +
        ",\n\tminChegada=" + minChegada +
        ",\n\tmaxChegada=" + maxChegada +
        ",\n\tminServico=" + minServico +
        ",\n\tmaxServico=" + maxServico +
        "\n}";
  }
}
