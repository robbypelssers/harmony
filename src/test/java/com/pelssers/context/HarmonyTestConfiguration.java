package com.pelssers.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({VertxConfig.class, MainRouterConfig.class, ApiRouterConfig.class, ControllerConfig.class, ServicesConfig.class, RepositoryConfig.class})
@PropertySource(value = { "classpath:conf/harmony-test.properties" })
public class HarmonyTestConfiguration {
}
