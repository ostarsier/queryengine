package cn.xianbin.queryengine.expression.filter;


import cn.xianbin.queryengine.expression.column.AbstractColumn;

public class Less extends SimpleFilter {

    public Less(AbstractColumn abstractColumn, boolean equal, Object value) {
        super(abstractColumn, "<=", value);
    }

    public Less(AbstractColumn abstractColumn, Object value) {
        super(abstractColumn, "<", value);
    }
}
