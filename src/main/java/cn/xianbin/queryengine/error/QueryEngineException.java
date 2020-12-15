package cn.xianbin.queryengine.error;

public abstract class QueryEngineException extends RuntimeException {
    public QueryEngineException(String s) {
        super(s);
    }

    QueryEngineException(Exception ex) {
        super(ex);
    }
}
