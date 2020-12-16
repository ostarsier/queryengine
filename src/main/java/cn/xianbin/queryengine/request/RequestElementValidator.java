package cn.xianbin.queryengine.request;

public interface RequestElementValidator {

    default void parse() throws Exception {
    }

    default boolean validationCheck() {
        return true;
    }

}
