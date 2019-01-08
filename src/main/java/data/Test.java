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
        table.addField("id", FieldType.STRING.name());
        table.addField("name", FieldType.STRING.name());

        /*查询已经创建的数据*/
        System.out.println("----find----");
        Table product = dataBase.getTable("product");
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("id", "091412321");
        map.put("name", "澳龙1");
        product.insert(map);

        Map<String, Object> map2 = new HashMap<String, Object>(1);
        map2.put("id", "0914123212");
        map2.put("name", "澳龙2");
        product.insert(map2);

        Map<String, Object> map3 = new HashMap<String, Object>(1);
        map3.put("id", "0914123213");
        map3.put("name", "澳龙3");
        product.insert(map3);
        System.out.println(product.toString());
    }
}
