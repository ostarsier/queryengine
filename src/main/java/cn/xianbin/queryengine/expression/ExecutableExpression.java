package cn.xianbin.queryengine.expression;


import cn.xianbin.queryengine.expression.column.AbstractColumn;

import java.io.Serializable;

public interface ExecutableExpression extends Serializable {

    String eval(AbstractColumn column) throws Exception;

}
