package app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Main configuration class for the Spring application. This class enables various Spring features
 * and annotations to configure the application's behavior.
 *
 * - {@link Configuration}: Indicates that this class is a Spring configuration class.
 * - {@link ComponentScan}: Scans for Spring components within the application package and its subpackages.
 * - {@link EnableWebMvc}: Enables Spring Web MVC for handling web-related functionality.
 * - {@link EnableJpaRepositories}: Enables Spring Data JPA repository support.
 * - {@link EnableTransactionManagement}: Enables Spring's transaction management support.
 * - {@link EnableWebSecurity}: Enables Spring Security for web application security.
 */
@Configuration
@ComponentScan
@EnableWebMvc
@EnableJpaRepositories
@EnableTransactionManagement
@EnableWebSecurity
public class Application {
}
