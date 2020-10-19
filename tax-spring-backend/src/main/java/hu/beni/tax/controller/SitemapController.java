package hu.beni.tax.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.beni.tax.entity.Article;
import hu.beni.tax.exception.TaxException;
import hu.beni.tax.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SitemapController {

	private final ArticleRepository articleRepository;

	@GetMapping("/api/sitemap")
	public String getSitemap() {
		try (InputStream is = new ClassPathResource("sitemap.xml").getInputStream()) {
			return new String(is.readAllBytes()).replace("articlesComesThere", generateArticlesUrls());
		} catch (IOException e) {
			throw new TaxException("Could not read sitemap.xml!", e);
		}
	}

	private String generateArticlesUrls() {
		List<Article> articles = articleRepository.findAll();
		String lastModify = articles.get(0).getDate().toString();
		StringBuilder sb = new StringBuilder("<url><loc>https://adotanacs.com/cikkek</loc><lastmod>").append(lastModify)
				.append("</lastmod></url>");
		for (Article article : articles) {
			sb.append("<url><loc>https://adotanacs.com/cikkek/").append(article.getSearchKey())
					.append("</loc><lastmod>").append(article.getDate()).append("</lastmod></url>");
		}
		return sb.toString();
	}
}