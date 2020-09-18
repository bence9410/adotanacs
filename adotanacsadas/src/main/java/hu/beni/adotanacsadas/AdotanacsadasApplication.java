package hu.beni.adotanacsadas;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import hu.beni.adotanacsadas.controller.ArticleFilter;
import hu.beni.adotanacsadas.entity.Article;
import hu.beni.adotanacsadas.repository.ArticleRepository;
import hu.beni.adotanacsadas.service.PageService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class AdotanacsadasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdotanacsadasApplication.class, args);

	}

	@Bean
	public ApplicationRunner applicationRunner(ArticleRepository articleRepository) {
		return args -> {
			articleRepository.save(Article.builder().title("Alma §154. fa").content("Itt egy almafa.").build());
			articleRepository.save(Article.builder().title("Körte §155. fa").content("Itt van sok körte.").build());
		};

	}

	@Bean
	public FilterRegistrationBean<ArticleFilter> loggingFilter(PageService pageService) {
		FilterRegistrationBean<ArticleFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new ArticleFilter(pageService));
		registrationBean.addUrlPatterns("/cikkek/*");

		return registrationBean;
	}

}
