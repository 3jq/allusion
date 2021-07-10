package dev.sl33py.allusion.client.command;

public abstract class Command {

    public String name, usage;
    public String[] aliases;

    public Command(String name, String usage, String... aliases) {
        super();
        this.name = name;
        this.usage = usage;
        this.aliases = aliases;
    }

    public abstract void handle();

}
