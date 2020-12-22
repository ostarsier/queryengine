package cn.xianbin.queryengine.table;

import cn.xianbin.queryengine.expression.Limit;
import cn.xianbin.queryengine.expression.OrderBy;
import cn.xianbin.queryengine.expression.SqlGenerator;
import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.expression.filter.AbstractFilter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Data
public class Table implements SqlGenerator, Serializable {

    protected String tableName;
    protected List<AbstractColumn> columns = new ArrayList<>();
    private AbstractFilter filter;
    private List<String> groupByList;
    private List<OrderBy> orderByList;
    protected Limit limit;

    public Table(String tableName) {
        this.tableName = tableName;
    }

    protected String constructWhere() throws Exception {
        return filter.constructSql();
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
            sb.append(groupBySql);
        }

        if (this.orderByList != null) {
            sb.append("\n\t");
            sb.append("ORDER BY ");
            StringJoiner orderBySql = new StringJoiner(",");
            for (OrderBy orderBy : orderByList) {
                orderBySql.add(orderBy.constructSql());
            }
            sb.append(orderBySql);
        }
        if (this.limit != null) {
            sb.append("\n\t");
            sb.append(limit.constructSql());
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
