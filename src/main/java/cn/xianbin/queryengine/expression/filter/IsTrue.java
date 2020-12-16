package cn.xianbin.queryengine.expression.filter;

import cn.xianbin.queryengine.expression.column.AbstractColumn;

public class IsTrue extends AbstractFilter {

    public IsTrue(AbstractColumn abstractColumn) {
        super(abstractColumn);
    }

    @Override
    public String constructSql() throws Exception {
        return String.format("%s = 1", this.column.getId());
    }
}
