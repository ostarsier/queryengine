package cn.xianbin.queryengine.request.function;

import cn.xianbin.queryengine.error.parameter.FilterParamNumException;
import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.expression.filter.AbstractFilter;
import cn.xianbin.queryengine.expression.filter.Equal;

import java.util.List;

@FilterFunction(name = {"equal", "notequal"})
public class EqualCondition extends FunctionCondition {

    public EqualCondition(String field, String function, List<Object> params) {
        super(field, function, params);
    }

    @Override
    public void parse() throws Exception {
        if (this.params.isEmpty()) {
            throw new FilterParamNumException(this.function, " at least 1");
        }
    }

    @Override
    public AbstractFilter createFilter(AbstractColumn abstractColumn) {
        if (this.function.equalsIgnoreCase("equal")) {
            return new Equal(abstractColumn, false, this.params);
        }
        return new Equal(abstractColumn, true, this.params);
    }

}
