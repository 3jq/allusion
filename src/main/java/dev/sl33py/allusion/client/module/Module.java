package dev.sl33py.allusion.client.module;

public class Module {

    private String name;
    private String description;
    private Category category;
    private int key = -1;

    private boolean enabled = false;

    public Module(String name, String description, Category category) {
        super();
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        if (isEnabled()) disable();
        else enable();
    }

    public void enable() {
        setEnabled(true);
        onEnable();
    }

    public void disable() {
        setEnabled(false);
        onDisable();
    }

    public void onUpdate() {}

    public void onEnable() {}

    public void onDisable() {}

    public enum Category {
        Combat,
        Movement,
        Miscellaneous,
        Player,
        Render,
        Client
    }
}
