package cn.xianbin.queryengine.expression.function;


import cn.xianbin.queryengine.expression.ExecutableExpression;
import cn.xianbin.queryengine.expression.column.AbstractColumn;

import java.sql.SQLException;

public class Min implements ExecutableExpression {

    @Override
    public String eval(AbstractColumn columnList) throws Exception {
        if (columnList == null) {
            throw new SQLException("invalid column size");
        }
        return String.format("MIN(%s)", columnList.getId());
    }
}
