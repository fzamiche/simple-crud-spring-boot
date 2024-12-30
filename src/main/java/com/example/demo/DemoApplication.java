package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.repo.BookRepo;
import org.aspectj.weaver.bcel.BcelAccessForInlineMunger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertBooks(BookRepo bookRepo) {
		return args -> {
			bookRepo.save(new Book(null, "Le Petit Prince", "Antoine de Saint-Exupéry"));
			bookRepo.save(new Book(null, "1984", "George Orwell"));
			bookRepo.save(new Book(null, "Les Misérables", "Victor Hugo"));
			bookRepo.save(new Book(null, "L'Étranger", "Albert Camus"));
			bookRepo.save(new Book(null, "Harry Potter à l'école des sorciers", "J.K. Rowling"));
			bookRepo.save(new Book(null, "Le Seigneur des anneaux", "J.R.R. Tolkien"));
			bookRepo.save(new Book(null, "La Peste", "Albert Camus"));
			bookRepo.save(new Book(null, "Le Comte de Monte-Cristo", "Alexandre Dumas"));
			bookRepo.save(new Book(null, "Don Quichotte", "Miguel de Cervantes"));
			bookRepo.save(new Book(null, "Crime et Châtiment", "Fiodor Dostoïevski"));
		};
	}
}
