package com.ibm;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test() {

        log.info("test start ----------------------");
        int sum = 999;
        try{
            int[] nums = {2,3,4,5,6,0};
            for (int i = 0; i < nums.length; i++) {
                log.info("device by number:"+ String.valueOf(nums[i]));
                log.info("result : " + sum/nums[i]) ;
            }
        }
        catch (Exception e){
            //e.printStackTrace();
            log.info(e.getMessage());

        }
        log.info("test end ------------------------");
    }

    @Test
    public void test2() {
        log.trace("trace start");
        log.debug("debug start");
        log.info("info test start ----------------------");
        log.warn("warn start");
        log.error("error start");
    }


}
