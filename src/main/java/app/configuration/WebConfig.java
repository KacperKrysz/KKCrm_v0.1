package app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Configuration class for setting up web-related beans and configurations in the Spring application context.
 * Implements {@link WebMvcConfigurer} to customize Spring MVC settings.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure view resolvers to resolve JSP views.
     *
     * @param registry The {@link ViewResolverRegistry} to configure.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    /**
     * Set the default time zone to UTC for the application.
     */
    @Bean
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    /**
     * Configure and provide a {@link LocaleContextResolver} to manage the application's locale.
     *
     * @return An instance of {@link SessionLocaleResolver} with the default locale set to Polish (pl).
     */
    @Bean(name = "localeResolver")
    public LocaleContextResolver getLocaleContextResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.forLanguageTag("pl"));
        return localeResolver;
    }
}

