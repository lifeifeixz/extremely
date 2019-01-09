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

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;

/**
 * @author flysLi
 * @ClassName Analysis
 * @Decription TODO
 * @Date 2019/1/8 15:25
 * @Version 1.0
 */
public class Analysis {
    private String sql;
    private String doType;
    private SQLStatement sqlStatement;

    public Analysis(String sql) {
        this.sql = sql;
        SQLStatementParser parser = new MySqlStatementParser(sql);
        sqlStatement = parser.parseStatement();

    }

    public static void main(String[] args) {
        new Analysis("insert into user(id,name,age,sex)values(1,'feifei',26,'男')").doer();
    }

    public Object doer() {
        if (sql.toUpperCase().indexOf("SELECT ") > -1) {
            this.doType = "SELECT";
            SQLStatementParser parser = new MySqlStatementParser(sql);
            SQLStatement statement = parser.parseStatement();
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            statement.accept(visitor);
            return new Select(visitor);
        } else if (sql.toUpperCase().indexOf("INSERT ") > -1) {
            this.doType = "INSERT";
            MySqlInsertStatement mySqlInsertStatement = (MySqlInsertStatement) sqlStatement;
            return new Insert(mySqlInsertStatement);
        }
        return null;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getDoType() {
        return doType;
    }

    public void setDoType(String doType) {
        this.doType = doType;
    }

    public SQLStatement getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(SQLStatement sqlStatement) {
        this.sqlStatement = sqlStatement;
    }
}
