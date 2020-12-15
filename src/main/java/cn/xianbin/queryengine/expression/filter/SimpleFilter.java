package cn.xianbin.queryengine.expression.filter;


import cn.xianbin.queryengine.expression.column.AbstractColumn;

import java.io.Serializable;
import java.util.Date;

public class SimpleFilter extends AbstractFilter {

    protected String operator;
    protected Object value;

    public SimpleFilter(AbstractColumn column, String operator, Object value) {
        super(column);
        this.operator = operator;
        this.value = value;
    }

    @Override
    public String constructSql() throws Exception {
        Serializable richValue;
        if (this.value instanceof Number) {
            richValue = this.value.toString();
        } else if (this.value instanceof Date) {
            richValue = ((Date) this.value).getTime();
        } else {
            richValue = String.format("'%s'", this.value);
        }
        return String.format("%s %s %s", super.getColumn().getId(), this.operator, richValue);
    }

}
