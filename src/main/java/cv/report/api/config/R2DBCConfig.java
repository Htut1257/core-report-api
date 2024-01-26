/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cv.report.api.config;

import io.r2dbc.spi.ConnectionFactory;
import org.mariadb.r2dbc.MariadbConnectionConfiguration;
import org.mariadb.r2dbc.MariadbConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionalOperator;

/**
 *
 * @author myoht
 */
@Configuration
@EnableR2dbcRepositories
@EnableTransactionManagement
@PropertySource("file:config/application.properties")
public class R2DBCConfig extends AbstractR2dbcConfiguration {

    private static final Logger log = LoggerFactory.getLogger(R2DBCConfig.class);

    @Autowired
    private Environment environment;

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        //ClassLoader loader = Thread.currentThread().getContextClassLoader();
        log.info("connectionFactory");
        return new MariadbConnectionFactory(MariadbConnectionConfiguration.builder()
                .host(environment.getProperty("db.host"))
                .port(Integer.parseInt(environment.getProperty("db.port")))
                .username(environment.getProperty("db.username"))
                .password(environment.getProperty("db.password"))
                .database(environment.getProperty("db.name"))
                .build());
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public TransactionalOperator transactionalOperator(ReactiveTransactionManager transactionManager) {
        return TransactionalOperator.create(transactionManager);
    }

}
