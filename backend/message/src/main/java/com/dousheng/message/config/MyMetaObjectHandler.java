package com.dousheng.message.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Date;

@Primary
@Component(value = "myMetaObjectHandlerByMessage")
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "createTime", Date::new, Date.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "updateTime", Date::new, Date.class);
    }
}
