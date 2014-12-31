/*
    Minecraft Plugin: AutoWhitelist 
    Copyright (C) 2014  Philip Tibom

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
