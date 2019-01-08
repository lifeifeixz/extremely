import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.alibaba.druid.stat.TableStat;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        String sql = "select (select userId from dept order by userId) from user " +
                "where id=121 and name='flysLi' group by name order by age desc;";
        long t = System.currentTimeMillis();
        SQLStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement statement = parser.parseStatement();
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        statement.accept(visitor);
        System.out.println("----子查询-----");
        List<SQLObject> children = statement.getChildren();
        for (SQLObject object : children) {
            SQLStatementParser childParser = new MySqlStatementParser(sql);
            SQLStatement cStatement = childParser.parseStatement();
            MySqlSchemaStatVisitor sqlastVisitor = new MySqlSchemaStatVisitor();
            object.accept(sqlastVisitor);
            System.out.println(sqlastVisitor.getColumns());


            System.out.println(object);
        }
        System.out.println("----table----");
        Map<TableStat.Name, TableStat> tables = visitor.getTables();
        Set<TableStat.Name> keys = tables.keySet();
        for (TableStat.Name name : keys) {
            System.out.println(name.getName());
            TableStat tableStat = tables.get(name);
            System.out.println(tableStat.getDropCount());
            tableStat.getInsertCount();
            System.out.println("查询次数:" + tableStat.getSelectCount());
        }

        System.out.println("------cloum-------");
        Collection<TableStat.Column> columns = visitor.getColumns();
        Iterator<TableStat.Column> iterator = columns.iterator();
        while (iterator.hasNext()) {
            TableStat.Column column = iterator.next();
            System.out.println(column.getName() + "\t" + column.isWhere());
        }

        System.out.println("-----条件--------");
        List<TableStat.Condition> conditions = visitor.getConditions();
        for (TableStat.Condition condition : conditions) {
            List<Object> objects = condition.getValues();
            TableStat.Column column = condition.getColumn();
            System.out.print(column.getName() + "\t");
            System.out.print(condition.getOperator() + "\t");
            for (Object o : objects) {
                System.out.print(o + ",");
            }
        }
        System.out.println();
        System.out.println("-----------排序字段---------");
        List<TableStat.Column> orderColumns = visitor.getOrderByColumns();
        System.out.println();
        for (TableStat.Column column : orderColumns) {
            System.out.print(column);

        }

        System.out.println();
        System.out.println("-----------分组字段---------");
        Set<TableStat.Column> groupColumns = visitor.getGroupByColumns();
        for (TableStat.Column column : groupColumns) {
            System.out.print(column.getName());
        }
        long e = System.currentTimeMillis();
        System.out.println("共耗时: " + (e - t));

    }
}
