package dev.sl33py.allusion;

import dev.sl33py.allusion.api.setting.SettingManager;
import dev.sl33py.allusion.client.module.ModuleManager;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Allusion implements ModInitializer {

    public static final String name = "Allusion";
    public static final String version = "b1";
    public static final String nameVersion = name + " " + version;

    Logger log = LogManager.getLogger(name);

    public static ModuleManager moduleManager;
    public static SettingManager settingManager;

    @Override
    public void onInitialize() {
        log.info("Allusion started.");

        settingManager = new SettingManager();
        moduleManager = new ModuleManager();

        log.info("Allusion initialized.");
        log.info("https://github.com/fuckyouthinkimboogieman/allusion");
    }
}
