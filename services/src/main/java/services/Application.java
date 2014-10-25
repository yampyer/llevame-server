package services;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import DB.DataBaseHandler;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
    	DataBaseHandler.getInstance();
    	
    	if(args.length>1){
    		
    	} else {
    		SpringApplication.run(Application.class, args);
    	}
    }
}
