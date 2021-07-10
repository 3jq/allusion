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
        for (Module module : modules) if (module.getName().equalsIgnoreCase(name)) return module;
        return null;
    }

    public ArrayList<Module> getModulesByCategory(Module.Category category) {
        ArrayList<Module> modulesInCategory = new ArrayList<>();

        for (Module module : modules) {
            if (module.getCategory() == category) modulesInCategory.add(module);
        }

        return modulesInCategory;
    }
}
