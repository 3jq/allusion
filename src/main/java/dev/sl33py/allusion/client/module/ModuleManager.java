package dev.sl33py.allusion.client.module;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

public class ModuleManager {

    public ArrayList<Module> modules;

    public ModuleManager() {
        modules = new ArrayList<>();

        /**
         * Iterating every module, adding it to massive.
         */
        Set<Class<? extends Module>> reflections = new Reflections("dev.sl33py.allusion.client.module.modules").getSubTypesOf(Module.class);
        reflections.forEach(aClass -> {
            try {
                modules.add(aClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        /**
         * Sorting the modules alphabetically.
         */
        modules.sort(Comparator.comparing(object -> object.getName()));
    }

    public Module getModule(String name) {
        return modules.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public ArrayList<Module> getModulesByCategory(Module.Category category) {
        ArrayList<Module> modulesInCategory = new ArrayList<>();

        modules.forEach(m -> {
            if(m.getCategory().equals(category)) modulesInCategory.add(m);
        });

        return modulesInCategory;
    }
}
