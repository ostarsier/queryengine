package cn.xianbin.queryengine.request.function;


import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.expression.filter.AbstractFilter;

import java.util.List;

public abstract class FunctionCondition {

    protected String field;
    protected String function;
    protected List<Object> params;

    public FunctionCondition(String field, String function, List<Object> params) {
        this.field = field;
        this.function = function;
        this.params = params;
    }

    public abstract void parse() throws Exception;

    public abstract AbstractFilter createFilter(AbstractColumn abstractColumn);

}
