package hu.beni.tax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.beni.tax.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findAllByOrderByIdDesc();

}
