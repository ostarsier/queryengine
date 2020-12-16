package cn.xianbin.queryengine.request.element;

import cn.xianbin.queryengine.common.AggregatorType;
import cn.xianbin.queryengine.request.RequestElementValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestElementMeasure implements RequestElementValidator {

    private String field;
    private String aggregator;
    private List<Object> params;

    @Override
    public void parse() throws Exception {
        if (StringUtils.isNotEmpty(this.aggregator)) {
            this.aggregator = AggregatorType.fromName(this.aggregator).name().toLowerCase();
        }
    }

}
