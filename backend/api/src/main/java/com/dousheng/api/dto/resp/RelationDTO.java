package com.dousheng.api.dto.resp;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RelationDTO {
    private String vlogerId;
    private String nickname;
    private String face;
    private boolean isFollowed;
}