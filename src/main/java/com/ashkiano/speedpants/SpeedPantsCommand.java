package com.ashkiano.speedpants;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class SpeedPantsCommand implements CommandExecutor {
    public static String SPEED_PANTS_LORE;
    private final JavaPlugin plugin;
    private String permission;

    public SpeedPantsCommand(String permission, JavaPlugin plugin) {
        this.permission = permission;
        this.plugin = plugin;
        SPEED_PANTS_LORE = ChatColor.GRAY + plugin.getConfig().getString("speedpants-lore", "Enjoy your speed!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission(permission)) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                return true;
            }

            String speedPantsName = plugin.getConfig().getString("speedpants-name", "Speed pants");
            ItemStack speedPants = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta meta = (LeatherArmorMeta) speedPants.getItemMeta();
            meta.setColor(Color.BLUE);
            meta.setLore(Arrays.asList(SPEED_PANTS_LORE));
            meta.setDisplayName(speedPantsName);
            speedPants.setItemMeta(meta);

            player.getInventory().addItem(speedPants);

            return true;
        }

        return false;
    }
}
