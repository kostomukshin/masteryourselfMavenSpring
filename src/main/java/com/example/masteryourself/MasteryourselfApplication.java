package com.example.masteryourself;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.masteryourself.model.ActivityModel;
import com.example.masteryourself.model.ActivityRepository;
import com.example.masteryourself.model.Category;
import com.example.masteryourself.model.CategoryRepository;
import com.example.masteryourself.model.User;
import com.example.masteryourself.model.UserRepository;



@SpringBootApplication
public class MasteryourselfApplication {
	private static final Logger log = LoggerFactory.getLogger(MasteryourselfApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MasteryourselfApplication.class, args);
	}

	@Bean
	public CommandLineRunner userDemo(ActivityRepository arepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("");
			
			crepository.save(new Category("Work"));
			crepository.save(new Category("Studies"));
			crepository.save(new Category("Hobby"));
			crepository.save(new Category("Else"));
			
			
			
			User userfirst = new User("admin", "$2a$12$57N7bzUCM8KubXnmDoqvn.wU4FTnj597hoAvFnqYLfFrwlzmUgZpa", "admin@aws.com", "ADMIN");
			urepository.save(userfirst);
			
			arepository.save(new ActivityModel("Composing music", 2.5, "26.04.2022", "Trying to learn harmonies", crepository.findByName("Hobby").get(0), userfirst));
			arepository.save(new ActivityModel("Preparing for Business Math exam", 3, "25.04.2022", "--", crepository.findByName("Studies").get(0), userfirst));
			arepository.save(new ActivityModel("Learning Bootstrap", 2, "26.04.2022", "Tables and Alerts", crepository.findByName("Studies").get(0), userfirst));
			arepository.save(new ActivityModel("Playing drums", 2, "28.04.2022", "--", crepository.findByName("Hobby").get(0), userfirst));
			arepository.save(new ActivityModel("Server Programming project", 5, "27.04.2022", "--", crepository.findByName("Studies").get(0), userfirst));
			arepository.save(new ActivityModel("Personal Trainer App", 2, "27.04.2022", "Tables and Alerts", crepository.findByName("Studies").get(0), userfirst));
			
			
			log.info("");
			for (ActivityModel activity : arepository.findAll()) {
				log.info(activity.toString());
			}
			
		};
			
			
			
		}
	}
	
	
