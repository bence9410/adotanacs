package hu.beni.adotanacsadas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.beni.adotanacsadas.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
