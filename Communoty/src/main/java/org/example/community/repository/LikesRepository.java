package org.example.community.repository;

import org.example.community.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    // 查找用户是否已经喜欢了某篇文章
    Optional<Likes> findByUser_IdAndArticle_Id(Long userId, Long articleId);
    // 查找用户喜欢的所有文章ID
    @Query("SELECT l.article.id FROM Likes l WHERE l.user.id = :userId")
    List<Long> findLikedArticleIdsByUserId(Long userId);
    // 统计某篇文章的点赞数
    long countByArticle_Id(Long articleId);

}
