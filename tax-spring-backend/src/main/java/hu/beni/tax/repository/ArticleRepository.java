package hu.beni.tax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.beni.tax.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
