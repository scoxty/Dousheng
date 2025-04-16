package com.dousheng.dto.req.favorite;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavoriteActionReqDTO implements Serializable {
    private Long userId;
    private Long authorId;
    private Long videoId;
    private Integer actionType;
}
