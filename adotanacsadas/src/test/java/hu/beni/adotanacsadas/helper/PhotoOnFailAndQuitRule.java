package hu.beni.adotanacsadas.helper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.util.FileCopyUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PhotoOnFailAndQuitRule extends TestWatcher {

    private final DriverFacade driverFacade;

    @Override
    protected void failed(Throwable e, Description description) {
        log.info("krunoki");
        try {
            File f = new File("/home/jenifer/" + description.getMethodName()
                    + LocalDateTime.now().toString().replace(':', '-') + ".png");
            File a = driverFacade.takeScreenshot();

            FileCopyUtils.copy(a, f);
            driverFacade.quit();
        } catch (IOException e1) {
            log.error("Error:", e1);

        }

    }

    @Override
    protected void succeeded(Description description) {
        driverFacade.quit();
    }

}
