package com.bess.springboot.wfx.util;

import java.util.Random;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/31 20:01
 */
public class RandomId {
    public static String getNum(int digit) {
        StringBuilder str = new StringBuilder();
        for (int i=0; i < digit; i++) {
            if (i == 0 && digit > 1) {
                str.append(new Random().nextInt(9)+1);
            } else {
                str.append(new Random().nextInt(10));
            }
        }
        Long value = Long.valueOf(str.toString());
        String strId = value+"";
        return strId;
    }
}
