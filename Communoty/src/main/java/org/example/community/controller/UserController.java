package org.example.community.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.community.entity.User;
import org.example.community.service.UserService;
import org.example.community.utils.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "用户管理", description = "提供用户的注册、登陆等接口")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册接口
     *
     * @param user 用户信息
     * @return 注册结果
     */
    @Operation(summary = "用户注册", description = "用户注册，提交用户名、密码等信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "注册成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        return userService.register(user);
    }

    /**
     * 用户登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @Operation(summary = "用户登录", description = "用户登录，提交用户名和密码进行验证")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "401", description = "用户名或密码错误")
    })
    @PostMapping("/login")
    public Result<?> login(
            @Parameter(description = "用户名", required = true) @RequestParam String username,
            @Parameter(description = "密码", required = true) @RequestParam String password) {
        return userService.login(username, password);
    }

    /**
     * 获取用户信息接口
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @Operation(summary = "获取用户信息", description = "根据用户ID获取用户详细信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "获取成功"),
            @ApiResponse(responseCode = "404", description = "用户未找到")
    })
    @GetMapping("/{id}")
    public Result<?> getUser(@PathVariable Long id) {
        return userService.getUserBy_Id(id);
    }

    /**
     * 更新用户信息接口
     *
     * @param id 用户ID
     * @param user 更新后的用户信息
     * @return 更新结果
     */
    @Operation(summary = "更新用户信息", description = "根据用户ID更新用户信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "404", description = "用户未找到")
    })
    @PutMapping("/{id}")
    public Result<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @Operation(summary = "更新用户头像", description = "用户可以更新自己的头像")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "头像更新成功"),
            @ApiResponse(responseCode = "404", description = "用户未找到")
    })
    @PatchMapping("/{id}/avatar")
    public Result<?> updateAvatar(
            @PathVariable Long id,
            @RequestParam String avatar) {
        return userService.updateAvatar(id, avatar);
    }



}
