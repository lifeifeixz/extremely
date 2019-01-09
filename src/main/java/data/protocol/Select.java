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
package data.protocol;

import com.alibaba.druid.sql.dialect.mysql.ast.clause.MySqlSelectIntoStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;

import java.util.*;

/**
 * @author flysLi
 * @ClassName Select
 * @Decription TODO
 * @Date 2019/1/8 15:35
 * @Version 1.0
 */
public class Select {
    private String table;
    private Map<String, String> viewColumn;
    private List<Condition> conditions;
    private Set<String> groupColumn;
    private Set<String> orderColumn;

    public Select(MySqlSchemaStatVisitor visitor) {
        Map<TableStat.Name, TableStat> statMap = visitor.getTables();
        conditions = convent(visitor.getConditions());
        groupColumn = convent(visitor.getGroupByColumns());
        orderColumn = convent(visitor.getGroupByColumns());
        for (TableStat.Name name : statMap.keySet()) {
            this.table = name.getName();
        }
    }

    protected Set<String> convent(Set<TableStat.Column> columns) {
        Set<String> keys = null;
        if (columns != null && columns.size() > 0) {
            keys = new HashSet<>();
            for (TableStat.Column column : columns) {
                keys.add(column.getName());
            }
        }
        return keys;
    }

    protected List<Condition> convent(List<TableStat.Condition> conditions) {
        List<Condition> conditions1 = null;
        if (conditions != null && conditions.size() > 0) {
            conditions1 = new ArrayList<>();
            for (TableStat.Condition condition : conditions) {
                Condition c = new Condition();
                c.setColumnName(condition.getColumn().getName());
                c.setOperator(condition.getOperator());
                c.setValues(condition.getValues());
                c.setDataType(condition.getColumn().getDataType());
                conditions1.add(c);
            }
        }
        return conditions1;
    }

    public String getTable() {
        return table;
    }

    public Map<String, String> getViewColumn() {
        return viewColumn;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public Set<String> getGroupColumn() {
        return groupColumn;
    }

    public Set<String> getOrderColumn() {
        return orderColumn;
    }

    public static void main(String[] args) {
    }
}
