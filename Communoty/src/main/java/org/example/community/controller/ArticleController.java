package org.example.community.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.community.entity.Article;
import org.example.community.service.ArticleService;
import org.example.community.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/articles")
@Tag(name = "文章管理", description = "提供文章的添加、查询等接口")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 发布文章
     *
     * @param article 文章信息
     * @return 文章发布结果
     */
    @Operation(summary = "发布文章", description = "提交文章信息并创建一篇新文章")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "文章发布成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @PostMapping
    public Result<?> createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    /**
     * 获取文章详情
     *
     * @param articleId 文章ID
     * @return 文章详情
     */
    @Operation(summary = "获取文章详情", description = "根据文章ID获取文章的详细信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功获取文章详情"),
            @ApiResponse(responseCode = "404", description = "文章未找到")
    })
    @GetMapping("/{articleId}")
    public Result<?> getArticle(@PathVariable Long articleId) {
        return articleService.getArticle(articleId);
    }

    /**
     * 获取热门文章（分页）
     *
     * @param page 当前页数（默认0）
     * @param size 每页文章数量（默认10）
     * @return 热门文章列表
     */
    @Operation(summary = "获取热门文章", description = "获取热门文章，并支持分页查询")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功获取热门文章"),
            @ApiResponse(responseCode = "400", description = "分页参数错误")
    })
    @GetMapping("/hot")
    public Result<?> getHotArticles(
            @Parameter(description = "当前页数", required = false) @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "每页文章数量", required = false) @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleService.getHotArticles(pageable);
    }

    /**
     * 获取最新文章（分页）
     *
     * @param page 当前页数（默认0）
     * @param size 每页文章数量（默认10）
     * @return 最新文章列表
     */
    @Operation(summary = "获取最新文章", description = "获取最新发布的文章，并支持分页查询")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "成功获取最新文章"),
            @ApiResponse(responseCode = "400", description = "分页参数错误")
    })
    @GetMapping("/latest")
    public Result<?> getLatestArticles(
            @Parameter(description = "当前页数", required = false) @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "每页文章数量", required = false) @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleService.getLatestArticles(pageable);
    }

    /**
     * 删除文章
     *
     * @param articleId 文章ID
     * @return 删除结果
     */
    @Operation(summary = "删除文章", description = "根据文章ID删除一篇文章")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "文章删除成功"),
            @ApiResponse(responseCode = "404", description = "文章未找到")
    })
    @DeleteMapping("/{articleId}")
    public Result<?> deleteArticle(@PathVariable Long articleId) {
        return articleService.deleteArticle(articleId);
    }
}
