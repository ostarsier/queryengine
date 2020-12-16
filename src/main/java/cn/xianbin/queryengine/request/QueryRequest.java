package cn.xianbin.queryengine.request;


import cn.xianbin.queryengine.request.element.RequestElementFilter;
import cn.xianbin.queryengine.request.element.RequestElementMeasure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryRequest implements RequestElementValidator, Serializable {

    private List<RequestElementMeasure> measures;
    private String table;
    private RequestElementFilter filter;
    private List<String> groupByList;
    private List<Integer> orderByList;
    private Long limit;

    @Override
    public void parse() throws Exception {
        if (CollectionUtils.isNotEmpty(this.measures)) {
            for (RequestElementMeasure measure : this.measures) {
                measure.parse();
            }
        }
        if (this.filter != null) {
            this.filter.parse();
        }
    }

}
