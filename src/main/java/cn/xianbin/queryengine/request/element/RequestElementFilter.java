package cn.xianbin.queryengine.request.element;

import cn.xianbin.queryengine.request.RequestElementValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestElementFilter implements RequestElementValidator, Serializable {

    private String relation;
    private List<RequestElementCondition> conditions;
    private List<RequestElementFilter> filters;

    @Override
    public void parse() throws Exception {
        if (CollectionUtils.isNotEmpty(this.conditions)) {
            for (RequestElementCondition condition : conditions) {
                condition.parse();
            }
        }

        if (CollectionUtils.isNotEmpty(this.filters)) {
            for (RequestElementFilter filter : filters) {
                filter.parse();
            }
        }
    }

    @Override
    public boolean validationCheck() {
        if (StringUtils.isEmpty(this.relation)) {
            return false;
        }
        if (!this.relation.equalsIgnoreCase("and") && !this.relation.equalsIgnoreCase("or")) {
            return false;
        }
        if (CollectionUtils.isEmpty(this.conditions) && CollectionUtils.isEmpty(this.filters)) {
            return false;
        }
        if (CollectionUtils.isNotEmpty(this.filters)) {
            for (RequestElementFilter filter : filters) {
                if (!filter.validationCheck()) {
                    return false;
                }
            }
        }
        if (CollectionUtils.isNotEmpty(this.conditions)) {
            for (RequestElementCondition condition : conditions) {
                if (!condition.validationCheck()) {
                    return false;
                }
            }
        }
        this.relation = this.relation.toLowerCase();
        return true;
    }


    public boolean isAnd() {
        return StringUtils.isEmpty(this.relation) || this.relation.equalsIgnoreCase("AND");
    }

    public boolean isOr() {
        return !this.isAnd();
    }

    public RequestElementFilter deepCopy() {
        return SerializationUtils.clone(this);
    }

    public void addFilter(RequestElementFilter requestElementFilter) {
        if (isValid(requestElementFilter)) {
            if (this.filters == null) {
                this.filters = new ArrayList<RequestElementFilter>();
            }
            this.filters.add(requestElementFilter);
        }
    }

    public static boolean isValid(RequestElementFilter requestElementFilter) {
        return !isInvalid(requestElementFilter);
    }

    public static boolean isInvalid(RequestElementFilter requestElementFilter) {
        return requestElementFilter == null || (CollectionUtils.isEmpty(requestElementFilter.getFilters()) && CollectionUtils.isEmpty(requestElementFilter.getConditions()));
    }


}
