package com.heycm.controller.demo;

import com.heycm.server.WebSocketServer;
import com.heycm.utils.JwtUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-03-24 18:07
 */

@Controller
@Api(tags = "接口样例")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping(value = {"/", "/index", "/index.html"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String toIndex(Model model){
        model.addAttribute("msg", "Hello Shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String toAdd(Model model){
        System.out.println("add方法");
        Object principal = SecurityUtils.getSubject().getPrincipal();
        System.out.println(principal);
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String toUpdate(Model model){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, boolean rememberMe, Model model){

        System.out.println("执行登录方法===");
        System.out.println(username+"--"+password+"--"+rememberMe);

        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 封装当前用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        // 执行登录逻辑
        try {
            subject.login(token);
            model.addAttribute("msg", username+"欢迎，" + username);
            System.out.println("登录成功");
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在");
            System.out.println("用户名不存在");
            return "login";
        } catch (DisabledAccountException e) {
            model.addAttribute("msg", "账户被禁用");
            System.out.println("账户被禁用");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            System.out.println("密码错误");
            return "login";
        } catch (AuthenticationException e) {
            model.addAttribute("msg", "认证失败");
            System.out.println("认证失败");
            return "login";
        } catch (Exception e) {
            model.addAttribute("msg", "系统异常");
            System.out.println("系统异常");
            return "login";
        }
    }


    @GetMapping("/open/ws")
    public String index(){
        return "ws/ws";
    }

    @GetMapping("/open/sendOne")
    @ResponseBody
    public ResponseMessage sendOne(String message, String userId, String friendId){
        // WsMessage wsMessage = new WsMessage();
        // wsMessage.setDate(new Date());
        // wsMessage.setUserId(userId);
        // wsMessage.setFriendId(friendId);
        // wsMessage.setContent(message);
        //
        // try {
        //     return webSocketServer.sendOne(wsMessage);
        // } catch (IOException e) {
        //     logger.error("websocket IO异常, 发送失败");
        //     return Result.error("websocket IO异常, 发送失败");
        // }
        return Result.ok();
    }

}
