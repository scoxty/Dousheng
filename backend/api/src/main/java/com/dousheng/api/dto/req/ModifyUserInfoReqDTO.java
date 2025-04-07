package com.dousheng.api.dto.req;

import lombok.Data;

import java.util.Date;

@Data
public class ModifyUserInfoReqDTO {
    private String id;
    private String nickname;
    private String douShengNum;
    private String face;
    private Integer sex;
    private Date birthday;
    private String country;
    private String province;
    private String city;
    private String district;
    private String description;
    private String bgImg;
    private Integer canDouShengNumBeUpdated;
}
