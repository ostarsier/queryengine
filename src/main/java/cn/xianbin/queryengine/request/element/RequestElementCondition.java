package cn.xianbin.queryengine.request.element;

import cn.xianbin.queryengine.request.RequestElementValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestElementCondition implements RequestElementValidator, Serializable {

    private String field;
    private String function;
    private List<Object> params;

    @Override
    public void parse() throws Exception {
    }

    @Override
    public boolean validationCheck() {
        if (StringUtils.isBlank(this.field) || StringUtils.isBlank(this.function)) {
            return false;
        }
        if (this.params == null) {
            this.params = Collections.emptyList();
        }
        return true;
    }

}
