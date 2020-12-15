package cn.xianbin.queryengine.expression;

import java.io.Serializable;

public interface SqlGenerator extends Serializable {

    String constructSql() throws Exception;

}
