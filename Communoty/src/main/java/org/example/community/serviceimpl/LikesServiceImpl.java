package org.example.community.serviceimpl;
import jakarta.annotation.Resource;
import org.example.community.entity.Article;
import org.example.community.entity.Likes;
import org.example.community.entity.User;
import org.example.community.repository.ArticleRepository;
import org.example.community.repository.LikesRepository;
import org.example.community.repository.UserRepository;
import org.example.community.service.LikesService;
import org.example.community.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class LikesServiceImpl implements LikesService {
    @Resource
    private LikesRepository likeRepository;
    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private UserRepository userRepository;
    //点赞
    @Override
    public Result<?> likeArticle(Long userId, Long articleId) {
        //用户是否已点赞
        if (likeRepository.findByUser_IdAndArticle_Id(userId, articleId).isPresent()) {
            return Result.error("用户已经点赞此文章");
        }
        //文章是否存在
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isEmpty()) {
            return Result.error("文章不存在");
        }
        // 用户是否存在
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return Result.error("用户不存在");
        }
        // 创建新的点赞
        Likes like = new Likes();
        like.setUser(user.get());
        like.setArticle(article.get());
        likeRepository.save(like);
        return Result.success("点赞成功");
    }

    // 取消点赞
    @Transactional
    @Override
    public Result<?> cancelLike(Long userId, Long articleId) {
        // 是否存在点赞
        Optional<Likes> existingLike = likeRepository.findByUser_IdAndArticle_Id(userId, articleId);
        if (existingLike.isEmpty()) {
            return Result.error("用户没有点赞此文章");
        }
        // 删除点赞
        likeRepository.delete(existingLike.get());
        return Result.success("取消点赞成功");
    }

    // 用户点赞的文章
    @Override
    public Result<?> getUserLikedArticles(Long userId) {
        List<Long> likedArticleIds = likeRepository.findLikedArticleIdsByUserId(userId);
        return Result.success(likedArticleIds);
    }
    // 文点赞数
    @Override
    public Result<?> getArticleLikesCount(Long articleId) {
        long likesCount = likeRepository.countByArticle_Id(articleId);
        return Result.success(likesCount);
    }
}
