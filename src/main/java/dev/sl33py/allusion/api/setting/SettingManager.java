package dev.sl33py.allusion.api.setting;

import dev.sl33py.allusion.client.module.Module;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SettingManager {

    public List<Setting> settingList;

    public SettingManager() {settingList = new ArrayList<>();}

    public void register(Setting s) { settingList.add(s); }

    public Setting getSetting(String name) {
        return settingList.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Setting getSetting(String name, Module module) {
        return settingList.stream().filter(s -> s.getName().equalsIgnoreCase(name)).filter(s -> s.getModule().equals(module)).findFirst().orElse(null);
    }

    public List<Setting> getSettings(Module module) {
        List<Setting> list = new ArrayList<>();
        settingList.forEach(s -> {
            if(s.getModule().equals(module)) list.add(s);
        });
        return list;
    }

}
