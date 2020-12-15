package cn.xianbin.queryengine.expression;

public class Limit implements SqlGenerator {
    private long value;

    public Limit(Long n) {
        this.value = n;
    }

    @Override
    public String constructSql() throws Exception {
        return String.format("LIMIT %d", this.value);
    }
}
