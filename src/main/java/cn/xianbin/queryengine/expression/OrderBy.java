package cn.xianbin.queryengine.expression;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBy implements SqlGenerator {

    private Object field;
    private boolean isAsc;

    @Override
    public String constructSql() throws Exception {
        return String.format("%s %s ", field, this.isAsc ? "asc" : "desc");
    }
}
