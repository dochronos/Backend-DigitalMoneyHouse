package com.example.activities_server;

import com.example.activities_server.services.ActivityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ActivitiesServerApplicationTests {

    @Autowired
    private ActivityService activityService;

    @Test
    void contextLoads() {
        assertThat(activityService).isNotNull();
    }
}