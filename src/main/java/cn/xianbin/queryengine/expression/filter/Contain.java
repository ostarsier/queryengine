package cn.xianbin.queryengine.expression.filter;

import cn.xianbin.queryengine.expression.column.AbstractColumn;

public class Contain extends AbstractFilter {

    private String text;
    private boolean reverse;

    public Contain(AbstractColumn abstractColumn, Object o, boolean reverse) {
        super(abstractColumn);
        this.reverse = false;
        this.text = (String) o;
        this.reverse = reverse;
    }

    @Override
    public String constructSql() throws Exception {
        return String.format("%s %s LIKE '%%%s%%'", this.column.getId(), this.reverse ? "NOT" : "", this.text);
    }
}
