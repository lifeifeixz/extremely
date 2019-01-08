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
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author flysLi
 * @ClassName Table
 * @Decription TODO
 * @Date 2019/1/7 15:54
 * @Version 1.0
 */
public class Table {
    private String name;
    private Map<String, Field> fields;

    public Table() {
        fields = new HashMap<String, Field>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        fields.put(field.getName(), field);
    }

    public Field addField(String clumm, String type) {
        Field field = new Field();
        field.setName(clumm);
        field.setType(type);
        fields.put(clumm, field);
        return field;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", fields=" + fields +
                '}';
    }

    public int insert(Map<String, Object> map) {
        Set<String> cloumns = map.keySet();
        for (String cloumn : cloumns) {
            //获取列对象
            Field field = fields.get(cloumn);
            //列数据
            List values = field.getValue();
            values.add(map.get(cloumn));
        }
        return 1;
    }
}
