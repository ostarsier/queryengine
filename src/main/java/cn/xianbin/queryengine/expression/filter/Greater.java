package cn.xianbin.queryengine.expression.filter;


import cn.xianbin.queryengine.expression.column.AbstractColumn;

public class Greater extends SimpleFilter {

    public Greater(AbstractColumn abstractColumn, boolean equal, Object value) {
        super(abstractColumn, ">=", value);
    }

    public Greater(AbstractColumn column, Object value) {
        super(column, ">", value);
    }

}
