package com.td.bbwp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.td.bbwp.commerce.Customer;
import com.td.bbwp.commerce.Gender;
import com.td.bbwp.course.domain.Student;
import com.td.bbwp.course.domain.StudentRepository;
import com.td.bbwp.course.domain.User;
import com.td.bbwp.course.domain.UserRepository;
import com.td.bbwp.web.action.commerce.CustomerRepository;



@SpringBootApplication
///@EntityScan("com.delivery.domain")
public class MainApp {

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(StudentRepository repository,  UserRepository urepository, CustomerRepository cr) {
		return args -> {
//			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
//			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
//			urepository.save( new User("krisv", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "USER"));
//			urepository.save( new User("mary", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "USER"));
//			
//			urepository.save(user1);
//			urepository.save(user2);
//	    	
//	        repository.save(new Student("John", "Johnson", "john@john.com"));
//	        repository.save(new Student("Mary", "Poppins", "pop@mary.com"));
//	        repository.save(new Student("Rob", "Robber", "rob@bery.com"));
//	        repository.save(new Student("Kate", "Robinson", "kate@robinson.com"));
//	        
//	        Customer jab = new Customer();
//	        jab.setFirstName("jab");
//	        jab.setLastName("mer");
//	        jab.setGender(Gender.MALE);
//	        cr.save(jab);
	    };
	}	
}
