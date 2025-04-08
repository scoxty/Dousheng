package com.dousheng.auth.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dousheng.auth.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class UserDO extends BaseDO {
    private Long id;
    private String name;
    private String password;
    private String doushengNum;
    private Integer sex;
    private Date birthday;
    private String country;
    private String province;
    private String city;
    private String district;
    private String signature;
    private String avatar;
    private String backgroundImage;
    private Integer canDoushengNumBeUpdated;
}
