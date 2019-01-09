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
package data.result;

import data.Table;
import data.protocol.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 分组
 *
 * @author flysLi
 * @ClassName ResultSetScreenGroup
 * @Decription TODO
 * @Date 2019/1/9 15:37
 * @Version 1.0
 */
public class ResultSetScreenGroup
        extends AbstractResultSetScreen
        implements ResultSetScreen {

    public ResultSetScreenGroup(Select select, Table table) {
        this.select = select;
        this.table = table;
    }

    @Override
    public List<Map<String, Object>> screen(List<Map<String, Object>> resultSet) {
        List<Map<String, Object>> newCore = null;
        Set<String> group = select.getGroupColumn();
        if (resultSet != null && resultSet.size() > 0) {
            if (group != null) {
                newCore = new ArrayList<>();
                for (String g : group) {
                    for (Map<String, Object> result : resultSet) {
                        Object o = result.get(g);
                        if (!newCore.contains(o)) {
                            newCore.add(result);
                        }
                    }
                }

            } else {
                return resultSet;
            }
        }
        return newCore;
    }
}
