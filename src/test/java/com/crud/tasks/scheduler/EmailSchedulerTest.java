package com.crud.tasks.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailSchedulerTest {
    @Autowired
    EmailScheduler emailScheduler;

    @Test
    public void constructTaskStringTest(){
        //given
        long size = 1L;
        String expected = "task";
        //when
        String result = emailScheduler.constructTaskString(1L);
        //then
        assertEquals(expected, result);

    }

}