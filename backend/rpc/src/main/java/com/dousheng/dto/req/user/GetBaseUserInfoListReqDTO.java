package com.dousheng.dto.req.user;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetBaseUserInfoListReqDTO implements Serializable {
    private List<Long> userIds;
}
