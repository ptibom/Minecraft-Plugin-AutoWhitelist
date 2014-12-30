/*
 * Plugin developed by Philip Tibom
 */

package se.autowhitelist;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class AutoWhitelist extends JavaPlugin implements Listener {
    private Whitelist whitelist;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().log(Level.INFO, "AutoWhitelist plugin loaded...");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length == 0) {
                sender.sendMessage("You need to provide an argument in minutes. Example: /opensignup 10");
                return true;
            }
            if (whitelist != null) {
                whitelist.cancelSignup();
            }
            try {
                int minutes = Integer.parseInt(args[0]);
                whitelist = new Whitelist(minutes*60, getServer());
                whitelist.start();
                return true;
            }
            catch (NumberFormatException e) {
                sender.sendMessage("You need to provide an argument in minutes. Example: /opensignup 10");
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onConnect (PlayerJoinEvent evt) {
        if (whitelist != null) {
            whitelist.addPlayer(evt.getPlayer());
        }
    }
}
