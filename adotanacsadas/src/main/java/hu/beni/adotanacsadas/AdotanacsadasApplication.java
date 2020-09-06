package hu.beni.adotanacsadas;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import hu.beni.adotanacsadas.entity.Article;
import hu.beni.adotanacsadas.repository.ArticleRepository;

@SpringBootApplication
public class AdotanacsadasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdotanacsadasApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(ArticleRepository articleRepository) {
		return args -> articleRepository.save(Article.builder().title("Alma").content("Itt egy almafa.").build());
	}

}
