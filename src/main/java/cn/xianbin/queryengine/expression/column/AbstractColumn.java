package cn.xianbin.queryengine.expression.column;


import cn.xianbin.queryengine.expression.SqlGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class AbstractColumn implements SqlGenerator {

    protected String alias;

    /**
     * 字段名或表达式
     */
    public abstract String getId() throws Exception;

    @Override
    public String constructSql() throws Exception {
        return String.format(" %s as %s ", this.getId(), this.alias);
    }

}
