package com.dousheng.video;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class VideoApplicationTests {

    @Test
    void testLong() {
        Long l = 10L;
        System.out.println(l.intValue());
        System.out.println(l.longValue());
    }

}
