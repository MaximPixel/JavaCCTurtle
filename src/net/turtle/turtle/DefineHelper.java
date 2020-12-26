package net.turtle.turtle;

import java.util.HashMap;

public class DefineHelper implements IDefine {

    private final HashMap<String, String> variables = new HashMap();

    @Override
    public void define(String variable, String value) {
        variables.put(variable, value);
    }

    @Override
    public String getDefine(String variable) {
        return variables.get(variable);
    }

    @Override
    public boolean hasDefine(String variable) {
        return variables.containsKey(variable);
    }

    @Override
    public String getDefineByValue(String value) {
        return variables.keySet().stream().filter(name -> variables.get(name).equals(value)).findAny().get();
    }

    @Override
    public boolean hasDefineByValue(String value) {
        return variables.containsValue(value);
    }
}
