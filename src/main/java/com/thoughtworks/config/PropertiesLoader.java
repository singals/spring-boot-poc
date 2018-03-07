package com.thoughtworks.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;
import java.util.Properties;

@Configuration
public class PropertiesLoader{

    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);
    private static final String DEV = "dev";

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            String env = System.getProperty("env");
            if (env == null || env.isEmpty()) env = DEV;
            String appPropertiesFile = String.format("%s.properties", env);

            final InputStream resourceAsStream = PropertiesLoader.class.getClassLoader()
                    .getResourceAsStream(appPropertiesFile);
            properties.load(resourceAsStream);
        } catch (final Exception e) {
            logger.error("Couldn't load Application properties", e);
        }
        return properties;
    }

    @Bean
    public PropertyPlaceholderConfigurer getConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setProperties(PropertiesLoader.loadProperties());
        return propertyPlaceholderConfigurer;

    }
}
