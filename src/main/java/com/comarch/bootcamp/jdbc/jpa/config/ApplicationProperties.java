package com.comarch.bootcamp.jdbc.jpa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationProperties {
  boolean devMode = false;
  Integer premiumUser;
}
