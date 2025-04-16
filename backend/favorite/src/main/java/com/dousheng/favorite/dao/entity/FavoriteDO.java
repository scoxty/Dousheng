package com.dousheng.favorite.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "favorite")
public class FavoriteDO {
    private Long id;
    private Long userId;
    private Long videoId;
}
