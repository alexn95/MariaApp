package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoDefaultConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@SpringBootApplication
public class CodeFlowExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeFlowExampleApplication.class, args);
    }

    /**
     * Enable the use of {@link PreAuthorize} annotation and OAuth expressions like {code}#oauth2.hasScope('email'){code}.
     */
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    protected static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
        @Override
        protected MethodSecurityExpressionHandler createExpressionHandler() {
            return new OAuth2MethodSecurityExpressionHandler();
        }
    }

    /**
     * The default Spring logout behavior redirects a user back to {code}/login?logout{code}, so you will likely want
     * to change that.  The easiest way to do this is by both extending from {@link OAuth2SsoDefaultConfiguration} and
     * annotating your implementation with {@link EnableOAuth2Sso}.
     */
    @Configuration
    @EnableOAuth2Sso
    static class ExampleSecurityConfigurerAdapter extends OAuth2SsoDefaultConfiguration {

        public ExampleSecurityConfigurerAdapter(ApplicationContext applicationContext, OAuth2SsoProperties sso) {
            super(applicationContext, sso);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            // In this example we allow anonymous access to the root index page
            // this MUST be configured before calling super.configure
            http.authorizeRequests().antMatchers("/").permitAll();

            // calling super.configure locks everything else down
            super.configure(http);
            // after calling super, you can change the logout success url
            http.logout().logoutSuccessUrl("/");
        }
    }

    /**
     * This example controller has endpoints for displaying the user profile info on {code}/{code} and "you have been
     * logged out page" on {code}/post-logout{code}.
     */
    @Controller
    public class ExampleController {

        @GetMapping("/")
        public String home() {
            return "index";
        }

        @RequestMapping(value = "orders", method = RequestMethod.GET)
        public ModelAndView orders(ModelAndView modelAndView) {
//            try (Connection connection = dataSource.getConnection()) {
//                Statement stmt = connection.createStatement();
//                ResultSet rs = stmt.executeQuery("SELECT * FROM orders");
//
//                ArrayList<String> output = new ArrayList<String>();
//                while (rs.next()) {
//                    String order = rs.getString("surname");
//                    order += ", ";
//                    order += rs.getString("name");
//                    order += ", ";
//                    order += rs.getString("phone");
//                    order += ", ";
//                    order += rs.getString("note");
//                    order += ", ";
//                    order += rs.getTimestamp("createtime");
//                    output.add("Заказ: " + order);
//                }
            modelAndView = new ModelAndView();
            modelAndView.setViewName("orders");
            return modelAndView;
        }
    }
}
