package com.dousheng.video;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dousheng.dto.common.VideoInfoDTO;
import com.dousheng.video.dao.entity.VideoDO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class VideoApplicationTests {

    @Test
    void testLong() {
        Long l = 10L;
        System.out.println(l.intValue());
        System.out.println(l.longValue());
        System.out.println(IdWorker.getId());
    }

}
