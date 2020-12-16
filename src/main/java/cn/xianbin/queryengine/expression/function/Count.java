package cn.xianbin.queryengine.expression.function;

import cn.xianbin.queryengine.expression.ExecutableExpression;
import cn.xianbin.queryengine.expression.column.AbstractColumn;

public class Count implements ExecutableExpression {

    private boolean distinct;
    private ExecutableExpression expression;

    public Count(boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public String eval(AbstractColumn column) throws Exception {
        if (column == null) {
            return "COUNT(1)";
        }
        if (distinct) {
            return String.format("COUNT (DISTINCT %s)", column.getId());
        } else {
            return String.format("COUNT (%s)", column.getId());
        }
    }

}
