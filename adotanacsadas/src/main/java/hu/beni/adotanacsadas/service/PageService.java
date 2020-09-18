package hu.beni.adotanacsadas.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import hu.beni.adotanacsadas.entity.Article;
import hu.beni.adotanacsadas.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PageService {

    private final ArticleRepository articleRepository;
    private String main;
    private String booking;
    private String article;

    @PostConstruct
    public void init() throws IOException {

        main = readFileInIso("index");
        article = replaceContentOfMain(readFileInIso("article"));
        main = readFile("index");
        booking = replaceContentOfMain(readFile("booking"));
        main = replaceContentOfMain(readFile("content"));

    }

    private String readFile(String htmlFileName) throws IOException {
        return new String(
                new ClassPathResource("public/html/" + htmlFileName + ".html").getInputStream().readAllBytes());
    }

    private String readFileInIso(String htmlFileName) throws IOException {
        return new String(
                new ClassPathResource("public/html/" + htmlFileName + ".html").getInputStream().readAllBytes(),
                "ISO-8859-1");
    }

    private String replaceContentOfMain(String content) {
        return main.replace("id=\"content\" class=\"container\">", "id=\"content\" class=\"container\">" + content);

    }

    private String articleLinksReplace(String html) {
        List<Article> articles = articleRepository.findAll();

        String links = "";
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            try {
                links += " <li class=\"nav-item\"><a class=\"nav-link\" href=\"/cikkek/"
                        + new String(article.getSearchKey().getBytes("UTF-8"), "ISO-8859-1") + "\">"
                        + new String(article.getTitle().getBytes("UTF-8"), "ISO-8859-1") + "</a> </li>";
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return html.replace("<!--LINKS-->", links);

    }

    public String main() {
        return articleLinksReplace(main);
    }

    public String booking() {
        return articleLinksReplace(booking);
    }

    public String article() {
        return articleLinksReplace(article);
    }
}
