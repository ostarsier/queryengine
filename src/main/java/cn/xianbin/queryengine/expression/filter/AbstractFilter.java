package cn.xianbin.queryengine.expression.filter;

import cn.xianbin.queryengine.expression.SqlGenerator;
import cn.xianbin.queryengine.expression.column.AbstractColumn;
import lombok.Data;

import java.io.Serializable;
import java.util.stream.Stream;

@Data
public abstract class AbstractFilter implements SqlGenerator, Serializable {

    protected AbstractColumn column;

    public AbstractFilter(AbstractColumn column) {
        this.column = column;
    }

    @Override
    public String toString() {
        try {
            return this.constructSql();
        } catch (Exception ex) {
            return "constructSql failed";
        }
    }

    public Stream<AbstractFilter> stream() {
        return Stream.of(this);
    }
}
