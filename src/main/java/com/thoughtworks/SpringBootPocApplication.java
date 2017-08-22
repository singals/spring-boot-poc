package com.thoughtworks;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootPocApplication {

    private static Log logger = LogFactory.getLog(SpringBootPocApplication.class);

//    @Bean
//    protected ServletContextListener listener() {
//        return new ServletContextListener() {
//
//            @Override
//            public void contextInitialized(ServletContextEvent sce) {
//                logger.info("********************SHASHANK**************");
//            }
//
//            @Override
//            public void contextDestroyed(ServletContextEvent sce) {
//                logger.info("********************SHASHANK**************");
//            }
//
//        };
//    }

    public static void main( String[] args ) {
        SpringApplication.run(SpringBootPocApplication.class, args);
    }
}
