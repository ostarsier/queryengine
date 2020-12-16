package cn.xianbin.queryengine.expression.function;

import cn.xianbin.queryengine.expression.ExecutableExpression;
import cn.xianbin.queryengine.expression.column.AbstractColumn;

import java.sql.SQLException;

public class Sum implements ExecutableExpression {

    @Override
    public String eval(AbstractColumn column) throws Exception {
        if (column == null) {
            throw new SQLException("invalid column size");
        }
        return String.format("SUM(%s)", column.getId());
    }

}
