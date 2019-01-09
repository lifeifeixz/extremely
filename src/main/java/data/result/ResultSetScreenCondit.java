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
import data.protocol.Condition;
import data.protocol.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author flysLi
 * @ClassName ResultSetScreenCondit
 * @Decription TODO
 * @Date 2019/1/9 14:20
 * @Version 1.0
 */
@SuppressWarnings("all")
public class ResultSetScreenCondit
        extends AbstractResultSetScreen
        implements ResultSetScreen {
    ResultSetScreenGroup group;

    public ResultSetScreenCondit(Select select, Table table) {
        this.select = select;
        this.table = table;
        group = new ResultSetScreenGroup(select, table);
    }

    @Override
    public List<Map<String, Object>> screen(List<Map<String, Object>> resultSet) {
        List<Condition> conditions = select.getConditions();
        List<Map<String, Object>> newResultSet = null;
        int len = resultSet.size();
        //如果条件存在，将遍历条件
        if (select.getConditions() != null) {
            newResultSet = new ArrayList<>();
            Map<String, Object> row = null;
            for (int i = 0; i < len; i++) {
                row = resultSet.get(i);
                boolean flag = conditsSetUp(conditions, row);
                if (flag) {
                    newResultSet.add(row);
                }
            }
            return group.screen(newResultSet);
        } else {
            return group.screen(resultSet);
        }
    }

    public boolean conditsSetUp(List<Condition> conditions, Map<String, Object> row) {
        int flag = 0;
        for (Condition c : conditions) {
            String cName = c.getColumnName();
            Object cVal = c.getValues().get(0);
            String cOperator = c.getOperator();
            boolean var1 = conditSetUp(cVal, cOperator, row.get(cName));
            if (!var1) {
                flag = flag - 1;

            }
        }
        if (flag == 0) {
            System.out.println("ture");
        }
        return flag == 0 ? true : false;
    }

    /**
     * 验证条件是否成立
     *
     * @param o1
     * @param operator
     * @param o2
     * @return
     */
    public boolean conditSetUp(Object o1, String operator, Object o2) {
        boolean falg = false;
        if (operator.equals("=")) {
            if (o1.equals(o2)) {
                falg = true;
            }
        } else if (operator.equals("!=")) {
            if (!o1.equals(o2)) {
                falg = true;
            }
        }
        return falg;
    }

    public List<Map<String, Object>> findByCondit(Condition condition) {
        List<Map<String, Object>> result = new ArrayList<>();
        String cname = condition.getColumnName();
        Object cval = condition.getValues().get(0);
        String coperator = condition.getOperator();
        List<Object> objects = table.getColumn(cname);
        for (int i = 0; i < objects.size(); i++) {
            Object v1 = objects.get(i);
            if (coperator.equals("=")) {
                if (v1.equals(cval)) {
                    result.add(table.index(i));
                }
            }

            if (coperator.equals("!=")) {
                if (!objects.equals(cval)) {
                    result.add(table.index(i));
                }
            }
        }
        this.resultSet = result;
        return result;
    }
}
