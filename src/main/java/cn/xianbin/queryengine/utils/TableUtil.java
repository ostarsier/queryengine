package cn.xianbin.queryengine.utils;


import cn.xianbin.queryengine.common.AggregatorType;
import cn.xianbin.queryengine.error.parameter.QueryParameterErrorType;
import cn.xianbin.queryengine.error.parameter.QueryParameterException;
import cn.xianbin.queryengine.expression.column.AtomColumn;
import cn.xianbin.queryengine.expression.column.ExpressionColumn;
import cn.xianbin.queryengine.expression.function.Max;
import cn.xianbin.queryengine.request.element.RequestElementMeasure;

public class TableUtil {

    public static ExpressionColumn constructAggregation(RequestElementMeasure measure, String alias) {
        AggregatorType aggregatorType = AggregatorType.fromName(measure.getAggregator());
        AtomColumn column = new AtomColumn(measure.getField(), alias);
        switch (aggregatorType) {
            case MAX: {
                return new ExpressionColumn(alias, new Max(), column);
            }
            default: {
                throw QueryParameterException.of(QueryParameterErrorType.UNSUPPORTED_MEASURE_TYPE);
            }
        }
    }

}
