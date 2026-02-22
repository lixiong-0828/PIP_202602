package com.ibm;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest_2 {
    //private static final Logger log = LoggerFactory.getLogger(LogTest_2.class);

    @Test
    public void test() {

        System.out.println("test start ----------------------");
        int sum = 999;
        try{
            int[] nums = {2,3,4,5,6,0};
            for (int i = 0; i < nums.length; i++) {
                System.out.println("device by number:"+ String.valueOf(nums[i]));
                System.out.println("result : " + sum/nums[i]) ;
            }
        }
        catch (Exception e){
            //e.printStackTrace();
            System.out.println("exception occured");
            System.out.println(e.getMessage());

        }
        System.out.println("test end ------------------------");
    }


}
