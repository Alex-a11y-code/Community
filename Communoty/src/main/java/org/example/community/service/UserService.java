package org.example.community.service;

import org.example.community.entity.User;
import org.example.community.utils.Result;

public interface UserService {
    // 用户注册
    Result<?> register(User user);
    // 用户登录
    Result<?> login(String username, String password);
    // 更新用户信息
    Result<?> updateUser(Long userId, User user);
    //更新头像
    Result<?> updateAvatar(Long userId, String avatar);
    // 根据ID获取用户
    Result<?> getUserBy_Id(Long id);
}
