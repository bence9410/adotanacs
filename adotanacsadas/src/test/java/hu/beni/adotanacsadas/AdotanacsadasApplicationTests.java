package hu.beni.adotanacsadas;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import hu.beni.adotanacsadas.helper.DriverFacade;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AdotanacsadasApplicationTests {

	static {
		System.setProperty("webdriver.gecko.driver", "/home/jenifer/Letöltések/geckodriver");
		// System.setProperty("webdriver.gecko.driver",
		// "C:\\geckodriver\\geckodriver.exe");
	}

	@LocalServerPort
	private int port;

	private DriverFacade driverFacade;

	@Test
	void contextLoads() {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:" + port);
		driverFacade = new DriverFacade(driver);
		driverFacade.click("#navbarButton");
		driverFacade.click(".navbar-nav li:nth-child(2)");
		driverFacade.visible("#avaiableTimes");
	}

}
