package org.example.community.serviceimpl;

import jakarta.annotation.Resource;
import org.example.community.entity.Article;
import org.example.community.repository.ArticleRepository;
import org.example.community.service.ArticleService;
import org.example.community.utils.Result;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleRepository articleRepository;
    //保存文章
    @Transactional
    @Override
    public Result<?> createArticle(Article article) {
        articleRepository.save(article);
        return Result.success("文章发布成功");
    }
    //获取文章
    @Override
    public Result<?> getArticle(Long articleId) {
        //获取浏览量
        articleRepository.incrementViews(articleId);
        return articleRepository.findById(articleId)
                .map(Result::success)
                .orElseGet(() -> Result.error("文章不存在"));
    }
    //获取热门文章
    @Override
    public Result<?> getHotArticles(Pageable pageable) {
        Page<Article> hotArticles = articleRepository.findHotArticles(pageable);
        return Result.success(hotArticles);
    }
    //获取最新文章
    @Override
    public Result<?> getLatestArticles(Pageable pageable) {
        Page<Article> latestArticles = articleRepository.findAllByOrderByCreatedAtDesc(pageable);
        return Result.success(latestArticles);
    }
    //删除文章
    @Transactional
    @Override
    public Result<?> deleteArticle(Long articleId) {
        if (articleRepository.existsById(articleId)) {
            articleRepository.deleteById(articleId);
            return Result.success("文章删除成功");
        } else {
            return Result.error("文章不存在");
        }
    }
}
