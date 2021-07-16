package dev.sl33py.allusion.client.module;

import org.reflections.Reflections;

import java.util.*;

public class ModuleManager {

    public TreeMap<String, Module> modules;

    public ModuleManager() {
        modules = new TreeMap<>();

        /**
         * Iterating every module, adding it to massive.
         */
        Set<Class<? extends Module>> reflections = new Reflections("dev.sl33py.allusion.client.module.modules").getSubTypesOf(Module.class);
        reflections.forEach(aClass -> {
            try {
                modules.put(aClass.newInstance().getName().toLowerCase(), aClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public Module getModule(String name) {
        return modules.get(name.toLowerCase());
    }

    public ArrayList<Module> getModulesByCategory(Module.Category category) {
        ArrayList<Module> modulesInCategory = new ArrayList<>();

        modules.forEach((key, value) -> {
            if (value.getCategory().equals(category)) modulesInCategory.add(value);
        });

        return modulesInCategory;
    }
}
