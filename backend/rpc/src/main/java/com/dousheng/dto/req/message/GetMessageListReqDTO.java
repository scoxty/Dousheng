package com.dousheng.dto.req.message;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetMessageListReqDTO implements Serializable {
    private Long userId;
    private Long friendId;
    private Integer page;
    private Integer pageSize;
}
