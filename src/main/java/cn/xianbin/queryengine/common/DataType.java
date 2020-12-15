package cn.xianbin.queryengine.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DataType {


    NUMBER(1, "Int64", "整数"),
    STRING(2, "String", "字符串"),
    LIST(3, "Array(String)", "集合"),
    DATE(4, "Date", "日期"),
    DATETIME(5, "DateTime64(3,'Asia/Shanghai')", "时间"),
    BOOL(6, "Int8", "布尔"),
    DOUBLE(7, "Float64", "浮点数"),
    UNKNOWN(0, "UNKNOWN", "null");

    private Integer index;
    private String clickHouseType;
    private String typeName;

    private static DataType[] indexArray;

    static {
        DataType.indexArray = new DataType[8];
        for (DataType dataType : values()) {
            DataType.indexArray[dataType.index] = dataType;
        }
    }

    public static DataType fromInt(Integer dataType) {
        if (dataType == null) {
            return null;
        }
        if (dataType >= 1 && dataType <= 6) {
            return DataType.indexArray[dataType];
        }
        return DataType.UNKNOWN;
    }

    public static DataType fromName(String typeName) {
        for (DataType dataType : DataType.values()) {
            if (dataType.getTypeName().equals(typeName)) {
                return dataType;
            }
        }
        return DataType.UNKNOWN;
    }

}