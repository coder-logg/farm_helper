package edu.itmo.isbd;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class FarmHelper {
	public static ConfigurableApplicationContext CONTEXT;

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		CONTEXT = SpringApplication.run(FarmHelper.class, args);
	}
}
