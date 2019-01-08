/*
 * Copyright (c) 2018, 2018, Travel and/or its affiliates. All rights reserved.
 * TRAVEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author flysLi
 * @ClassName SpeedTest
 * @Decription TODO
 * @Date 2019/1/8 11:53
 * @Version 1.0
 */
public class SpeedTest {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<String>(1000);
        long t = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            stringList.add(String.valueOf(i));
        }
        long e = System.currentTimeMillis();
        System.out.println("总耗时:" + (e - t));
    }
}
