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

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.*;

public class Whitelist {

    private Timer timer = new Timer();

    private long startTime;
    private long whitelistTime;
    private Server server;

    private int delay = 1000;
    private int period = 1000;

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            timerTaskLogic();
        }
    };


    public Whitelist(long whitelistTime, Server server) {
        this.whitelistTime = whitelistTime;
        this.startTime = whitelistTime;
        this.server = server;
    }

    public void start() {
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }

    public void addPlayer(Player player) {
        if (whitelistTime > 0 && !player.isWhitelisted()) {
            player.setWhitelisted(true);
            server.broadcastMessage(ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + " has been added to the whitelist");
        }
    }

    public void closeSignup() {
        whitelistTime = 0;
    }

    public void cancelSignup() {
        timerTask.cancel();
        timer.cancel();
    }

    private void timerTaskLogic () {
        if (startTime == whitelistTime) {
            server.broadcastMessage(ChatColor.GREEN + "Auto-whitelisting is open for " + whitelistTime / 60 + " minutes.");
            server.setWhitelist(false);
            Collection players = server.getOnlinePlayers();
            List<Player> playerList = new ArrayList<Player>();
            for (Object o : players) {
                Player player = (Player)o;
                if (!player.isWhitelisted()) {
                    player.setWhitelisted(true);
                    playerList.add(player);
                }
            }
            if (!playerList.isEmpty()) {
                StringBuilder sb = new StringBuilder("Following players has automatically been whitelisted: " + ChatColor.LIGHT_PURPLE);
                for (Player player : playerList) {
                    sb.append(" " + player.getDisplayName());
                }
                server.broadcastMessage(sb.toString());
            }
        }
        whitelistTime--;
        if (whitelistTime <= 0) {
            server.broadcastMessage(ChatColor.RED + "Whitelisting is now CLOSED!" + ChatColor.RED + " Good Luck!");
            server.setWhitelist(true);
            timerTask.cancel();
            timer.cancel();
            timer.purge();
        }
        else if (whitelistTime % (60*5) == 0) { // Prints every 5 minutes.
            server.broadcastMessage(ChatColor.DARK_PURPLE + "Whitelisting is open for " + whitelistTime / 60 + " minutes! Make sure your friends join before it closes.");
        }
        else if (whitelistTime == 60) { // Prints when last minute.
            server.broadcastMessage(ChatColor.DARK_PURPLE + "Whitelisting is open for " + whitelistTime/60 + " minute! Make sure your friends join before it closes.");
        }
    }
}
