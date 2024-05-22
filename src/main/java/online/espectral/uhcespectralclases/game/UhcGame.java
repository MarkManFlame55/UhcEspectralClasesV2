package online.espectral.uhcespectralclases.game;

import online.espectral.uhcespectralclases.classes.PlayerPositionStatus;
import online.espectral.uhcespectralclases.util.ServerMessage;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;

import java.util.*;

public class UhcGame {

    Server server;

    public UhcGame(Server server) {
        this.server = server;
    }
    public static List<UhcPlayer> gamePlayers = new ArrayList<>();
    public void addPlayer(UUID uuid) {
        for (UhcPlayer uhcPlayer : gamePlayers) {
            if (uhcPlayer.getUuid().equals(uuid)) {
                //server.getLogger().warning("UUID " + uuid + " (" + uhcPlayer.getPlayer().getName() + ") ya pertenece a la partida");
                return;
            }
        }
        UhcPlayer uhcPlayer = new UhcPlayer(uuid);
        gamePlayers.add(uhcPlayer);
    }
    public void start() {
        AbilityManager.initAbilities();
        PlayerPositionStatus.init(this.server);
        for (UhcPlayer uhcPlayer : gamePlayers) {
            if (uhcPlayer != null && uhcPlayer.getPlayer() != null) {
                ServerMessage.unicastSuccess(uhcPlayer.getPlayer(), "Has recibido tu habilidad de " + uhcPlayer.getUhcClass());
            }
        }
        ServerMessage.multicastToOp(ChatColor.GRAY + "Partida Comenzada");
        ServerMessage.broadcastSound(Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.1f);
    }
    public void stop() {
        gamePlayers.removeIf(Objects::nonNull);
    }
    public void reload() {
        gamePlayers.removeIf(Objects::nonNull);
        ServerMessage.broadcast(ChatColor.GRAY + "Se ha reiniciado la partida!");
    }
    public UhcPlayer getPlayer(UUID uuid) {
        for (UhcPlayer uhcPlayer : gamePlayers) {
            if (uhcPlayer.getUuid().equals(uuid)) {
                return uhcPlayer;
            }
        }
        return null;
    }
    public void removePlayer(UUID uuid) {
        if (gamePlayers.contains(getPlayer(uuid))) {
            gamePlayers.remove(getPlayer(uuid));
        } else {
            server.getLogger().warning("Player does not exist");
        }
    }
    public List<UhcPlayer> getPlayers()  {
        return gamePlayers;
    }
}
