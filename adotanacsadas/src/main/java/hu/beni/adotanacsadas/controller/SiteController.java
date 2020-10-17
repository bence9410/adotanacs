package hu.beni.adotanacsadas.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.beni.adotanacsadas.entity.Article;
import hu.beni.adotanacsadas.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SiteController {

	private final ArticleRepository articleRepository;

	@GetMapping("/api/sitemap")
	public String getSitemap() {

		String sitemap = "";

		try {
			sitemap = new String(new ClassPathResource("sitemap.xml").getInputStream().readAllBytes());
		} catch (IOException e) {
			log.error("Error:", e);
		}

		List<Article> articles = articleRepository.findAll();
		String lastModify = articles.get(0).getDate().toString();
		String articlesSitemap = "<url><loc>http://adotanacs.com/cikkek</loc><lastmod>" + lastModify
				+ "</lastmod></url>";
		for (Article article : articles) {
			articlesSitemap += "<url><loc>http://adotanacs.com/cikkek/" + article.getSearchKey() + "</loc><lastmod>"
					+ article.getDate() + "</lastmod></url>";
		}
		return sitemap.replace("articlesComesHere", articlesSitemap);
	}
}