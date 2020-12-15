package cn.xianbin.queryengine.table;

import cn.xianbin.queryengine.expression.Limit;
import cn.xianbin.queryengine.expression.SqlGenerator;
import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.expression.column.ExpressionColumn;
import cn.xianbin.queryengine.expression.filter.AbstractFilter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Data
public class AbstractTable implements SqlGenerator, Serializable {

    protected String tableName;
    private String tableAlias;
    protected List<AbstractColumn> columns = new ArrayList<>();
    protected Limit limit;
    private List<String> groupByList;
    private Boolean orderByAsc;
    private List<AbstractFilter> filters;

    private List<Pair<AbstractTable, JoinType>> joinTables;
    private List<List<SqlGenerator>> joinConditions;
    private List<ExpressionColumn> expressionColumns;

    public enum JoinType {
        INNER,
        LEFT,
        RIGHT,
        FULL;
    }

    public AbstractTable(String tableName, String tableAlias) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    protected String constructWhere() throws Exception {
        StringJoiner sql = new StringJoiner(" AND ");
        for (AbstractFilter andFilter : filters) {
            sql.add(andFilter.constructSql());
        }
        return sql.toString();
    }

    public void addSelect(AbstractColumn field) {
        this.columns.add(field);
    }

    @Override
    public String constructSql() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(columnSql());

        sb.append("\n\tFROM ");
        sb.append(this.getTableName());
        sb.append(" ");

        String whereSql = this.constructWhere();
        if (whereSql.length() > 0) {
            sb.append("\n\tWHERE ");
            sb.append(whereSql);
        }

        if (this.groupByList != null) {
            sb.append("\n\t");
            sb.append("GROUP BY ");
            StringJoiner groupBySql = new StringJoiner(",");
            for (String groupBy : groupByList) {
                groupBySql.add("`" + groupBy + "`");
            }
        }
        return sb.toString();
    }

    private StringJoiner columnSql() throws Exception {
        StringJoiner columnSql = new StringJoiner(",");
        for (AbstractColumn column : this.getColumns()) {
            columnSql.add(column.constructSql());
        }
        return columnSql;
    }

}
