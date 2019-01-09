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

import data.protocol.Condition;
import data.protocol.Kv;
import data.protocol.Select;
import data.result.ResultSetScreenCondit;

import java.util.*;

/**
 * @author flysLi
 * @ClassName Table
 * @Decription TODO
 * @Date 2019/1/7 15:54
 * @Version 1.0
 */
public class Table {
    private String name;
    private Map<String, Column> columnMap;
    private Select select;

    public Table() {
        columnMap = new HashMap<String, Column>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Column> getFields() {
        return columnMap;
    }

    public Column addColumn(String name) {
        Column column = new Column<>();
        column.setName(name);
        columnMap.put(name, column);
        return column;
    }

    public Column addColumn(Column column) {
        columnMap.put(column.getName(), column);
        return column;
    }

    public List<Object> getColumn(String name) {
        return columnMap.get(name).getValue();
    }

    public List<Object> getColumn(int index) {
        Iterator iterator = columnMap.values().iterator();
        if (iterator.hasNext()) {
            return (List) iterator.next();
        }
        return null;
    }

    public int getRowCount() {
        Set<String> keySet = columnMap.keySet();
        for (String key : keySet) {
            return columnMap.get(key).getValue().size();
        }

        return 0;
    }

    public Set<String> getColName() {
        return columnMap.keySet();
    }

    public int getColCount() {
        return getColName().size();
    }

    public Column addColumn(String clumm, String type) {
        Column column = new Column();
        column.setName(clumm);
        column.setType(type);
        columnMap.put(clumm, column);
        return column;
    }

    /**
     * 通过行号(下标)获取某行数据
     *
     * @param index
     * @return
     */
    public Map<String, Object> index(int index) {
        Set<String> keys = this.getColName();
        Map<String, Object> result = new HashMap<>();
        for (String key : keys) {
            Object value = getColumn(key).get(index);
            result.put(key, value);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", fields=" + columnMap +
                '}';
    }

    public int insert(List<Kv> kvs) {
        for (Kv kv : kvs) {
            //获取列对象
            String colName = kv.getColumn();
            Column column = columnMap.get(colName);
            /*如果列不存在，将创建列*/
            if (column == null) {
                column = this.addColumn(colName);
            }
            //列数据
            List values = column.getValue();
            values.add(kv.getValue());
        }
        return 1;
    }


    public Object select(Select select) {
        List<Map<String, Object>> kvs = new ArrayList<>(100);
        //获取总行数
        int count = this.getRowCount();
        Set<String> columnKeys = getColName();
        for (int i = 0; i < count; i++) {
            Map<String, Object> kv = new HashMap<>(columnKeys.size());
            for (String key : columnKeys) {
                List rows = columnMap.get(key).getValue();
                kv.put(key, rows.get(i));
            }
            kvs.add(kv);
        }
        return new ResultSetScreenCondit(select, this).screen(kvs);
    }

    public int executeInsert() {
        return 0;
    }
}
