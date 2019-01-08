package data;/*
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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author flysLi
 * @ClassName dataEngine.DataEngine
 * @Decription TODO
 * @Date 2019/1/7 15:51
 * @Version 1.0
 */
public class DataEngine {
    private Map<String, DataBase> dataBases = new ConcurrentHashMap<>(2);
    private static DataEngine dataEngine = null;

    private DataEngine() {

    }

    public static DataEngine getInstance() {
        synchronized (DataEngine.class) {
            if (dataEngine == null) {
                dataEngine = new DataEngine();
            }
        }
        return dataEngine;
    }

    public DataBase createDatabase(String name) {
        DataBase dataBase = new DataBase();
        dataBase.setDataBaseName(name);
        dataBases.put(name, dataBase);
        return dataBase;
    }

    public DataBase getDatabase(String dbName) {
        return dataBases.get(dbName);
    }

    public static void main(String[] args) {
        DataEngine engine = getInstance();
        DataBase dataBase = engine.createDatabase("daas");
        Table table = dataBase.create("product");
        table.addField("id", FieldType.STRING.name());
        table.addField("name", FieldType.STRING.name());

        /*查询已经创建的数据*/
        System.out.println("----find----");
        Table product = dataBase.getTable("product");
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("id", "091412321");
        map.put("name", "澳龙1");
        product.insert(map);
        System.out.println(product.toString());
    }

}
