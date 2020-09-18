package hu.beni.adotanacsadas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import hu.beni.adotanacsadas.service.PageService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PageController {
    private final PageService pageService;

    @GetMapping
    public String main() {
        return pageService.main();
    }

    @GetMapping("/idopontfoglalas")
    public String booking() {
        return pageService.booking();
    }

}
