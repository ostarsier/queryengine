package cn.xianbin.queryengine.error;

public abstract class QueryEngineException extends RuntimeException {

    public QueryEngineException(String message) {
        super(message);
    }

    QueryEngineException(Exception ex) {
        super(ex);
    }
}
