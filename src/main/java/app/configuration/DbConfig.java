package app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;

/**
 * Configuration class for setting up the database-related beans in the Spring application context.
 */
@Configuration
public class DbConfig {

    /**
     * Configures and provides an {@link LocalEntityManagerFactoryBean} to manage the JPA entity manager factory.
     *
     * @return An instance of {@link LocalEntityManagerFactoryBean}.
     */
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean entityManagerFactory = new LocalEntityManagerFactoryBean();
        entityManagerFactory.setPersistenceUnitName("kkcrmdatabasePersistenceUnit");
        return entityManagerFactory;
    }

    /**
     * Configures and provides a {@link JpaTransactionManager} for handling JPA transactions.
     *
     * @param entityManagerFactory The JPA entity manager factory.
     * @return An instance of {@link JpaTransactionManager}.
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
