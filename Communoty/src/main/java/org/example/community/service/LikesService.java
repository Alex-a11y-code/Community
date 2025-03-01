package org.example.community.service;

import org.example.community.utils.Result;

public interface LikesService {  // 修改为 LikesService
    // 用户点赞文章
    Result<?> likeArticle(Long userId, Long articleId);
    // 用户取消点赞
    Result<?> cancelLike(Long userId, Long articleId);
    // 获取某个用户已点赞的文章列表
    Result<?> getUserLikedArticles(Long userId);
    // 获取某篇文章的点赞数
    Result<?> getArticleLikesCount(Long articleId);
}
