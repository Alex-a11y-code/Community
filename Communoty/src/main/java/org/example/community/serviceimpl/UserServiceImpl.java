package org.example.community.serviceimpl;
import jakarta.annotation.Resource;
import org.example.community.entity.User;
import org.example.community.repository.UserRepository;
import org.example.community.service.UserService;
import org.example.community.utils.Result;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;
    // 用户注册
    @Override
    public Result<?> register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return Result.error("用户名已存在");
        }
        userRepository.save(user);
        return Result.success("注册成功");
    }
    // 用户登录
    @Override
    public Result<?> login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return Result.success("登录成功");
            }
        }
        return Result.error("用户名或密码错误");
    }
    // 更新用户信息
    @Override
    public Result<?> updateUser(Long userId, User user) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setAvatar(user.getAvatar());
            userRepository.save(updatedUser);
            return Result.success("用户信息更新成功");
        } else {
            return Result.error("用户不存在");
        }
    }

    @Override
    public Result<?> updateAvatar(Long userId, String avatar) {
        if (avatar == null || avatar.trim().isEmpty()) {
            return Result.error("头像 URL 不能为空");
        }

        // 仅允许 https 的图片链接
        if (!avatar.matches("^(https?://.*\\.(jpg|jpeg|png|gif|bmp))$")) {
            return Result.error("头像 URL 格式不正确");
        }

        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setAvatar(avatar);
            try {
                userRepository.save(user);
                return Result.success("头像更新成功");
            } catch (Exception e) {
                return Result.error("头像更新失败");
            }
        } else {
            return Result.error("用户不存在");
        }
    }



    // 根据ID获取用户
    @Override
    public Result<?> getUserBy_Id(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(Result::success).orElseGet(() -> Result.error("用户不存在"));
    }


}
