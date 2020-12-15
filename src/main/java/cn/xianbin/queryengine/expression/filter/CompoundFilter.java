package cn.xianbin.queryengine.expression.filter;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class CompoundFilter extends AbstractFilter {

    private boolean isAnd;
    private List<AbstractFilter> filters;
    private boolean isNegative;

    public CompoundFilter() {
        super(null);
        this.isAnd = true;
        this.filters = new ArrayList<AbstractFilter>();
        this.isNegative = false;
    }

    public CompoundFilter(boolean isNegative) {
        super(null);
        this.isAnd = true;
        this.filters = new ArrayList<AbstractFilter>();
        this.isNegative = false;
        this.isNegative = isNegative;
    }

    public void addSubFilter(AbstractFilter abstractFilter) {
        if (abstractFilter != null) {
            this.filters.add(abstractFilter);
        }
    }

    public void addSubFilters(Collection<? extends AbstractFilter> collection) {
        this.filters.addAll(collection);
    }

    public List<AbstractFilter> getFilters() {
        return this.filters;
    }

    @Override
    public String constructSql() throws Exception {
        StringBuilder sb = new StringBuilder();
        int n = 1;
        for (AbstractFilter abstractFilter : this.filters) {
            sb.append(' ');
            String constructSql = abstractFilter.constructSql();
            if (StringUtils.isBlank(constructSql)) {
                constructSql = " 1=1 ";
            }
            if (n == 0) {
                if (this.isAnd) {
                    sb.append("AND");
                } else {
                    sb.append("OR");
                }
            }
            sb.append(' ');
            sb.append("(");
            sb.append(constructSql);
            sb.append(")");
            n = 0;
        }
        if (!this.isNegative) {
            return sb.toString();
        }
        return String.format("NOT (%s)", sb.toString());
    }

    public void setIsAnd(boolean isAnd) {
        this.isAnd = isAnd;
    }

    @Override
    public Stream<AbstractFilter> stream() {
        return Stream.concat((Stream<? extends AbstractFilter>) Stream.of(this), this.filters.stream().flatMap(AbstractFilter::stream));
    }
}
