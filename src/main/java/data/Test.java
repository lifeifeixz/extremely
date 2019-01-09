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

import java.util.HashMap;
import java.util.Map;

/**
 * @author flysLi
 * @ClassName Test
 * @Decription TODO
 * @Date 2019/1/7 16:11
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        DataEngine engine = DataEngine.getInstance();
        DataBase dataBase = engine.createDatabase("daas");
        Table table = dataBase.create("product");
        table.addColumn("id", FieldType.STRING.name());
        table.addColumn("name", FieldType.STRING.name());

        /*查询已经创建的数据*/
        System.out.println("----find----");
        Table product = dataBase.getTable("product");
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("id", "091412321");
        map.put("name", "澳龙1");
    }
}
