package org.ggt.kafka.config.provider;

import org.apache.kafka.common.config.ConfigData;
import org.apache.kafka.common.config.provider.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

public class KafkaEnvConfigProvider implements ConfigProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEnvConfigProvider.class);
  private static final Long TTL = Duration.of(5, ChronoUnit.MINUTES).toMillis();

  private final EnvPropertiesProvider propertiesProvider;

  public KafkaEnvConfigProvider() {
    this.propertiesProvider = new EnvPropertiesProvider();
  }

  @Override
  public ConfigData get(String path) {
    ConfigData configData = new ConfigData(propertiesProvider.getProperties());
    return configData;
  }

  @Override
  public ConfigData get(String path, Set<String> keys) {
    ConfigData configData = new ConfigData(propertiesProvider.getProperties(keys));
    return configData;
  }

  @Override
  public void close() throws IOException {
  }

  @Override
  public void configure(Map<String, ?> configs) {
    LOGGER.info("received config parameters: {}", configs);
  }

}
