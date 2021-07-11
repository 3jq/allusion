package dev.sl33py.allusion.client.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {

    public String name, usage;
    public List<String> aliases = new ArrayList<>();

    public Command(String name, String usage, String... aliases) {
        super();
        this.name = name;
        this.usage = usage;
        this.aliases = Arrays.asList(aliases);
    }

    public abstract void handle(String[] args);

}
