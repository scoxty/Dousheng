package com.dousheng.api.dto.req;

import lombok.Data;

import java.util.Date;

@Data
public class ModifyUserInfoReqDTO {
    private String id;
    private String username;
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
