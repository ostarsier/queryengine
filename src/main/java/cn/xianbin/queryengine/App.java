package cn.xianbin.queryengine;

import cn.xianbin.queryengine.expression.AliasGenerator;
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
import com.alibaba.fastjson.JSONObject;

/**
 * @author xianbin.yang
 * @date 2020/12/7
 */
public class App {

    public static void main(String[] args) throws Exception {

        String requestJson = "{\"limit\":10,\"orderByList\":[1],\"groupByList\":[\"name\"],\"filter\":{\"and\":true,\"conditions\":[{\"field\":\"age\",\"function\":\"equal\",\"params\":[20]}]},\"measures\":[{\"aggregator\":\"count\",\"field\":\"age\"}],\"table\":\"person\"}";
        QueryRequest request = JSONObject.parseObject(requestJson, QueryRequest.class);

        AliasGenerator aliasGenerator = new AliasGenerator();
        Table table = new Table(request.getTable());

        for (String groupBy : request.getGroupByList()) {
            table.addSelect(new AtomColumn(groupBy, aliasGenerator.nextAlias()));
        }
        request.getMeasures().forEach(measure -> {
            ExpressionColumn expressionColumn = TableUtil.constructAggregation(measure, aliasGenerator.nextAlias());
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
        table.setGroupByList(request.getGroupByList());
        table.setOrderByList(request.getOrderByList());
        table.setLimit(new Limit(request.getLimit()));
        System.out.println(">>>" + table.constructSql());
    }
}
