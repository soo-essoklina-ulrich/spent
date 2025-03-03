package com.month.spent;

import com.month.spent.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpentApplication implements CommandLineRunner {
	@Value("${super.admin.email}")
	private String EMAIL;
	@Value("${super.admin.password}")
	private String PASSWORD;
	@Value("${super.admin.username}")
	private String USERNAME;

	private final UtilisateurService utilisateurService;

	public SpentApplication(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			utilisateurService.createSuprerAdmin(EMAIL, USERNAME, PASSWORD);
		} catch (Exception e) {
			System.out.println();
		}
	}
}
