package org.soldier.yuananticheating;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TitleNameChecker {
    private Main plugin;

    public TitleNameChecker(Main plugin) {
        this.plugin = plugin;
        startChecking();
    }

    private void startChecking() {
        (new BukkitRunnable() {
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.isOnline())
                        continue;
                    String actualTitle = TitleNameChecker.this.getPlayerWindowTitle(player);
                    String expectedTitle = TitleNameConfig.getInstance().getWindowTitle();
                    if (!expectedTitle.equals(actualTitle))
                        TitleNameChecker.this.kickPlayer(player);
                }
            }
        }).runTaskTimer((Plugin)this.plugin, 1200L, 1200L);
    }

    private String getPlayerWindowTitle(Player player) {
        return player.getName() + "'s Minecraft";
    }

    private void kickPlayer(Player player) {
        player.kickPlayer("检测到非法客户端。");
    }
}
