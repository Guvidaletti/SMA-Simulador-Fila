package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class ConfigLoader {
  public static SimuladorConfig load(String name) {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    try {
      // Carregue o arquivo YAML para um mapa
      SimuladorConfig yamlMap = objectMapper.readValue(new File(name + ".yml"), SimuladorConfig.class);

      return yamlMap;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
