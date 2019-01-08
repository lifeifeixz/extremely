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

import java.util.ArrayList;
import java.util.List;

/**
 * @author flysLi
 * @ClassName Field
 * @Decription TODO
 * @Date 2019/1/7 15:55
 * @Version 1.0
 */
public class Field<T> {
    private String name;
    private String type;
    private int maxLength;
    private List<T> value;

    public Field() {
        this.value = new ArrayList<T>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public List<T> getValue() {
        return value;
    }

    public void setValue(List<T> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", maxLength=" + maxLength +
                ", value=" + value +
                '}';
    }
}
