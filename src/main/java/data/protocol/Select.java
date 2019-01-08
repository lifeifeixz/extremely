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

import java.util.Map;

/**
 * @author flysLi
 * @ClassName Select
 * @Decription TODO
 * @Date 2019/1/8 15:35
 * @Version 1.0
 */
public class Select {
    private String table;
    private Map<String, String> viewCloumn;
    private Map<String, Object> condit;

    public Select(String sql) {
        //获取表明
        int formIndex = sql.indexOf(" FROM ");
        int whereIndex = sql.indexOf(" WHERE ");
        int groupIndex = sql.indexOf(" GROUP BY ");
        int orderIndex = sql.indexOf(" ORDER BY ");
        if (whereIndex == -1 && groupIndex == -1 && orderIndex == -1) {
            table = sql.substring(formIndex);
        }
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Map<String, String> getViewCloumn() {
        return viewCloumn;
    }

    public void setViewCloumn(Map<String, String> viewCloumn) {
        this.viewCloumn = viewCloumn;
    }

    public Map<String, Object> getCondit() {
        return condit;
    }

    public void setCondit(Map<String, Object> condit) {
        this.condit = condit;
    }
}
