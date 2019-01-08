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

/**
 * @author flysLi
 * @ClassName Analysis
 * @Decription TODO
 * @Date 2019/1/8 15:25
 * @Version 1.0
 */
public class Analysis {
    private String sql;
    private String doType;

    public Analysis(String sql) {
        this.sql = sql;
    }

    public Object doer() {
        if (sql.indexOf("SELECT ") > -1) {
            this.doType = "SELECT";
            return new Select(sql);
        } else if (sql.indexOf("INSERT ") > -1) {
            this.doType = "INSERT";
            return new Insert(sql);
        }
        return null;
    }

}
