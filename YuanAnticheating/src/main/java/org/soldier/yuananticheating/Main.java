package org.soldier.yuananticheating;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.soldier.yuananticheating.fly.a1;
import org.soldier.yuananticheating.fly.a2;

public class Main extends JavaPlugin implements Listener {
  private TitleNameChecker titleNameChecker;
  
  private a2 a2Instance;
  
  public void onEnable() {
    TitleNameConfig.initialize(this);
    this.titleNameChecker = new TitleNameChecker(this);
    this.a2Instance = new a2();
    Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
    Bukkit.getPluginManager().registerEvents((Listener)new a1(), (Plugin)this);
    Bukkit.getPluginManager().registerEvents((Listener)this.a2Instance, (Plugin)this);
    getCommand("yuan").setExecutor(new ReloadCommand());
    getLogger().info("" + ChatColor.GREEN + "元神，启动！");
    getLogger().info("" + ChatColor.GREEN + "作者: Kpect");
    getLogger().info("" + ChatColor.GREEN + "=====================================");
    getLogger().info("" + ChatColor.GREEN + "            #       #");
    getLogger().info("" + ChatColor.GREEN + "             #     #");
    getLogger().info("" + ChatColor.GREEN + "              #   #");
    getLogger().info("" + ChatColor.GREEN + "               # #");
    getLogger().info("" + ChatColor.GREEN + "                #");
    getLogger().info("" + ChatColor.GREEN + "                #");
    getLogger().info("" + ChatColor.GREEN + "                #");
    getLogger().info("" + ChatColor.GREEN + "Version: " + ChatColor.GREEN);
    getLogger().info("" + ChatColor.GREEN + "=====================================");
  }
  
  public void onDisable() {
    TitleNameConfig.getInstance().saveConfig();
    getLogger().info("" + ChatColor.RED + "=====================================");
    getLogger().info("" + ChatColor.RED + "        Yuan Anti-Cheat Plugin");
    getLogger().info("" + ChatColor.RED + "             Version: " + ChatColor.RED);
    getLogger().info("" + ChatColor.RED + "          元神，关闭！");
    getLogger().info("" + ChatColor.RED + "          插件定制：263178598");
    getLogger().info("" + ChatColor.RED + "=====================================");
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    int requiredModCount = this.a2Instance.getRequiredModCount();
    int actualModCount = this.a2Instance.countMods(player);
    if (actualModCount != requiredModCount)
      player.kickPlayer("" + ChatColor.RED + "检测到非法客户端。"); 
  }
  
  private class ReloadCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
        reloadConfigs();
        sender.sendMessage("" + ChatColor.GREEN + "已重新加载配置文件。");
        return true;
      } 
      return false;
    }
    
    private void reloadConfigs() {
      TitleNameConfig.getInstance().reloadConfig();
      Main.this.a2Instance.reloadConfig();
    }
  }
}
