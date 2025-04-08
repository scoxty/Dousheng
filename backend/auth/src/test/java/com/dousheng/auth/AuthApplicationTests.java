package com.dousheng.auth;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class AuthApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testStrUtil() {
        String str = "  ";
        System.out.println(StrUtil.isEmpty(str));
        System.out.println(StrUtil.isBlank(str));
    }

}
