package org.example.community.service;

import org.example.community.entity.Comment;
import org.example.community.utils.Result;

public interface CommentService {
    // 添加评论
    Result<?> addComment(Comment comment);

    // 获取文章的所有父评论（不包含子评论）
    Result<?> getParentCommentsByArticle(Long articleId);

    // 获取某个父评论下的所有子评论
    Result<?> getRepliesByParentComment(Long parentCommentId);
}

