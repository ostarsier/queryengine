package cn.xianbin.queryengine;

import cn.xianbin.queryengine.request.QueryRequest;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) throws Exception {

        /**
         * 自动生成别名、order by用数字下标
         */
//        String requestJson = "{\"limit\":10,\"orderByList\":[{\"field\":\"2\",\"isAsc\":false}],\"groupByList\":[{\"field\":\"name\"}],\"filter\":{\"and\":true,\"conditions\":[{\"field\":\"age\",\"function\":\"greaterequal\",\"params\":[20]}]},\"measures\":[{\"aggregator\":\"count\",\"field\":\"age\"}],\"table\":\"student\"}";

        /**
         * 自定义别名, order by用别名
         */
        String requestJson = "{\"limit\":10,\"orderByList\":[{\"field\":\"ageAlias\",\"isAsc\":false}],\"groupByList\":[{\"field\":\"name\",\"alias\":\"nameAlias\"}],\"filter\":{\"and\":true,\"conditions\":[{\"field\":\"age\",\"function\":\"greaterequal\",\"params\":[20]}]},\"measures\":[{\"aggregator\":\"count\",\"field\":\"age\",\"alias\":\"ageAlias\"}],\"table\":\"student\"}";

        QueryRequest request = JSONObject.parseObject(requestJson, QueryRequest.class);
        QueryEngineClient client = new QueryEngineClient();
        String sql = client.sql(request);
        log.info("sql:\n{}", sql);
    }

}
