package com.github.irvinglink.DreamWorld.Commands;

import com.github.irvinglink.DreamWorld.Handlers.DreamPlayer;
import com.github.irvinglink.DreamWorld.Handlers.SettingsMenu;
import com.github.irvinglink.DreamWorld.Handlers.Spawn;
import com.github.irvinglink.DreamWorld.Handlers.SpawnHandler;
import com.github.irvinglink.DreamWorld.MClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.irvinglink.DreamWorld.Handlers.SpawnHandler.getSpawnList;

public class DreamCommand implements CommandExecutor {

    private final MClass plugin;

    public DreamCommand(MClass plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        SpawnHandler handler = new SpawnHandler(plugin);

        if (sender instanceof Player) {

            DreamPlayer dreamPlayer = new DreamPlayer((Player) sender);

            if (dreamPlayer.getPlayer().hasPermission("RandomDreams.admin")) {
                if (args.length == 0 || args[0].equalsIgnoreCase("help")) {

                    dreamPlayer.sendMessage("&7---===&8[&bDream&eWorld]&7===---");
                    dreamPlayer.sendMessage("&e/dreamworld &ahelp");
                    dreamPlayer.sendMessage("&e/dreamworld &alist &b[&e#&b]");
                    dreamPlayer.sendMessage("&e/dreamworld &aworld set &c<world>");
                    dreamPlayer.sendMessage("&e/dreamworld &aworld delete &c<world>");
                    dreamPlayer.sendMessage("&e/dreamworld &aworld teleport &c<world>");
                    dreamPlayer.sendMessage("&7---===&8[&bDream&eWorld]&7===---");

                    return true;
                }
                if (args[0].equalsIgnoreCase("list")) {

                    if (!getSpawnList().isEmpty()) {

                        if (args.length == 2) {

                            try {
                                dreamPlayer.sendMessage("\n &7-=&bDream&eWorlds&7=-");

                                ////////////////////
                                // FIXME: 2/15/2020
                                int page = Integer.parseInt(args[1]);


                                dreamPlayer.sendMessage("&7 -=&bDream&eWorlds&7=-");

                            } catch (NumberFormatException ignored) {
                                dreamPlayer.sendMessage("&cYou can only use numbers&7.", true);
                            }
                        }

                    } else {
                        dreamPlayer.sendMessage("&cThere are no worlds registered&7.", true);
                    }

                    return true;
                }
                if (args[0].equalsIgnoreCase("world")) {

                    if (args.length == 1) {
                        dreamPlayer.sendMessage("&7Use &bset&7/&bdelete&7/&bteleport&7.", true);
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("set") && args.length == 3) {

                        String world = args[2];

                        if (!handler.existSpawn(world)){
                            handler.createSpawn(world, dreamPlayer.getLocation());
                            dreamPlayer.sendMessage("&7World &a" + world + " &7registered successfully!", true);
                        } else dreamPlayer.sendMessage("&cThat world already exists!", true);

                        return true;

                    }

                    if (args[1].equalsIgnoreCase("delete") && args.length == 3) {

                        String world = args[2];

                        if (handler.existSpawn(world)){
                            handler.deleteSpawn(world);
                            dreamPlayer.sendMessage("&cWorld &b" + world + " &chas been deleted!", true);
                        } else dreamPlayer.sendMessage("&cThat world does not exists&7.", true);
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("teleport") && args.length == 3) {

                        String world = args[2];
                        Spawn spawn = handler.getSpawn(world);

                        dreamPlayer.teleport(spawn);
                        dreamPlayer.sendMessage("&aMoving to another world!", true);
                        return true;

                    }
                    if (args[1].equalsIgnoreCase("settings") && args.length == 3) {

                        String world = args[2];
                        SettingsMenu settingsMenu = new SettingsMenu(plugin);

                        settingsMenu.setWorldName(world);
                        dreamPlayer.getPlayer().openInventory(new SettingsMenu(plugin).getInventory());

                    } else {

                        dreamPlayer.sendMessage("&cThat command does not exists&7.&c Use &b/dreamworld help&7.", true);
                        return true;

                    }
                } else {

                    dreamPlayer.sendMessage("&cThat command does not exists&7.&c Use &b/dreamworld help&7.", true);
                    return true;

                }
            } else {

                dreamPlayer.sendMessage("&cYou do not have permission to do this&7.", true);
                return true;

            }

        }
        return false;
    }
}
