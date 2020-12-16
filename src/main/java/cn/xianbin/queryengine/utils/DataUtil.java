package cn.xianbin.queryengine.utils;

import java.io.Serializable;
import java.util.Date;

public class DataUtil {

    public static String richValue(Object value) {
        Serializable richValue;
        if (value instanceof Number) {
            richValue = value.toString();
        } else if (value instanceof Date) {
            richValue = ((Date) value).getTime();
        } else {
            richValue = String.format("'%s'", value);
        }
        return richValue.toString();
    }

}
