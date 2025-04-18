package com.dousheng.dto.req.video;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IsExistReqDTO implements Serializable {
    private Long videoId;
}
