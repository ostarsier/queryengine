package cn.xianbin.queryengine.expression.filter;

import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.utils.DataUtil;

import java.util.Collection;
import java.util.StringJoiner;

public class Equal extends AbstractFilter {

    private boolean not;
    private Collection<Object> values;

    public Equal(AbstractColumn column, boolean not, Collection<Object> values) {
        super(column);
        this.not = not;
        this.values = values;
    }

    @Override
    public String constructSql() throws Exception {
        StringJoiner values = new StringJoiner(",");
        for (Object value : this.values) {
            values.add(DataUtil.richValue(value));
        }

        if (this.values.size() > 1) {
            String opt;
            if (this.not) {
                opt = "not in";
            } else {
                opt = "in";
            }
            return String.format("%s %s (%s)", super.getColumn().getId(), opt, values);
        }

        if (this.values.size() == 1) {
            String opt;
            if (this.not) {
                opt = "!=";
            } else {
                opt = "=";
            }
            return String.format("%s %s %s", super.getColumn().getId(), opt, values);
        }

        return "false";
    }
}
