package ru.storeMVC.storeMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreMvcApplication {

	public static void main(String[] args) {
		System.out.println("Start app!");
		SpringApplication.run(StoreMvcApplication.class, args);
	}

}
