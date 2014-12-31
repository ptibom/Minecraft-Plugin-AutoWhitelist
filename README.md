Minecraft-Plugin-AutoWhitelist
==============================

**[Download ready to use Plugin](https://github.com/ptibom/Minecraft-Plugin-AutoWhitelist/raw/master/DownloadCompiledPluginHere/AutoWhitelist.jar)**


##Plugin Description##
Opens auto-whitelisting for a set period of time. Then closes signup.

**Commands:**  
/opensignup {minutes}
- Enables autowhitelisting for X amount of minutes. When a player joins, the player is whitelisted. Also whitelists current players on the server.

/closesignup
- Force closes signup. Sets timer to 0 minutes.

/cancelsignup
- Cancels the timer. Auto whitelisting remains. (Not fully tested, could cause issues.)

##Requirements##
The plugin is built with Java 8 for [Spigot 1.8](http://www.spigotmc.org/). Although not tested, it should work for earlier versions of [Spigot](http://www.spigotmc.org) and [Bukkit](http://bukkit.org). It should also work for Java 7.

[Bukkit](http://bukkit.org) is a Minecraft server with plugin functionality. It is no longer being developed.  
[Spigot](http://www.spigotmc.org) is a newer, faster, better and up to date version of Bukkit.

**Recommended requirements**
- Java 8 or newer
- Spigot 1.8 or newer

**Alternative requirements**
- Java 7
- Most older versions of Spigot
- Most versions of Bukkit

The above are runtime requirements. To compile the project a Spigot/Bukkit API is required and it is included in the project. The API doesn't provide much code but rather the classes and function definitions so that they can be called within the project.


##Installing##
1. Grab your self a version of [Spigot](http://www.spigotmc.org) server or [Bukkit](http://bukkit.org) server. (Spigot is better)
2. When you run the server for the first time, a plugin folder will be created.
3. Put the [AutoWhitelist.jar](https://github.com/ptibom/Minecraft-Plugin-AutoWhitelist/raw/master/DownloadCompiledPluginHere/AutoWhitelist.jar) file in the newly created plugin folder.
4. Restart the server. (Or use the reload command)
5. Plugin should now work.


##Building & Compiling##
This is for devs. Look no further if you're not a dev.

Project is created and built in [IntelliJ IDEA 14 Community Edition](https://www.jetbrains.com/idea) and Java 8. Java 7 should work too.

To build, simply open the project file and click build project / build artifacts.

**Building by hand or with other IDEs**

Every file in the src/ folder should be compiled to class files, except plugin.yml.  
The result should be added to a JAR file.

Make sure to output a JAR file with the following content:  
JAR-File/plugin.yml  
JAR-File/se/autowhitelist/AutoWhitelist.class  
JAR-File/se/autowhitelist/Whitelist.class

**Dependencies**  
spigot-api-1.8-R0.1-SNAPSHOT.jar (can be swapped to other versions of spigot and bukkit)
