package com.ajay.designPatterns.decorator;


import java.util.HashMap;
import java.util.Map;

public class CodeBuilder
{
    String className;
    Map<String, String> fields = new HashMap<>();

    public CodeBuilder(String className)
    {
        // todo
        this.className = className;
    }

    public CodeBuilder addField(String name, String type)
    {
        // todo
        this.fields.put(name, type);
        return this;

    }

    @Override
    public String toString()
    {
        // todo
        StringBuilder result = new StringBuilder("public class ");
        result.append(className);
        result.append("\n{");
        fields.entrySet().forEach(entry -> {
            result.append("\n");
            result.append("public "+entry.getKey()+" "+entry.getValue()+";");
        });
        result.append("\n}");
        return result.toString();
    }
}