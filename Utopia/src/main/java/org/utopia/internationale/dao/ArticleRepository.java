package org.utopia.internationale.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utopia.internationale.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
