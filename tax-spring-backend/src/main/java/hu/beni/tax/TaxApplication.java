package hu.beni.tax;

import java.time.LocalDate;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

import hu.beni.tax.entity.Article;
import hu.beni.tax.filter.TaxFilter;
import hu.beni.tax.repository.ArticleRepository;

@SpringBootApplication
public class TaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxApplication.class, args);
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

			articleRepository.save(Article.builder()
				.title("Fejlesztési tartalék, mint az előrehozott értékcsökkenés új korlátja")
				.date(LocalDate.of(2020,10,1))
				.content(
						"Az előrehozott értékcsökkenés célja, hogy a négy éven belül megkezdett beruházás megvalósítására legyen elegendő forrás.<br>Tao tv. 7. § (1) bekezdés f) pontja szerint az adózás előtti eredményt csökkenti az eredménytartaléknak az adóévben lekötött tartalékba átvezetett és az adóév utolsó napján a beszámolóban lekötött tartalékként (a továbbiakban: fejlesztési tartalék) kimutatott összege. 2020. évközi törvénymódosítás megváltoztatta a korábbi 50%-os korlátot, hatályos jogszabály szerint a korlát az adóévi adózás előtti nyereség, de legfeljebb adóévenként 10 milliárd forint. Tao tv. 7. § (15) bekezdése felsorolja, hogy mely beruházásoknál nem élhet az adózó az előrehozott értékcsökkenéssel, egyben túlmutat a számviteli törvény szerinti értékcsökkenési korlátokra. Megvásárolni kívánt telek bekerülési értékére, amennyiben nem tartozik a kivételek közé, fejlesztési tartalékot feloldani nem szabad, annak értékcsökkenését tiltja a törvény.<br>Ha a fejlesztési tartalék képzésénél, annak felhasználásával összefüggésben hibák fordulnak elő, annak a következménye lehet adóhiány, adóbírság, mulasztási bírság. Immateriális javak bekerülési értéke alapján jogkövetkezmények nélkül nem oldható fel a tartalék.")
				.build());

			articleRepository.save(Article.builder()
				.title("Szerződés elszámolási egysége és a teljesítési fok meghatározása nem számlázott alvállalkozói teljesítés esetén")
				.date(LocalDate.of(2020, 11, 1))
				.content(
						"Az összemérés elvével összhangban el kell számolni az eredményben a szerződés elszámolási egységéhez kapcsolódó, az adott teljesítési fokhoz felmerült költségeket, ráfordításokat. Tárgyévi árbevételnek kell fedezetet nyújtania az adott évet terhelő költségekre. A teljesítési fok, azaz a ténylegesen elvégzett munkáknak az elvégzendő összes munkához viszonyított aránya független attól, hogy az alvállalkozói szerződés szerinti teljesítés és számlázás megtörtént-e a fordulónapra, vagy csak azt követően. Az új előírások szerint az - áfa-t nem tartalmazó árbevételt a szerződés elszámolási egysége teljesítésével arányosan (a teljesítési fok arányában) kell elszámolni az eredményben.<br>2019.évben vagy azt megelőzően kötött és 2020. évben még be nem fejeződött projekteknél az adózót választási lehetőség illeti a fordulónapi elszámolásnál, hogy<br>- a „hagyományos” módon, befejezetlen termelésként mutatja be, vagy<br>- a projektszámvitel alkalmazását választja, ha a teljesítmény, szerződés elszámolási egységhez tartozik.")
				.build());
			};
	}

	@Bean
	public FilterRegistrationBean<TaxFilter> loggingFilter() {
		FilterRegistrationBean<TaxFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new TaxFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

}
