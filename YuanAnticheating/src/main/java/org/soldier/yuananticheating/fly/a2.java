package org.soldier.yuananticheating.fly;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class a2 extends JavaPlugin implements Listener {
  private FileConfiguration modsConfig;
  
  public void onEnable() {
    saveDefaultConfig();
    this.modsConfig = getConfig();
    getServer().getPluginManager().registerEvents(this, (Plugin)this);
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    int requiredModCount = this.modsConfig.getInt("required_mod_count", 0);
    int actualModCount = countMods(player);
    if (actualModCount != requiredModCount)
      player.kickPlayer("" + ChatColor.RED + "检测到非法客户端。"); 
  }
  
  public int getRequiredModCount() {
    return this.modsConfig.getInt("required_mod_count", 0);
  }
  
  public int countMods(Player player) {
    return 0;
  }
  
  public void saveDefaultConfig() {
    File configFile = new File(getDataFolder(), "mods.yml");
    if (!configFile.exists())
      saveResource("mods.yml", false); 
  }
  
  public void reloadConfig() {
    this.modsConfig = getConfig();
  }
}
