package online.espectral.uhcespectralclases.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ServerMessage {

    private static final String prefix = ChatColor.WHITE + "" + ChatColor.BOLD + "[" + ChatColor.AQUA + ChatColor.BOLD + "UHC Clases" + ChatColor.WHITE + ChatColor.BOLD + "] " + ChatColor.GRAY + "➤ " + ChatColor.RESET;
    public static final String NOT_ALLOWED = "No puedes usar este item";

    public static void broadcast(String message) {
        Server server = Bukkit.getServer();
        for (Player players : server.getOnlinePlayers()) {
            players.sendMessage(prefix + message);
        }
    }
    public static void multicastToOp(String message) {
        Server server = Bukkit.getServer();
        for (Player player : server.getOnlinePlayers()) {
            if (player.isOp()) {
                player.sendMessage(prefix + message);
            }
        }
    }

    public static void unicastSuccess(Player player, String message) {
        player.sendMessage(prefix + ChatColor.GREEN + message);
    }
    public static String unicastInfo(Player player, String message) {
        player.sendMessage(prefix + message);
        return message;
    }
    public static void unicastError(Player player, String message) {
        player.sendMessage(prefix + ChatColor.RED + message);
    }
    public static void cooldownMesssage(Player player, long timeElapsed, long finishTime) {
        unicastError(player, "Esta habilidad sigue en cooldown! " + ChatColor.DARK_GRAY + "(" + Time.getRemainTime(timeElapsed, finishTime) + "s)");
    }
    public static void broadcastSound(Sound sound, float volume, float pitch) {
        Server server = Bukkit.getServer();
        for (Player player : server.getOnlinePlayers()) {
            player.playSound(player, sound, volume, pitch);
        }
    }
}
