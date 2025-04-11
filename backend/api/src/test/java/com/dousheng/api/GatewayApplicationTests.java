package com.dousheng.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import com.dousheng.api.dto.req.ModifyUserInfoReqDTO;
import com.dousheng.dto.common.UserInfoDTO;
import org.junit.jupiter.api.Test;

class GatewayApplicationTests {

    @Test
    void testPacker() {
        System.out.println(NumberUtil.parseLong(""));
        System.out.println(NumberUtil.parseLong(" "));
        System.out.println(NumberUtil.parseLong(null));
    }

}
