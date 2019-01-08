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
package data.protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author flysLi
 * @ClassName Insert
 * @Decription TODO
 * @Date 2019/1/8 16:21
 * @Version 1.0
 */
public class Insert {
    private String table;
    private Map<String, Object> cloumn;

    public Insert(String sql) {
        String[] c = sql.split(" ");
        List<String> nc = new ArrayList();
        for (String s : c) {
            if (s.indexOf(",") > -1) {
                String[] ns = s.split(",");
                for (int j = 0; j < ns.length; j++) {
                    nc.add(ns[j]);
                }
            } else {
                nc.add(s);
            }
        }
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
