package hu.beni.adotanacsadas.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    private final String main;
    private final String content;
    private final String booking;
    private final String article;
    private String asd = "id=\"navbar-generated-links\">";

    public PageController() throws IOException {
        main = new String(new ClassPathResource("public/index.html").getInputStream().readAllBytes()).replace(asd,
                " class=\"nav-item\"><a class=\"nav-link\" href=\"/cikkek/alma\">Alma</a>");
        content = new String(new ClassPathResource("public/html/content.html").getInputStream().readAllBytes());
        booking = new String(new ClassPathResource("public/html/booking.html").getInputStream().readAllBytes());
        article = new String(new ClassPathResource("public/html/article.html").getInputStream().readAllBytes());

    }

    @GetMapping
    public String main() {
        return main.replace("id=\"content\" class=\"container\">", "id=\"content\" class=\"container\">" + content);
    }

    @GetMapping("/idopontfoglalas")
    public String booking() {
        return main.replace("id=\"content\" class=\"container\">", "id=\"content\" class=\"container\">" + booking);
    }

    @GetMapping("/cikkek")
    public String article() {
        return main.replace("id=\"content\" class=\"container\">", "id=\"content\" class=\"container\">" + article);
    }

    @GetMapping("/cikkek/{title}")
    public String oneArticle(@PathVariable String title) {
        return title;

    }
}
