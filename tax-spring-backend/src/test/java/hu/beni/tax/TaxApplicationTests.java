package hu.beni.tax;

import org.junit.After;
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

import hu.beni.tax.helper.DriverFacade;
import hu.beni.tax.service.FreeTimeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaxApplicationTests {
	private static final FirefoxOptions FO;

	static {
		System.setProperty("webdriver.gecko.driver", "/home/jenifer/Downloads/geckodriver");
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		// firefoxBinary.addCommandLineOptions("--headless");
		FO = new FirefoxOptions();
		FO.setBinary(firefoxBinary);
	}

	@LocalServerPort
	private int port;

	private DriverFacade driverFacade;

	@Autowired
	private FreeTimeService freeTimeGenerator;

	@Before
	public void before() {
		driverFacade = new DriverFacade(new FirefoxDriver(FO));
	}

	@After
	public void after() throws InterruptedException {
		// Thread.sleep(30000000);
		driverFacade.quit();
	}

	@Test
	public void articleTest() {
		String articleTitle = "Természetes személy vagy azok csoportja révén fennálló kapcsolat a Kkv.tv. alapján";
		driverFacade.get("http://localhost:" + port);
		driverFacade.click(".v-app-bar__nav-icon");
		driverFacade.click(".v-navigation-drawer__content a:nth-child(4)");
		driverFacade.text(".articles h1", "Cikkek");
		driverFacade.text(".articles .v-card__title", articleTitle);
		driverFacade.text(".articles .v-card__subtitle ", "2020-09-01");
		driverFacade.click(".v-input .v-select__slot");
		driverFacade.text(".v-menu__content .v-list-item__title", articleTitle);
		driverFacade.click(".v-menu__content .v-list-item__title");
		driverFacade.text(".articles .v-card__title", articleTitle);
		driverFacade.attributeToBe(".v-input .v-select__slot input", "value", articleTitle);

	}

	@Test
	public void bookingTest() {
		driverFacade.get("http://localhost:" + port);
		openDialogForFirstAvailableForOneHour();
		driverFacade.write(".v-form .v-text-field__slot input", "Varga Virag");
		driverFacade.write(".v-form .row div:nth-child(2) input", "varga@gmail.com");
		driverFacade.click(".v-form .row div:nth-child(3) .v-select__slot");
		driverFacade.click(".menuable__content__active .v-list-item:nth-child(2)");
		driverFacade.write(".v-form .row div:nth-child(4) textarea", "Jó napot!");
		driverFacade.click(".v-form .row div:nth-child(4) .v-input:nth-child(2)");
		driverFacade.click(".v-card__actions .v-btn ");
		driverFacade.text(".v-dialog h1", "Sikertelen foglalás.");
		driverFacade.click(".v-dialog .red .v-btn__content");
		driverFacade.hidden(".v-dialog");
	}

	@Test
	public void bookingValidate() {
		driverFacade.get("http://localhost:" + port);
		openDialogForFirstAvailableForOneHour();
		driverFacade.click(".v-form .row div:nth-child(4) .v-input:nth-child(2)");
		driverFacade.click(".v-card__actions .v-btn ");
		driverFacade.text(".v-form .row div:nth-child(1) .v-messages__message", "Kötelező kitölteni.");
		driverFacade.text(".v-form .row div:nth-child(2) .v-text-field__details .v-messages__message",
				"Az e-mail címet a valaki@pelda.com formátumban írja be.");
		driverFacade.text(".v-form .row div:nth-child(3) .v-messages__message", "Kötelező kitölteni.");
		driverFacade.text(".v-form .row div:nth-child(4) .v-messages__message", "Kötelező kitölteni.");
	}

	public void openDialogForFirstAvailableForOneHour() {
		driverFacade.click(".v-app-bar__nav-icon");
		driverFacade.click(".v-navigation-drawer__content a:nth-child(3)");
		driverFacade.text(".content h4", "Foglaljon időpontot most.");
		String nextFarFriday = freeTimeGenerator.calcNextFarFriday().toString().replace('-', '.') + ". Péntek";
		driverFacade.text(".v-card__text", nextFarFriday);
		driverFacade.text(".v-card .v-card .v-card__text ", "14:30-tól");
		driverFacade.click(".v-input__slot");
		driverFacade.click(".v-menu__content .v-list-item:nth-child(2)");
		driverFacade.click(".v-card .v-btn ");
		driverFacade.text(".v-card .headline",
				"Kérem töltse ki a foglaláshoz az adatait.\n" + nextFarFriday + " 14:30 60 perc");
	}
}
