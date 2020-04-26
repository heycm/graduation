package com.heycm.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.heycm.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 自动填充字段(createUser/createTime/modifyUser/modifyTime)
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void insertFill(MetaObject metaObject) {
        //判断表里是否有createTime的方法,如果有可以再去加自动填充，因为我们几乎所有表都有，所以不加判断
        //boolean hasSetter = metaObject.hasSetter("createTime");
        boolean createUser = metaObject.hasSetter("createUser");
        boolean modifyUser = metaObject.hasSetter("modifyUser");
        setInsertFieldValByName("createTime", new Date(), metaObject);
        setInsertFieldValByName("modifyTime", new Date(), metaObject);
        setInsertFieldValByName("isDeleted",0, metaObject);
        if(createUser){
            setInsertFieldValByName("createUser",getIdByToken(), metaObject);
        }
        if(modifyUser){
            setInsertFieldValByName("modifyUser",getIdByToken(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //加上判断你是否自己设置了修改时间，如果设置了这里不会填充
        Object modifyTime = getFieldValByName("modifyTime",metaObject);
        Object modifyUser = getFieldValByName("modifyUser",metaObject);
        if(modifyTime==null){
            setUpdateFieldValByName("modifyTime", new Date(), metaObject);
        }
        if(modifyUser==null){
            setUpdateFieldValByName("modifyUser",getIdByToken(), metaObject);
        }
    }

    private Integer getIdByToken(){
        System.out.println("======》getIdByToken()");
        Subject subject = SecurityUtils.getSubject();
        String token = (String) subject.getPrincipal();
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Integer userId = jwtUtil.getUserId(token);
        System.out.println("======》自动填充userID："+userId);
        return userId;
    }
}
