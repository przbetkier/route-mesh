package dev.przbetkier.routemesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RouteMeshApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteMeshApplication.class, args);
	}
}
