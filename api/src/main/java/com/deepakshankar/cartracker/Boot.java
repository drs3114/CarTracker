package com.deepakshankar.cartracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManagerFactory;

/**
 * This is the Booter class {@link SpringApplication} will use to start the spring boot application. All the
 * necessary beans such as datasource, transactionManager are defined in this class.
 *
 * @author Deepak Shankar
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
public class Boot {

    /**
     * The main function to start the application
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

    /**
     * This is a bean to get a {@link DispatcherServlet} to start dispatching
     *
     * @return a {@link DispatcherServlet} object.
     */
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    /**
     * All the servlet mappings are done through this bean.
     *
     * @return a {@link ServletRegistrationBean}
     */
    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet(), "/tracker/api/*");
        servletRegistrationBean.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return servletRegistrationBean;
    }

    /**
     * This bean is used to create a {@link io.swagger.models.Swagger} UI to get API documentation.
     *
     * @return a {@link Docket}
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }


    /**
     * This bean is used to get a {@link javax.transaction.TransactionManager} to handle transactions in the
     * application.
     *
     * @param emf the {@link EntityManagerFactory} object
     * @return a {@link javax.transaction.TransactionManager} object.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txm = new JpaTransactionManager(emf);
        return txm;
    }

}
