package net.turtle.turtle;

public interface IDefine {

    void define(String variable, String value);

    String getDefine(String variable);

    boolean hasDefine(String variable);

    String getDefineByValue(String value);

    boolean hasDefineByValue(String value);

    default String getRealArgumentValue(String arg) {
        boolean start = arg.startsWith("*");
        boolean end = arg.endsWith("*");

        if (start) {
            arg = arg.substring(1);
        }
        if (end) {
            arg = arg.substring(0, arg.length() - 1);
        }

        boolean hasDefine = hasDefine(arg);

        if (hasDefine) {
            return getDefine(arg);
        } else if (start || end) {
            throw new RuntimeException(String.format("\"%s\" not defined", arg));
        }

        return arg;
    }
}
