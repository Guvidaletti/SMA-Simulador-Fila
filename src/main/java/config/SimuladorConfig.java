package config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class SimuladorConfig {
  @JsonProperty("numeros")
  private long numeros;

  @JsonProperty("sementes")
  private long[] sementes;

  @JsonProperty("chegadas")
  private Map<String, Double> chegadas;

  @JsonProperty("filas")
  private Map<String, FilaConfig> filas;

  public long getNumeros() {
    return numeros;
  }

  public void setNumeros(long numeros) {
    this.numeros = numeros;
  }

  public long[] getSementes() {
    return sementes;
  }

  public void setSementes(long[] sementes) {
    this.sementes = sementes;
  }

  public Map<String, Double> getChegadas() {
    return chegadas;
  }

  public void setChegadas(Map<String, Double> chegadas) {
    this.chegadas = chegadas;
  }

  public Map<String, FilaConfig> getFilas() {
    return filas;
  }

  public void setFilas(Map<String, FilaConfig> filas) {
    this.filas = filas;
  }

  @Override
  public String toString() {
    return "SimuladorConfig = {" +
        "\n\tnumeros=" + numeros +
        ",\n\tsementes=" + sementes +
        ",\n\tchegadas=" + chegadas +
        ",\n\tfilas=" + filas +
        '}';
  }
}
