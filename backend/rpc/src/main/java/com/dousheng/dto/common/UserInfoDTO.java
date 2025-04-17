package com.dousheng.dto.common;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfoDTO implements Serializable {
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
    private Boolean isFollow;
}

