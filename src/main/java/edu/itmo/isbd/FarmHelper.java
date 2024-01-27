package edu.itmo.isbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.TimeZone;

@SpringBootApplication
public class FarmHelper {
	public static ConfigurableApplicationContext CONTEXT;

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		CONTEXT = SpringApplication.run(FarmHelper.class, args);
	}
}
