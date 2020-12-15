package cn.xianbin.queryengine.request;

public interface RequestElementValidator {

    default void parse(Parser parser) throws Exception {
    }

    default boolean validationCheck() {
        return true;
    }

}
