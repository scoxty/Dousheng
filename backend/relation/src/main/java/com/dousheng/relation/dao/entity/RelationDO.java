package com.dousheng.relation.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "relation")
public class RelationDO {
    private Long id;
    private Long userId;
    private Long fanId;

}
