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

    private String table;
    private List<RequestElementMeasure> measures;
    private RequestElementFilter filter;
    private List<String> byFields;
    private Long limit;

    @Override
    public void parse(final Parser parser) throws Exception {
        if (CollectionUtils.isNotEmpty(this.measures)) {
            for (RequestElementMeasure measure : this.measures) {
                measure.parse(parser);
            }
        }

        if (this.filter != null) {
            this.filter.parse(parser);
        }
    }

}
