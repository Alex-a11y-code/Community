package org.example.community.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.community.entity.Comment;
import org.example.community.service.CommentService;
import org.example.community.utils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * 评论管理 Controller 提供评论的添加、查询等接口
 */
@RestController
@RequestMapping("/comments")
@Tag(name = "评论管理", description = "提供评论的添加、查询等接口")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 添加评论
     *
     * @param comment 评论对象，包含文章ID、用户ID、评论内容及父评论（可选）
     * @return 操作结果，包含成功或失败信息
     */
    @PostMapping("/add")
    @Operation(summary = "添加评论", description = "支持添加父评论和子评论")
    public Result<?> addComment(
            @Parameter(description = "评论对象，包含文章ID、用户ID、评论内容及父评论（可选）") @RequestBody Comment comment
    ) {
        return commentService.addComment(comment);
    }

    /**
     * 获取某篇文章的所有父评论（不包含子评论）
     *
     * @param articleId 文章ID，指定获取哪篇文章的父评论
     * @return 文章的父评论列表，包含每个评论的详细信息
     */
    @GetMapping("/article/{articleId}/parents")
    @Operation(summary = "获取文章的父评论", description = "获取某篇文章的所有父级评论（不包含子评论）")
    public Result<?> getParentComments(
            @Parameter(description = "文章ID", example = "1") @PathVariable Long articleId
    ) {
        return commentService.getParentCommentsByArticle(articleId);
    }

    /**
     * 获取某个父评论下的所有子评论
     *
     * @param parentCommentId 父评论ID，指定获取哪个父评论的子评论
     * @return 子评论列表，包含每个子评论的详细信息
     */
    @GetMapping("/parent/{parentCommentId}/replies")
    @Operation(summary = "获取子评论", description = "获取某个父评论的所有子评论")
    public Result<?> getReplies(
            @Parameter(description = "父评论ID", example = "1") @PathVariable Long parentCommentId
    ) {
        return commentService.getRepliesByParentComment(parentCommentId);
    }
}
