package app.configuration;

import app.Application;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Servlet initializer class for configuring the Spring MVC application.
 * Extends {@link AbstractAnnotationConfigDispatcherServletInitializer}.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Specify the root configuration classes.
     *
     * @return An array of root configuration classes, including {@link Application}.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { Application.class };
    }

    /**
     * Specify the servlet configuration classes.
     *
     * @return An array of servlet configuration classes, including {@link WebConfig}.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    /**
     * Specify the servlet mappings for the DispatcherServlet.
     *
     * @return An array of URL patterns, in this case, the root ("/").
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    /**
     * Specify servlet filters to be applied.
     *
     * @return An array of servlet filters, including character encoding filter for UTF-8 encoding.
     */
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new CharacterEncodingFilter("UTF-8") };
    }
}
