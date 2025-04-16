package com.dousheng.dto.req.favorite;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetFavoriteListReqDTO implements Serializable {
    private Long userId;
    private Integer page;
    private Integer pageSize;
}
