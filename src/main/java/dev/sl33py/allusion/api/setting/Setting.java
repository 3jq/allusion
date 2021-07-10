package dev.sl33py.allusion.api.setting;

import dev.sl33py.allusion.client.module.Module;

import java.util.List;

public class Setting<T> {

    private T value;
    private Module module;
    private String name;
    private Type type;

    private T min;
    private T max;
    private T inc;

    private List<String> modes;

    public Setting(Module m, String name, T value) {
        this.module = m;
        this.name = name;
        this.value = value;
        this.type = Type.B;
    }

    public Setting(Module m, String name, T value, T min, T max, T inc) {
        this.module = m;
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.inc = inc;
        this.type = Type.N;
    }

    public Setting(Module m, String name, T value, List<String> modes) {
        this.module = m;
        this.name = name;
        this.value = value;
        this.modes = modes;
        this.type = Type.M;
    }

    public T getValue() { return value; }

    public Module getModule() { return module; }

    public String getName() { return name; }

    public List<String> getModes() { return modes; }

    public T getInc() { return inc; }

    public T getMax() { return max; }

    public T getMin() { return min; }

    public Type getType() { return type; }

    public void setValue(T value) { this.value = value; }

    public enum Type {
        B, N, M
    }

}
