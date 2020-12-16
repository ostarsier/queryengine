

package cn.xianbin.queryengine.request.function;


import cn.xianbin.queryengine.error.parameter.FilterParamNumException;
import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.expression.filter.AbstractFilter;
import cn.xianbin.queryengine.expression.filter.Contain;

import java.util.List;

@FilterFunction(name = {"contain", "notcontain"})
public class ContainCondition extends FunctionCondition {

    public ContainCondition(String field, String function, List<Object> params) {
        super(field, function, params);
    }

    @Override
    public void parse() throws Exception {
        if (this.params.size() != 1) {
            throw new FilterParamNumException(this.function, 1);
        }
    }

    @Override
    public AbstractFilter createFilter(AbstractColumn abstractColumn) {
        if (this.function.equalsIgnoreCase("contain")) {
            return new Contain(abstractColumn, this.params.get(0), false);
        }
        return new Contain(abstractColumn, this.params.get(0), true);
    }
}
