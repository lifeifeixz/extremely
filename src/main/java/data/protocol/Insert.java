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

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author flysLi
 * @ClassName Insert
 * @Decription TODO
 * @Date 2019/1/8 16:21
 * @Version 1.0
 */
public class Insert {
    private String table;
    private List<Kv> kv;

    public Insert(MySqlInsertStatement statement) {
        table = statement.getTableName().getSimpleName();
        kv = new ArrayList<>(10);
        List<SQLExpr> exprs = statement.getColumns();
        SQLInsertStatement.ValuesClause clause = statement.getValues();
        List<SQLExpr> values = clause.getValues();
        for (int i = 0; i < exprs.size(); i++) {
            SQLExpr e = exprs.get(i);
            SQLExpr v = values.get(i);
            System.out.println(v.toString());
            kv.add(new Kv(e.toString(), v.toString()));
        }
    }

    public List<Kv> getKv() {
        return kv;
    }

    public String getTable() {
        return table;
    }
}

