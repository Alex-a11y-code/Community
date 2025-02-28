package org.example.community.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.community.service.LikesService;
import org.example.community.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Likes")
@Tag(name = "点赞管理", description = "提供点赞的添加、删除等接口")
public class LikesController {

    @Resource
    private LikesService likeService;

    /**
     * 用户点赞文章
     *
     * @param userId 用户ID
     * @param articleId 文章ID
     * @return 点赞结果
     */
    @Operation(summary = "点赞文章", description = "用户点赞指定的文章")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "点赞成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "404", description = "文章未找到")
    })
    @PostMapping("/article/{articleId}")
    public Result<?> likeArticle(
            @Parameter(description = "用户ID", required = true) @RequestParam Long userId,
            @Parameter(description = "文章ID", required = true) @PathVariable Long articleId) {
        return likeService.likeArticle(userId, articleId);
    }

    /**
     * 用户取消点赞文章
     *
     * @param userId 用户ID
     * @param articleId 文章ID
     * @return 取消点赞结果
     */
    @Operation(summary = "取消点赞文章", description = "用户取消点赞指定的文章")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "取消点赞成功"),
            @ApiResponse(responseCode = "404", description = "文章未找到")
    })
    @DeleteMapping("/article/{articleId}")
    public Result<?> cancelLike(
            @Parameter(description = "用户ID", required = true) @RequestParam Long userId,
            @Parameter(description = "文章ID", required = true) @PathVariable Long articleId) {
        return likeService.cancelLike(userId, articleId);
    }

    /**
     * 获取文章的点赞数量
     *
     * @param articleId 文章ID
     * @return 点赞数量
     */
    @Operation(summary = "获取文章点赞数", description = "获取指定文章的点赞数量")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功获取点赞数"),
            @ApiResponse(responseCode = "404", description = "文章未找到")
    })
    @GetMapping("/count/{articleId}")
    public Result<?> getLikeCount(@PathVariable Long articleId) {
        return likeService.getArticleLikesCount(articleId);
    }

    /**
     * 获取用户已点赞的文章
     *          
     * @param userId 用户ID
     * @return 用户已点赞的文章列表
     */
    @Operation(summary = "获取用户已点赞文章", description = "获取指定用户已点赞的所有文章")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功获取已点赞文章"),
            @ApiResponse(responseCode = "404", description = "用户未找到")
    })
    @GetMapping("/user/{userId}")
    public Result<?> getUserLikedArticles(@PathVariable Long userId) {
        return likeService.getUserLikedArticles(userId);
    }
}
