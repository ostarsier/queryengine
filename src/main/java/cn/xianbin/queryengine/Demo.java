package cn.xianbin.queryengine;

import cn.xianbin.queryengine.expression.AliasGenerator;
import cn.xianbin.queryengine.expression.column.AbstractColumn;
import cn.xianbin.queryengine.expression.column.AtomColumn;
import cn.xianbin.queryengine.expression.column.ExpressionColumn;
import cn.xianbin.queryengine.expression.filter.CompoundFilter;
import cn.xianbin.queryengine.request.QueryRequest;
import cn.xianbin.queryengine.request.element.RequestElementCondition;
import cn.xianbin.queryengine.request.element.RequestElementFilter;
import cn.xianbin.queryengine.table.AbstractTable;
import cn.xianbin.queryengine.utils.FilterUtil;
import cn.xianbin.queryengine.utils.TableUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

/**
 * @author xianbin.yang
 * @date 2020/12/7
 */
public class Demo {

    public static void main(String[] args) throws Exception {
//        QueryRequest request = new QueryRequest();
//
//        RequestElementMeasure measure1 = RequestElementMeasure.builder().field("age").aggregator("max").build();
//        List<RequestElementMeasure> measures = Lists.newArrayList(measure1);
//
//        RequestElementCondition condition1 = RequestElementCondition.builder().field("age").function("greater").params(Lists.newArrayList(20)).build();
//        List<RequestElementCondition> conditions = Lists.newArrayList(condition1);
//        RequestElementFilter filter = RequestElementFilter.builder().relation("and").conditions(conditions).build();
//
//        request.setTable("person");
//        request.setMeasures(measures);
//        request.setFilter(filter);
//        request.setByFields(Lists.newArrayList("name"));

        String requestJson = "{\"byFields\":[\"name\"],\"filter\":{\"and\":true,\"conditions\":[{\"field\":\"age\",\"function\":\"greater\",\"params\":[20]}]},\"measures\":[{\"aggregator\":\"max\",\"field\":\"age\"}],\"table\":\"person\"}";
        QueryRequest request = JSONObject.parseObject(requestJson, QueryRequest.class);

        AliasGenerator aliasGenerator = new AliasGenerator();
        AbstractTable table = new AbstractTable(request.getTable(), aliasGenerator.nextAlias());

        request.getMeasures().forEach(measure -> {
            ExpressionColumn expressionColumn = TableUtil.constructAggregation(measure, aliasGenerator.nextAlias());
            table.addSelect(expressionColumn);
        });

        RequestElementFilter filter1 = request.getFilter();
        CompoundFilter compoundFilter = new CompoundFilter();
        compoundFilter.setIsAnd(filter1.isAnd());
        for (RequestElementCondition condition : filter1.getConditions()) {
            AbstractColumn column = new AtomColumn(condition.getField(), aliasGenerator.nextAlias());
            compoundFilter.addSubFilter(FilterUtil.getFunctionCondition(condition.getField(), condition.getFunction(), condition.getParams())
                    .createFilter(column));
        }
        ;
        table.setFilters(Lists.newArrayList(compoundFilter));
        table.setGroupByList(request.getByFields());

        System.out.println(">>>" + table.constructSql());
    }
}
