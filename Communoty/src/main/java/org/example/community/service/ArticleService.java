package org.example.community.service;

import org.example.community.entity.Article;
import org.example.community.utils.Result;

import org.springframework.data.domain.Pageable;


public interface ArticleService {
    //创建文章
    Result<?> createArticle(Article article);
    //查询文章
    Result<?> getArticle(Long articleId);
    //查询热门文章
    Result<?> getHotArticles(Pageable pageable);
    //查询最新文章
    Result<?> getLatestArticles(Pageable pageable);
    //删除文章
    Result<?> deleteArticle(Long articleId);
}
