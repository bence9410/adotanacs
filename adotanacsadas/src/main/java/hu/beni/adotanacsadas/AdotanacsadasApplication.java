package hu.beni.adotanacsadas;

import java.time.LocalDate;
import java.util.Properties;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

import hu.beni.adotanacsadas.controller.ArticleFilter;
import hu.beni.adotanacsadas.entity.Article;
import hu.beni.adotanacsadas.repository.ArticleRepository;
import hu.beni.adotanacsadas.service.PageService;

@SpringBootApplication
public class AdotanacsadasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdotanacsadasApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(JavaMailSender mailSender, ArticleRepository articleRepository) {
		return args -> {

			articleRepository.save(Article.builder()
					.title("Természetes személy vagy azok csoportja révén fennálló kapcsolat a Kkv.tv. alapján")
					.date(LocalDate.of(2020, 9, 1))
					.content(
							"A Kkv. tv. 4. § (5) bekezdése értelmében a vállalkozások között fennálló kapcsolódó vállalkozási jogviszonyt az is megalapozza, ha az egy természetes személy vagy közösen fellépő természetes személyek egy csoportja révén jön létre, amennyiben a vállalkozások a tevékenységüket vagy annak egy részét az érintett piacon vagy egymással szomszédos piacokon folytatják. Kapcsolat lehet többségi tulajdonlás, döntő irányítási vagy ellenőrzési jogkör. A közösen fellépő természetes személyek csoportja úgy határozható meg, hogy a társaságoknak olyan magánszemély tulajdonosai vannak, akik üzleti érdekeiket összehangoltan érvényesítik, valamilyen célból közösen cselekednek. Ennek értelmében egymástól független természetes személyek által tulajdonolt vállalkozások esetén is fennállhat kapcsolódó vállalkozási viszony. Minden esetben az egyedi tényállás alapján lehet meghatározni, hogy a körülmények alapján a közös gazdasági érdek kikövetkeztethető-e.")
					.build());
		};

	}

	@Bean
	public FilterRegistrationBean<ArticleFilter> loggingFilter(PageService pageService) {
		FilterRegistrationBean<ArticleFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new ArticleFilter(pageService));
		registrationBean.addUrlPatterns("/*");

		return registrationBean;
	}

}
