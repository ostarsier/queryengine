package cn.xianbin.queryengine.expression.column;

import lombok.Data;

@Data
public class AtomColumn extends AbstractColumn {

    private String name;

    public AtomColumn(String name, String alias) {
        super(alias);
        this.name = name;
    }


    @Override
    public String getId() throws Exception {
        return this.name;
    }

}
