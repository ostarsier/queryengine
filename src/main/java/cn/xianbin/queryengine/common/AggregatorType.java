package cn.xianbin.queryengine.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AggregatorType {
    COUNT(0),
    AVG(1),
    MAX(2),
    MIN(3),
    SUM(4),
    UNIQ_COUNT(5),
    UNIQ_AVG(6);

    private int index;

    public static AggregatorType fromName(String aggregator) {
        String lowerCase = aggregator.toLowerCase();
        switch (lowerCase) {
            case "avg": {
                return AggregatorType.AVG;
            }
            case "max": {
                return AggregatorType.MAX;
            }
            case "min": {
                return AggregatorType.MIN;
            }
            case "sum": {
                return AggregatorType.SUM;
            }
            case "uniqcount":
            case "unique":
            case "uniq_count": {
                return AggregatorType.UNIQ_COUNT;
            }
            case "uniqavg":
            case "uniq_avg":
            case "average": {
                return AggregatorType.UNIQ_AVG;
            }
            case "count":
            default: {
                return AggregatorType.COUNT;
            }
        }
    }

    public static String fromType(AggregatorType aggregatorType) {
        switch (aggregatorType) {
            case AVG: {
                return "avg";
            }
            case MAX: {
                return "max";
            }
            case MIN: {
                return "min";
            }
            case SUM: {
                return "sum";
            }
            case UNIQ_COUNT: {
                return "uniq_count";
            }
            case UNIQ_AVG: {
                return "uniq_avg";
            }
            case COUNT:
            default: {
                return "count";
            }
        }
    }

}
