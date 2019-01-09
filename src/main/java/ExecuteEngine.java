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

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import data.DataBase;
import data.DataEngine;
import data.Table;
import data.protocol.Analysis;
import data.protocol.Insert;
import data.protocol.Select;


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
        DataBase dataBase = engine.getDatabase("daas");
        Analysis analysis = new Analysis(sql);
        analysis.doer();
        String doType = analysis.getDoType();
        if ("INSERT".equals(doType)) {
            Insert insert = (Insert) analysis.doer();
            String tableName = insert.getTable();
            Table table = dataBase.getTable(tableName);
            if (table == null) {
                table = dataBase.create(tableName);
            }
            return table.insert(insert.getKv());
        } else if ("SELECT".equals(doType)) {
            //todo 查询所有数据，测试
            Select select = (Select) analysis.doer();
            String tableName = select.getTable();
            Table table = dataBase.getTable(tableName);
            return table.select(select);
        } else {
            return "[Error]:SQL Grammatical abnormality!";
        }
    }


}
