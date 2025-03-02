package org.example.community.repository;

import org.example.community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 根据用户名查找用户
    Optional<User> findByUsername(String username);

    // 根据 ID 查找用户
    Optional<User> findById(Long id);

    // 判断用户名是否存在
    boolean existsByUsername(String username);

    // 根据 ID 删除用户
    void deleteById(Long id);
}
