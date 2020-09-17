package hu.beni.adotanacsadas;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import hu.beni.adotanacsadas.helper.DriverFacade;
import hu.beni.adotanacsadas.helper.FreeTimeGenerator;
import hu.beni.adotanacsadas.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdotanacsadasApplicationTests {
	private static final FirefoxOptions FO;

	static {
		System.setProperty("webdriver.gecko.driver", "/home/jenifer/Letöltések/geckodriver");
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		// firefoxBinary.addCommandLineOptions("--headless");
		FO = new FirefoxOptions();
		FO.setBinary(firefoxBinary);
	}

	@LocalServerPort
	private int port;

	private DriverFacade driverFacade;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private FreeTimeGenerator freeTimeGenerator;

	@Before
	public void before() {
		driverFacade = new DriverFacade(new FirefoxDriver(FO));

	}

	@After
	public void after() {
		driverFacade.quit();
	}

	@Test
	public void articleTest() {
		driverFacade.get("http://localhost:" + port);
		driverFacade.click("#navbarButton");
		driverFacade.click(".navbar-nav li:nth-child(3)");
		driverFacade.text("#items h2", "Cikkek");
		driverFacade.text("#articlesRoot > div:nth-child(1) .card-title ", "Alma");
		driverFacade.text("#articlesRoot > div:nth-child(1) .card-text", "Itt egy almafa.");
		driverFacade.notPresent("#articlesRoot > div:nth-child(2)");
	}

	@Test
	public void bookingDate() {
		driverFacade.get("http://localhost:" + port);
		driverFacade.click("#navbarButton");
		driverFacade.click(".navbar-nav li:nth-child(2)");
		driverFacade.text(".bookingContainer h4", "Foglaljon időpontot most.");
		LocalDate nextFarFriday = freeTimeGenerator.calcNextFarFriday();
		String bookingFreeDateTime = (nextFarFriday.getMonth().getValue() < 10
				? "0" + nextFarFriday.getMonth().getValue()
				: nextFarFriday.getMonth().getValue()) + "."
				+ (nextFarFriday.getDayOfMonth() < 10 ? "0" + nextFarFriday.getDayOfMonth()
						: nextFarFriday.getDayOfMonth())
				+ ". Péntek";
		driverFacade.text(".day:nth-child(1) .card-header", bookingFreeDateTime);
		driverFacade.text(".day:nth-child(1) .card:nth-child(1) .card-body div:nth-child(1) ", "14:30-tól");
		driverFacade.select(".day:nth-child(1) .card:nth-child(1) select", "ONE_HOUR");
		driverFacade.click(".day:nth-child(1) input ");
		driverFacade.text("#actualDateTime", "09.11. Péntek 14:30 60 perc");
		driverFacade.write("#bookingInputName", "Varga Virag");
		driverFacade.write("#bookingInputEmail", "varga@gmail.com");
		driverFacade.select("#meetingType", "SKYPE");
		driverFacade.write("#bookingInputText", "Jó napot!");
		driverFacade.click("#toBooking");
		driverFacade.click("#bookingSucces button ");
		Assert.assertEquals(1, bookingRepository.count());

	}

	@Test
	public void bookingValidate() {
		driverFacade.get("http://localhost:" + port);
		driverFacade.click("#navbarButton");
		driverFacade.click(".navbar-nav li:nth-child(2)");
		driverFacade.text(".bookingContainer h4", "Foglaljon időpontot most.");
		driverFacade.text(".day:nth-child(1) .card:nth-child(1) .card-body div:nth-child(1) ", "14:30-tól");
		driverFacade.select(".day:nth-child(1) .card:nth-child(1) select", "ONE_HOUR");
		driverFacade.click(".day:nth-child(1) input ");
		driverFacade.text("#actualDateTime", "09.25. Péntek 14:30 60 perc");
		driverFacade.write("#bookingInputEmail", "varga@gmail.com");
		driverFacade.write("#bookingInputText", "Üdvözlöm!");
		driverFacade.click("#toBooking");
		driverFacade.text("#bookingNotValidMessage",
				"Nem lehet üres a név mező!" + "\n" + "A név mező legalább 3 de maximum 30 karaktert tartalmazhat!");
		driverFacade.click(".modal-header button");
		driverFacade.hidden(".modal-backdrop");
		driverFacade.click(".day:nth-child(1) input ");
		driverFacade.write("#bookingInputName", "Varga Virag");
		driverFacade.write("#bookingInputEmail", "vargagmail.com");
		driverFacade.write("#bookingInputText", "Üdvözlöm!");
		driverFacade.click("#toBooking");
		driverFacade.text("#bookingNotValidMessage", "Az email mezőnek tartalmaznia kell @ és . karaktereket!");
		driverFacade.click(".modal-header button");
		driverFacade.hidden(".modal-backdrop");
		driverFacade.click(".day:nth-child(1) input ");
		driverFacade.write("#bookingInputName", "Varga Virag");
		driverFacade.write("#bookingInputEmail", "varga@gmail.com");
		driverFacade.click("#toBooking");
		driverFacade.text("#bookingNotValidMessage", "Nem lehet üres a rövid leírás mező!" + "\n"
				+ "A rövid leírás mezönek legalább 3 de maximum 60 karaktert tartalmazhat!");
		driverFacade.click(".modal-header button");
		driverFacade.hidden(".modal-backdrop");
	}

}
