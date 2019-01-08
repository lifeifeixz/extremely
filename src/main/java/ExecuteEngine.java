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

import data.DataBase;
import data.DataEngine;
import data.Field;
import data.Table;
import data.protocol.Analysis;
import data.protocol.Select;

import java.util.*;

/**
 * @author flysLi
 * @ClassName ExecuteEngine
 * @Decription TODO
 * @Date 2019/1/8 16:11
 * @Version 1.0
 */
public class ExecuteEngine {
    DataEngine engine = DataEngine.getInstance();

    public Object execute(String sql) {
        Analysis analysis = new Analysis(sql);
        List<Map<String, Object>> mapList = null;
        Select select = (Select) analysis.doer();
        DataBase dataBase = engine.getDatabase("daas");
        String tableName = select.getTable();
        if (tableName == null) {
            Table table = dataBase.getTable(tableName);
            Map<String, Field> tableFields = table.getFields();
            mapList = new ArrayList<>(1);
            List values = (ArrayList) tableFields.values();
            //获取所有字段名
            Set<String> cloumns = tableFields.keySet();
            for (int i = 0; i < values.size(); i++) {
                Map<String, Object> row = new HashMap<>(1);
                for (String cloumn : cloumns) {
                    List val = tableFields.get(cloumn).getValue();
                    row.put(cloumn, val);
                }
                mapList.add(row);
            }
        } else {
            return tableName + " 表信息不存在!";
        }

        return mapList;
    }
}
