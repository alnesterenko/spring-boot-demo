package com.example.demo.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {
/* Параметры соединения могут изменяться, поэтому имеет смысл и дальше указывать их отдельно от кода приложения.
 В данном примере они хранятся в файле application.properties */
    @Value("${custom.datasource.url}")
    private String datasourceUrl;

    @Value("${custom.datasource.username}")
    private String datasourceUsername;

    @Value("${custom.datasource.password}")
    private String datasourcePassword;

    /* Метод возвращает объект DataSource.
     Если Spring Boot обнаруживает, что в контексте Spring уже есть DataSource, новый он не создает */
    @Bean
    public DataSource dataSource() {
        /* В качестве источника данных в этом примере мы будем использовать HikariCP.
         Но если проект требует чего-то другого, самостоятельно создавая бин,
          вы можете выбрать любой другой источник данных */
        HikariDataSource dataSource = new HikariDataSource();
/* Устанавливаем параметры соединения для источника данных */
        dataSource.setJdbcUrl(datasourceUrl);
        dataSource.setUsername(datasourceUsername);
        dataSource.setPassword(datasourcePassword);
/* Вы можете определить и другие параметры (которые, возможно, понадобятся при определенных условиях).
 В данном случае я в качестве примера использовал время ожидания подключения
  (сколько времени источник данных будет ждать установки соединения, прежде чем решит, что оно не удалось) */
        dataSource.setConnectionTimeout(1000);
/* Возвращаем экземпляр DataSource, который Spring внесет в контекст */
        return dataSource;
    }
}
