package org.example.community.repository;

import org.example.community.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 按点击量降序分页查询热门文章
    @Query("SELECT a FROM Article a ORDER BY a.views DESC")
    Page<Article> findHotArticles(Pageable pageable);
    // 按时间降序分页查询最新文章
    Page<Article> findAllByOrderByCreatedAtDesc(Pageable pageable);
    //实现浏览量递增
    @Modifying
    @Query("UPDATE Article a SET a.views = COALESCE(a.views, 0) + 1 WHERE a.id = :articleId")
    void incrementViews(@Param("articleId") Long articleId);


}
