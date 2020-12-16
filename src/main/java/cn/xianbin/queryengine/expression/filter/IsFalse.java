package cn.xianbin.queryengine.expression.filter;


import cn.xianbin.queryengine.expression.column.AbstractColumn;

public class IsFalse extends AbstractFilter {

    public IsFalse(AbstractColumn abstractColumn) {
        super(abstractColumn);
    }

    @Override
    public String constructSql() throws Exception {
        return String.format("%s = 0", this.column.getId());
    }
}
