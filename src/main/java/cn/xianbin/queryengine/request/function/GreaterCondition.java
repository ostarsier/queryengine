package cn.xianbin.queryengine.request.function;

import cn.xianbin.queryengine.error.parameter.FilterParamNumException;
import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.expression.filter.AbstractFilter;
import cn.xianbin.queryengine.expression.filter.Greater;
import cn.xianbin.queryengine.request.Parser;

import java.util.List;

@FilterFunction(name = {"greater", "greaterequal", "least"})
public class GreaterCondition extends FunctionCondition {

    public GreaterCondition(String field, String function, List<Object> params) {
        super(field, function, params);
    }

    @Override
    public void parse(Parser parser) throws Exception {
        if (this.params.size() != 1) {
            throw new FilterParamNumException(this.function, 1);
        }
    }

    @Override
    public AbstractFilter createFilter(AbstractColumn abstractColumn) {
        if (this.function.equalsIgnoreCase("greaterequal") || this.function.equalsIgnoreCase("least")) {
            return new Greater(abstractColumn, true, this.params.get(0));
        }
        return new Greater(abstractColumn, this.params.get(0));
    }
}
