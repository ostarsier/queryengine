package cn.xianbin.queryengine.expression;

public class AliasGenerator {

    private int a;
    private String b;
    private static int c = 25;

    public AliasGenerator() {
        this.a = 0;
        this.b = "";
    }

    public String nextAlias() {
        String string = this.b + (char) (97 + this.a);
        ++this.a;
        if (this.a > 25) {
            this.a = 0;
            this.b += "x";
        }
        return string;
    }

    public void clear() {
        this.a = 0;
        this.b = "";
    }

}
