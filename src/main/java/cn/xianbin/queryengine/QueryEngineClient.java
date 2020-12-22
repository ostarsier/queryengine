package cn.xianbin.queryengine;

import cn.xianbin.queryengine.expression.AliasGenerator;
import cn.xianbin.queryengine.expression.GroupBy;
import cn.xianbin.queryengine.expression.Limit;
import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.expression.column.AtomColumn;
import cn.xianbin.queryengine.expression.column.ExpressionColumn;
import cn.xianbin.queryengine.expression.filter.CompoundFilter;
import cn.xianbin.queryengine.request.QueryRequest;
import cn.xianbin.queryengine.request.element.RequestElementCondition;
import cn.xianbin.queryengine.request.element.RequestElementFilter;
import cn.xianbin.queryengine.table.Table;
import cn.xianbin.queryengine.utils.FilterUtil;
import cn.xianbin.queryengine.utils.TableUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;

public class QueryEngineClient {

    public String sql(QueryRequest request) throws Exception {
        AliasGenerator aliasGenerator = new AliasGenerator();
        Table table = new Table(request.getTable());

        for (GroupBy groupBy : request.getGroupByList()) {
            String alias = groupBy.getAlias();
            if (StringUtils.isBlank(alias)) {
                alias = aliasGenerator.nextAlias();
            }
            table.addSelect(new AtomColumn(groupBy.getField(), alias));
        }

        request.getMeasures().forEach(measure -> {
            String alias = measure.getAlias();
            if (StringUtils.isBlank(alias)) {
                alias = aliasGenerator.nextAlias();
            }
            ExpressionColumn expressionColumn = TableUtil.constructAggregation(measure, alias);
            table.addSelect(expressionColumn);
        });

        RequestElementFilter filter = request.getFilter();
        CompoundFilter compoundFilter = new CompoundFilter();
        compoundFilter.setIsAnd(filter.isAnd());
        for (RequestElementCondition condition : filter.getConditions()) {
            AbstractColumn column = new AtomColumn(condition.getField(), aliasGenerator.nextAlias());
            compoundFilter.addSubFilter(FilterUtil.getFunctionCondition(condition.getField(), condition.getFunction(), condition.getParams())
                    .createFilter(column));
        }
        table.setFilter(compoundFilter);
        table.setGroupByList(request.getGroupByList().stream().map(GroupBy::getField).collect(Collectors.toList()));
        table.setOrderByList(request.getOrderByList());
        table.setLimit(new Limit(request.getLimit()));

        String sql = table.constructSql();
        return sql;
    }
}
