package hu.beni.adotanacsadas;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import hu.beni.adotanacsadas.helper.DriverFacade;
import hu.beni.adotanacsadas.helper.PhotoOnFailAndQuitRule;
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

	private LocalDate calcNextFarFriday() {
		LocalDate date = LocalDate.now();
		return isWednesdayOrThursday(date) ? nextFriday(nextFriday(date)) : nextFriday(date);

	}

	private boolean isWednesdayOrThursday(LocalDate localDate) {
		return localDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)
				|| localDate.getDayOfWeek().equals(DayOfWeek.THURSDAY);
	}

	private LocalDate nextFriday(LocalDate localDate) {
		return localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
	}

	@Test
	public void bokingDate() {
		driverFacade.get("http://localhost:" + port);
		driverFacade.click("#navbarButton");
		driverFacade.click(".navbar-nav li:nth-child(2)");
		driverFacade.text(".bookingContainer h4", "Foglaljon időpontot most.");
		LocalDate nextFarFriday = calcNextFarFriday();
		String bookingFreeDateTime = (nextFarFriday.getMonth().getValue() < 10
				? "0" + nextFarFriday.getMonth().getValue()
				: nextFarFriday.getMonth().getValue()) + "."
				+ (nextFarFriday.getDayOfMonth() < 10 ? "0" + nextFarFriday.getDayOfMonth()
						: nextFarFriday.getDayOfMonth())
				+ ". Péntek";
		driverFacade.text(".day:nth-child(1) .card-header", bookingFreeDateTime);
		driverFacade.text(".day:nth-child(1) .card:nth-child(1) .card-body div:nth-child(1) ", "14:30-tól");
		driverFacade.select(".day:nth-child(1) .card:nth-child(1) select", "1");
		driverFacade.click(".day:nth-child(1) input ");
		/// ".day:nth-child(1) .time:nth-child(2) select"
		driverFacade.text("#actualDateTime", "9.11. Péntek 14:30 60 perc");
		driverFacade.write("#bookingInputName", "Varga Virag");
		driverFacade.write("#bookingInputEmail", "varga@gmail.com");
		driverFacade.select("#meetingType", "skype");
		driverFacade.write("#bookingInputText", "Jó napot!");
		driverFacade.click("#toBooking");
		driverFacade.click(".modal-body:nth-child(1) button ");
		driverFacade.text(".day:nth-child(1) .card:nth-child(1) .card-body div:nth-child(1) ", "16:00-tól");
		// driverFacade.text("#finalBooking", "");
	}

}
