package com.spharos.conversion.configuration.database;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@EnableConfigurationProperties(value = {
        DatabaseInfoVendor.DataSourceProperties.class,
        DatabaseInfoVendor.JpaProperties.class
})
@Slf4j
public class DatabaseInfoVendor {
    @Getter
    @Setter
    @ToString
    public static class Properties {
        private String driverClassName;
        private String url;
        private String username;
        private String password;
        private String params;
        private String sqlScriptEncoding;
        private String initializationMode;
        private String schema;
        private String data;
    }


    @ConfigurationProperties(prefix = "spring.datasource")
    public static class DataSourceProperties extends Properties {
        @PostConstruct
        public void postConstruct() {
            DatabaseInfoVendor.log.debug("get datasource information::::: " + this.toString());
        }
    }

    @Getter
    @Setter
    @ConfigurationProperties(prefix = "spring.jpa")
    public static class JpaProperties {
        @Getter
        @Setter
        public static class Properties {
            @Getter
            @Setter
            public static class Hibernate {
                public static class Hbm2ddl {
                    private String auto;

                    public String getAuto() {
                        return auto;
                    }

                    public void setAuto(String auto) {
                        this.auto = auto;
                    }
                }


                private String dialect;
                private Hbm2ddl hbm2ddl;
                private String showSql;
                private String formatSql;
            }


            private Hibernate hibernate;
        }


        private Properties properties;

        @PostConstruct
        public void postConstruct() {
            DatabaseInfoVendor.log.debug("get jpaProperties information::::: " + this.toString());
        }
    }
}
