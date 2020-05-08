package io.github.runnlin.controller;

import io.github.runnlin.pojo.User;
import io.github.runnlin.result.Result;
import io.github.runnlin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    // 设定返回体
    @ResponseBody
    // 设置RequestBody后，传入的数据会自动转换成这个类
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        // 进行转义，防止 xss 攻击
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());

        if (null == user) {
            String message = "帐号或密码错误";
            System.out.println("error username");
            return new Result(400);
        } else {
            System.out.println(username+" Logged in");
            return new Result(200);
        }
    }
}
