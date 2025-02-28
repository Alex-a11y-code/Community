package org.example.community.repository;

import org.example.community.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 查询某文章的所有父评论（parentComment 为 null 的即为父评论）
    @Query("SELECT c FROM Comment c WHERE c.article.id = :articleId AND c.parentComment IS NULL")
    List<Comment> findParentCommentsByArticleId(@Param("articleId") Long articleId);

    // 查询某个父评论的所有子评论
    @Query("SELECT c FROM Comment c WHERE c.parentComment.id = :parentCommentId")
    List<Comment> findRepliesByParentCommentId(@Param("parentCommentId") Long parentCommentId);
}