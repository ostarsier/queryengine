package cn.xianbin.queryengine.error.parameter;

public class FilterParamNumException extends QueryParameterException {

    public FilterParamNumException(String function, Integer expectedParamsNum) {
        super(QueryParameterErrorType.FILTER_PARAMETER_NUM_INVALID);
        this.addErrorAttr("function", function);
        this.addErrorAttr("expectedParamsNum", expectedParamsNum);
    }

}
