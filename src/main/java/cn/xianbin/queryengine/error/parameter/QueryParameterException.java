package cn.xianbin.queryengine.error.parameter;


import cn.xianbin.queryengine.error.QueryEngineException;

import java.util.HashMap;
import java.util.Map;

public class QueryParameterException extends QueryEngineException {
    private QueryParameterErrorType errorType;
    private Map<String, Object> errorAttrs;

    protected QueryParameterException(QueryParameterErrorType errorType) {
        super(errorType.toString());
        this.errorAttrs = new HashMap<String, Object>();
        this.errorType = errorType;
    }

    public static QueryParameterException of(QueryParameterErrorType queryParameterErrorType) {
        return new QueryParameterException(queryParameterErrorType);
    }

    public static QueryParameterException of(QueryParameterErrorType queryParameterErrorType, String s, Object o) {
        QueryParameterException ex = new QueryParameterException(queryParameterErrorType);
        ex.addErrorAttr(s, o);
        return ex;
    }

    void addErrorAttr(String s, Object o) {
        this.errorAttrs.put(s, o);
    }

    private String a() {
        if (this.errorAttrs == null || this.errorAttrs.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : this.errorAttrs.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    public QueryParameterErrorType getErrorType() {
        return this.errorType;
    }

    @Override
    public String getMessage() {
        String a = this.a();
        if (a.isEmpty()) {
            return this.errorType.toString();
        }
        return String.format("%s(%s)", this.errorType.toString(), a);
    }
}
