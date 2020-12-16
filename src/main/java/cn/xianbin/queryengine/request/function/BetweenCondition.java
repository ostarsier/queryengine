

package cn.xianbin.queryengine.request.function;


import cn.xianbin.queryengine.error.parameter.FilterParamNumException;
import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.expression.filter.AbstractFilter;
import cn.xianbin.queryengine.expression.filter.Between;

import java.util.List;

@FilterFunction(name = {"between"})
public class BetweenCondition extends FunctionCondition {
    public static String BETWEEN = "between";

    public BetweenCondition(String field, String function, List<Object> params) {
        super(field, function, params);
    }

    @Override
    public void parse() throws Exception {
        if (this.params.size() != 2) {
            throw new FilterParamNumException(this.function, 2);
        }
    }

    @Override
    public AbstractFilter createFilter(AbstractColumn abstractColumn) {
        return new Between(abstractColumn, this.params.get(0), this.params.get(1));
    }
}
