package autosaveworld.commands.subcommands;

import autosaveworld.commands.ISubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class StopCommand implements ISubCommand {

    private static volatile boolean stoppedByASW;

    public static boolean isStoppedByAsw() {
        return stoppedByASW;
    }

    public static void stop() {
        stoppedByASW = true;
        Bukkit.shutdown();
    }

    @Override
    public void handle(CommandSender sender, String[] args) {
        stop();
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public int getMinArguments() {
        return 0;
    }

}
