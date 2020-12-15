package cn.xianbin.queryengine.common;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum AggregatorType {
    COUNT(0),
    AVG(1),
    MAX(2),
    MIN(3),
    SUM(4),
    UNIQ_COUNT(5),
    UNIQ_AVG(6),
    BOUNCE_RATE(7),
    EXIT_RATE(8),
    UNIQ_COUNT_APPROX(9),
    COUNT_PERCENT(10),
    RANK_ASC(11),
    RANK_DESC(12),
    PERCENT_RANK_ASC(13),
    PERCENT_RANK_DESC(14),
    SESSION_COUNT(15);

    private int index;
    public static Set<String> AGGREGATOR_SET;
    public static Set<String> AGGREGATOR_CN_SET;

    private AggregatorType(int index) {
        this.index = index;
    }

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
            case "bounce_rate": {
                return AggregatorType.BOUNCE_RATE;
            }
            case "exit_rate": {
                return AggregatorType.EXIT_RATE;
            }
            case "uniq_count_approx": {
                return AggregatorType.UNIQ_COUNT_APPROX;
            }
            case "count_percent": {
                return AggregatorType.COUNT_PERCENT;
            }
            case "rank_asc": {
                return AggregatorType.RANK_ASC;
            }
            case "rank_desc": {
                return AggregatorType.RANK_DESC;
            }
            case "percent_rank_asc": {
                return AggregatorType.PERCENT_RANK_ASC;
            }
            case "percent_rank_desc": {
                return AggregatorType.PERCENT_RANK_DESC;
            }
            case "session_count": {
                return AggregatorType.SESSION_COUNT;
            }
            case "count":
            case "general":
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
            case BOUNCE_RATE: {
                return "bounce_rate";
            }
            case EXIT_RATE: {
                return "exit_rate";
            }
            case UNIQ_COUNT_APPROX: {
                return "uniq_count_approx";
            }
            case COUNT_PERCENT: {
                return "count_percent";
            }
            case SESSION_COUNT: {
                return "session_count";
            }
            case COUNT:
            default: {
                return "count";
            }
        }
    }

    public static String nameToCname(String s, String s2, String s3) {
        switch (fromName(s)) {
            case COUNT: {
                if (s3.equals("user")) {
                    return "用户总数";
                }
                if (s3.equals("event")) {
                    return "总次数";
                }
                if (s3.equals("session")) {
                    return "总数";
                }
                return "总次数";
            }
            case AVG: {
                return "均值";
            }
            case MAX: {
                return "最大值";
            }
            case MIN: {
                return "最小值";
            }
            case SUM: {
                return "总和";
            }
            case UNIQ_COUNT: {
                if (s2 == null) {
                    return "用户数";
                }
                return "去重数";
            }
            case UNIQ_AVG: {
                if (s2 == null) {
                    return "人均次数";
                }
                return "人均值";
            }
            default: {
                return null;
            }
        }
    }

    public boolean isMergeRequest(AggregatorType aggregatorType, boolean b) {
        if (b) {
            return true;
        }
        switch (aggregatorType) {
            case COUNT:
            case AVG:
            case MAX:
            case MIN:
            case SUM:
            case UNIQ_COUNT_APPROX: {
                return true;
            }
            case UNIQ_COUNT:
            case UNIQ_AVG:
            default: {
                return false;
            }
        }
    }

    public boolean isAdd(AggregatorType aggregatorType) {
        switch (aggregatorType) {
            case COUNT:
            case SUM:
            case UNIQ_COUNT_APPROX: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public Object defaultValue() {
        if (this == AggregatorType.MAX || this == AggregatorType.MIN || this == AggregatorType.AVG) {
            return null;
        }
        return 0L;
    }

    public Object defaultMiddleValue() {
        if (this == AggregatorType.UNIQ_COUNT_APPROX) {
            return null;
        }
        return this.defaultValue();
    }

    public int getIndex() {
        return this.index;
    }

    public static boolean isRank(String s) {
        return !StringUtils.isBlank(s) && fromName(s).isRank();
    }

    public boolean isRank() {
        return this == AggregatorType.RANK_ASC || this == AggregatorType.RANK_DESC;
    }

    public static boolean getRankSortType(String s) {
        String[] split = s.split("_");
        return split[split.length - 1].equalsIgnoreCase("desc");
    }

    static {
        AGGREGATOR_SET = Collections.unmodifiableSet((Set<? extends String>) new HashSet<String>(Arrays.asList("count", "general", "avg", "max", "min", "sum", "uniqcount", "unique", "uniqavg", "average", "bounce_rate", "exit_rate", "uniq_count_approx")));
        AGGREGATOR_CN_SET = Collections.unmodifiableSet((Set<? extends String>) new HashSet<String>(Arrays.asList("\u603b\u6b21\u6570", "\u603b\u6570", "\u7528\u6237\u603b\u6570", "\u5747\u503c", "\u6700\u5927\u503c", "\u6700\u5c0f\u503c", "\u603b\u548c", "\u89e6\u53d1\u7528\u6237\u6570", "\u53bb\u91cd\u6570", "\u4eba\u5747\u6b21\u6570", "\u4eba\u5747\u503c")));
    }
}
