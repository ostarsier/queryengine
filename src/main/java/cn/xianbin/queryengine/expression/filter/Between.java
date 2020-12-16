package cn.xianbin.queryengine.expression.filter;

import cn.xianbin.queryengine.expression.column.AbstractColumn;

public class Between extends AbstractFilter {

    private Object lowerBound;
    private Object upperBound;

    public Between(AbstractColumn abstractColumn, Object lowerBound, Object upperBound) {
        super(abstractColumn);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public String constructSql() throws Exception {
        String id = this.column.getId();
        if (this.lowerBound instanceof Number) {
            return String.format("%s BETWEEN %s AND %s", id, this.lowerBound, this.upperBound);
        }
        return String.format("%s BETWEEN '%s' AND '%s'", id, this.lowerBound, this.upperBound);
    }
}
