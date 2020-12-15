package cn.xianbin.queryengine.expression.column;


import cn.xianbin.queryengine.expression.ExecutableExpression;

public class ExpressionColumn extends AbstractColumn {

    protected ExecutableExpression expression;
    protected AbstractColumn column;


    public ExpressionColumn(String alias, ExecutableExpression expression, AbstractColumn column) {
        super(alias);
        this.expression = expression;
        this.column = column;
    }

    @Override
    public String getId() throws Exception {
        return this.expression.eval(this.column);
    }

}
