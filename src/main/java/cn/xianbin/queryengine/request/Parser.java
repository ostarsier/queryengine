package cn.xianbin.queryengine.request;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class Parser {

    public static FelEngine felEngine = new FelEngineImpl();

    private List<String> allEventNames = new ArrayList();
    private List<String> allFields = new ArrayList();


    public void addEvent(String eventName) {
        this.allEventNames.add(eventName);
    }

    public void addField(String field) {
        if (StringUtils.isNotEmpty(field)) {
            this.allFields.add(field);
        }
    }


}
