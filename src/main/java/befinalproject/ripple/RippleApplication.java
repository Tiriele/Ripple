package befinalproject.ripple;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import befinalproject.ripple.domain.AppUser;
import befinalproject.ripple.domain.AppUserRepository;
import befinalproject.ripple.domain.Post;
import befinalproject.ripple.domain.PostRepository;
import befinalproject.ripple.domain.Topic;
import befinalproject.ripple.domain.TopicRepository;

@SpringBootApplication
public class RippleApplication {
	private static final Logger log = LoggerFactory.getLogger(RippleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RippleApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PostRepository postrepository, TopicRepository topicrepository, AppUserRepository userrepository) {
		return (args) -> {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			log.info("Save some sample topics");
			topicrepository.save(new Topic("Cooking"));
			topicrepository.save(new Topic("Technology"));
			topicrepository.save(new Topic("Fitness"));

			log.info("Save some sample posts");
			postrepository.save(new Post("What should I make from leftover apples?", "SuperFoodie", LocalDate.parse("2025-01-01", formatter), LocalDate.parse("2026-01-01", formatter), 1, 10, -1, topicrepository.findByName("Cooking").get(0), ""));
			postrepository.save(new Post("Useful programming languages to learn", "TechFreak", LocalDate.parse("2025-02-02", formatter), LocalDate.parse("2026-02-02", formatter), 2, 20, -2, topicrepository.findByName("Technology").get(0), ""));
			postrepository.save(new Post("My fitness journey 2024-2025", "GymGuru", LocalDate.parse("2025-03-03", formatter), LocalDate.parse("2026-03-03", formatter), 3, 30, -3, topicrepository.findByName("Fitness").get(0), ""));

			userrepository.save(new AppUser("user", "$2a$10$MJ.8h0v8SP9EkY/jMaPTmuXUFyFRRi8ngbxhY1OYrBYERDi86NEMO", "user@email.com", "USER"));
			userrepository.save(new AppUser("admin", "$2a$10$8MuW9YQbiqlN0SSFGkv5OegdjX9oSeeu6WZGJETEpXDXdEiWxYWna", "admin@email.com", "ADMIN"));


			log.info("Fetch all the topics");
			for (Topic topic : topicrepository.findAll()) {
				log.info(topic.toString());
					};

			log.info("Fetch all the posts");
			for (Post post : postrepository.findAll()) {
		 	log.info(post.toString());
				}
		};
	}

}
