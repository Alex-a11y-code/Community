package org.example.community.serviceimpl;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.example.community.entity.Comment;
import org.example.community.repository.CommentRepository;
import org.example.community.service.CommentService;
import org.example.community.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentRepository commentRepository;

    // 添加评论
    @Transactional
    @Override
    public Result<?> addComment(Comment comment) {
        commentRepository.save(comment);
        return Result.success("评论添加成功");
    }

    // 获取某篇文章的所有父评论（不包含子评论）
    @Override
    public Result<?> getParentCommentsByArticle(Long articleId) {
        List<Comment> parentComments = commentRepository.findParentCommentsByArticleId(articleId);
        return Result.success(parentComments);
    }

    // 获取某个父评论下的所有子评论
    @Override
    public Result<?> getRepliesByParentComment(Long parentCommentId) {
        List<Comment> replies = commentRepository.findRepliesByParentCommentId(parentCommentId);
        return Result.success(replies);
    }
}
