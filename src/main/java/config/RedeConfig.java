package config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedeConfig {
  @JsonProperty("origem")
  private String origem;

  @JsonProperty("destino")
  private String destino;

  @JsonProperty("probabilidade")
  private double probabilidade;

//  Getters and Setters

  public String getOrigem() {
    return origem;
  }

  public void setOrigem(String origem) {
    this.origem = origem;
  }

  public String getDestino() {
    return destino;
  }

  public void setDestino(String destino) {
    this.destino = destino;
  }

  public double getProbabilidade() {
    return probabilidade;
  }

  public void setProbabilidade(double probabilidade) {
    this.probabilidade = probabilidade;
  }

//  Overrides

  @Override
  public String toString() {
    return "RedeConfig{" +
        "origem='" + origem + '\'' +
        ", destino='" + destino + '\'' +
        ", probabilidade=" + probabilidade +
        '}';
  }
}
