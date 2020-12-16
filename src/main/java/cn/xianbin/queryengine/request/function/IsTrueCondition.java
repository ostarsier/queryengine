package cn.xianbin.queryengine.request.function;


import cn.xianbin.queryengine.error.parameter.FilterParamNumException;
import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.expression.filter.AbstractFilter;
import cn.xianbin.queryengine.expression.filter.IsTrue;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

@FilterFunction(name = {"istrue"})
public class IsTrueCondition extends FunctionCondition {

    public IsTrueCondition(String field, String function, List<Object> params) {
        super(field, function, params);
    }

    @Override
    public void parse() throws Exception {
        if (CollectionUtils.isNotEmpty(this.params)) {
            throw new FilterParamNumException(this.function, 0);
        }
    }

    @Override
    public AbstractFilter createFilter(AbstractColumn abstractColumn) {
        return new IsTrue(abstractColumn);
    }
}
