package hu.beni.tax.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.beni.tax.entity.Article;
import hu.beni.tax.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

	private final ArticleRepository articleRepository;

	@GetMapping
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

}