package cn.xianbin.queryengine.expression.filter;


import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.utils.DataUtil;

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
        Serializable richValue = DataUtil.richValue(this.value);
        return String.format("%s %s %s", super.getColumn().getId(), this.operator, richValue);
    }

}
