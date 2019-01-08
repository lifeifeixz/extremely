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
package data;

import java.util.*;

/**
 * @author flysLi
 * @ClassName DataBase
 * @Decription TODO
 * @Date 2019/1/7 15:52
 * @Version 1.0
 */
public class DataBase {
    private String dataBaseName;
    private Map<String, Table> tables;

    public DataBase() {
        tables = new HashMap<String, Table>(1);
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public Table create(String tableName) {
        Table table = new Table();
        table.setName(tableName);
        tables.put(tableName, table);
        return table;
    }

    public void create(Table table) {
        tables.put(table.getName(), table);
    }

    public Iterator<Table> showTables() {
        return tables.values().iterator();
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "dataBaseName='" + dataBaseName + '\'' +
                ", tables=" + tables +
                '}';
    }
}
