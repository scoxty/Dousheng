package com.dousheng.message.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dousheng.message.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "message")
public class MessageDO extends BaseDO {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String content;
}
