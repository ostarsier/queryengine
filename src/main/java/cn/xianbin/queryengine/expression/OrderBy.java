package cn.xianbin.queryengine.expression;


import cn.xianbin.queryengine.expression.column.AbstractColumn;

public class OrderBy implements SqlGenerator {
    private boolean isAsc;
    private int orderByIndex;
    private AbstractColumn orderByField;
    private boolean useField;

    public OrderBy(int orderByIndex, boolean isAsc) {
        this.isAsc = true;
        this.orderByIndex = 0;
        this.useField = false;
        this.orderByIndex = orderByIndex;
        this.isAsc = isAsc;
    }

    public OrderBy(AbstractColumn orderByField, boolean isAsc) {
        this.isAsc = true;
        this.orderByIndex = 0;
        this.useField = false;
        this.orderByField = orderByField;
        this.isAsc = isAsc;
        this.useField = true;
    }

    @Override
    public String constructSql() throws Exception {
        if (this.useField) {
            return String.format("%s %s ", this.orderByField.getId(), this.isAsc ? "ASC" : "DESC");
        }
        return String.format("%d %s ", this.orderByIndex, this.isAsc ? "ASC" : "DESC");
    }
}
