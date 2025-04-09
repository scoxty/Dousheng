package com.dousheng.api.common.web.middleware;

import cn.hutool.core.util.NumberUtil;
import com.dousheng.api.common.biz.user.UserContext;
import com.dousheng.api.common.biz.user.UserInfoDTO;
import com.dousheng.dto.req.auth.AuthenticateReqDTO;
import com.dousheng.dto.resp.auth.AuthenticateRespDTO;
import com.dousheng.service.AuthRpcService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import static com.dousheng.api.common.constant.SuccessBaseRespConstant.SUCCESS_CODE;

/**
 * 鉴权中间件，用于验证用户是否有权限访问资源。
 */
@Component
public class Auth {

    @DubboReference(timeout = 5000, retries = 3, loadbalance = "roundrobin")
    private AuthRpcService authRpcService;

    public boolean isAllowed(HttpServletRequest request) {
        Long userId = NumberUtil.parseLong(request.getHeader("headerUserId"));
        String token = request.getHeader("headerUserToken");

        UserInfoDTO userInfoDTO = UserInfoDTO.builder().userId(userId).build();
        UserContext.setUser(userInfoDTO);

        AuthenticateReqDTO reqDTO = AuthenticateReqDTO.builder().
                userId(userId).
                token(token).build();
        AuthenticateRespDTO respDTO = authRpcService.authenticate(reqDTO);
        return respDTO.getCode().equals(SUCCESS_CODE);
    }
}
