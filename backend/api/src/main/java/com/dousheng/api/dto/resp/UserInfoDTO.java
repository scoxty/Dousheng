package com.dousheng.api.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfoDTO {
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
    private Date createTime;
    private Date updateTime;

    private String userToken;

    private Integer myFollowsCounts;
    private Integer myFansCounts;
    //    private Integer myLikedVlogCounts;
    private Integer totalLikeMeCounts;
}
