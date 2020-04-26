package com.heycm.controller.demo;

import com.google.code.kaptcha.Producer;
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

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;
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

    @RequestMapping("/unAuth")
    public String unAuthorized(){
        return "403";
    }

    @RequestMapping("/api/v1/open/getToken")
    @ResponseBody
    public String getJwtToken(String username, String password){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("password", password);
        String token = jwtUtil.generateJwtToken(1, username, map);
        return token;
    }

    @RequestMapping("/open/parseToken")
    @ResponseBody
    public Object parseJwtToken(String token){
        Claims claims = jwtUtil.parseJwtToken(token);
        String id = claims.getId();
        String issuer = claims.getSubject();
        Object password = claims.get("password");
        return id+" -- "+issuer+" -- "+password;
    }



    @ApiOperation(value = "获取图片验证码", notes = "获取图片验证码")
    @GetMapping("/open/captchaImage")
    public Object getCaptchaImage(HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            // Set standard HTTP/1.1 no-cache headers.
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            // Set standard HTTP/1.0 no-cache header.
            response.setHeader("Pragma", "no-cache");
            // return a jpeg
            response.setContentType("image/jpeg");
            // create the text for the image
            String capText = captchaProducer.createText();
            //将验证码存到session
            // 也可以存到redis灵活设置过期时间
            session.setAttribute("KAPTCHA_SESSION_KEY", capText);
            logger.info(capText);
            // 创建一张文本图片
            BufferedImage bi = captchaProducer.createImage(capText);
            // 写入数据
            ImageIO.write(bi, "jpg", outputStream);

            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @ApiOperation(value = "校验图片验证码", notes = "校验图片验证码")
    @GetMapping("/open/verifyCaptcha")
    @ResponseBody
    public String verifyCaptcha(HttpServletRequest request,
        @ApiParam(name = "code", value = "验证码值", required = true)String code){

        String captchaCode = (String) request.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        if (StringUtils.isEmpty(captchaCode)){
            return "验证码失效";
        }
        if (StringUtils.isEmpty(code)){
            return "验证码无效";
        }

        // 全都转为小写或大写
        captchaCode = captchaCode.toLowerCase();
        code = code.toLowerCase();

        if (captchaCode.equals(code)){
            return "验证通过";
        }

        return "验证失败";
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
