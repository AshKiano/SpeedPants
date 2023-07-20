package com.ashkiano.speedpants;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedPantsListener implements Listener {

    private final JavaPlugin plugin;

    public SpeedPantsListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> checkForSpeedPants(player), 1L);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> checkForSpeedPants(player), 1L);
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> checkForSpeedPants(player), 1L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        checkForSpeedPants(player);
    }

    private void checkForSpeedPants(Player player) {
        ItemStack pants = player.getInventory().getLeggings();
        int speedAmplifier = plugin.getConfig().getInt("speed-value", 1);

        if (pants != null && pants.getType() == Material.LEATHER_LEGGINGS) {
            ItemMeta meta = pants.getItemMeta();

            if (meta == null) return;
            if (meta.hasLore() && meta.getLore().contains(SpeedPantsCommand.SPEED_PANTS_LORE)) {
                if (!player.hasPotionEffect(PotionEffectType.SPEED)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, speedAmplifier - 1, false, false, false));
                }
                return;
            }
        }

        if (player.hasPotionEffect(PotionEffectType.SPEED)) {
            player.removePotionEffect(PotionEffectType.SPEED);
        }
    }
}
