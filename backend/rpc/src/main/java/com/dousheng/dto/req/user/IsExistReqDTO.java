package com.dousheng.dto.req.user;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IsExistReqDTO implements Serializable {
    private List<Long> userIdList;
}
