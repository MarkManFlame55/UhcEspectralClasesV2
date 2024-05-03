package online.espectral.uhcespectralclases.commands;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.classes.*;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.gui.HostMenu;
import online.espectral.uhcespectralclases.item.SelectorItem;
import online.espectral.uhcespectralclases.util.Description;
import online.espectral.uhcespectralclases.util.ServerMessage;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static online.espectral.uhcespectralclases.util.Description.helpMessage;

public class classCommand implements CommandExecutor, TabCompleter {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    List<String> options = new ArrayList<>();

    public classCommand() {
        options.add("selector");
        options.add("menu");
        options.add("give");
        options.add("get");
        options.add("help");
        options.add("start");
        options.add("list");
        options.add("reload");
        options.add("credits");
        options.add("remove");
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            Server server = player.getServer();
            if (strings.length > 0) {
                switch (strings[0]) {
                    case "selector":
                        for (Player player1 : server.getOnlinePlayers()) {
                            uhcGame.addPlayer(player1.getUniqueId());
                            if (!player1.getInventory().contains(SelectorItem.give())) {
                                player1.getInventory().addItem(SelectorItem.give());
                                ServerMessage.unicastSuccess(player1, "Has recibido el " + SelectorItem.give().getItemMeta().getDisplayName());
                                player1.playSound(player1, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.0f);
                            }
                        }
                        break;
                    case "menu":
                        HostMenu.open(player);
                        break;
                    case "give":
                        if (strings.length == 3) {
                            Player target = server.getPlayer(strings[1]);
                            if (target != null) {
                                uhcGame.addPlayer(player.getUniqueId());
                                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                                if (uhcPlayer != null && strings[2] != null) {
                                    if (strings[2].equalsIgnoreCase("WITCH")) {
                                        uhcPlayer.setUhcClass(UhcClass.WITCH);
                                        WitchClassAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("BREEZE")) {
                                        uhcPlayer.setUhcClass(UhcClass.BREEZE);
                                        BreezeAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("SPIDER")) {
                                        uhcPlayer.setUhcClass(UhcClass.SPIDER);
                                        SpiderAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("DOLPHIN")) {
                                        uhcPlayer.setUhcClass(UhcClass.DOLPHIN);
                                        DolphinAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("IRON_GOLEM")) {
                                        uhcPlayer.setUhcClass(UhcClass.IRON_GOLEM);
                                        IronGolemAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("WARRIOR")) {
                                        uhcPlayer.setUhcClass(UhcClass.WARRIOR);
                                        WarriorAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("GUNNER")) {
                                        uhcPlayer.setUhcClass(UhcClass.GUNNER);
                                        GunnerAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("WARDEN")) {
                                        uhcPlayer.setUhcClass(UhcClass.BUILDER);
                                        BuilderAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("MINER")) {
                                        uhcPlayer.setUhcClass(UhcClass.MINER);
                                        MinerAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("ANTMAN")) {
                                        uhcPlayer.setUhcClass(UhcClass.ANTMAN);
                                        AntmanClassAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("WITHER")) {
                                        uhcPlayer.setUhcClass(UhcClass.WITHER);
                                        WitherAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else if (strings[2].equalsIgnoreCase("BLAZE")) {
                                        uhcPlayer.setUhcClass(UhcClass.BLAZE);
                                        BlazeAbility.init(uhcPlayer);
                                        ServerMessage.unicastSuccess(player, "Se ha dado la clase " + strings[2] + " a " + target.getName());
                                        ServerMessage.unicastSuccess(target, "Has recibido la clase " + strings[2]);
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else {
                                        ServerMessage.unicastError(player, "No se encuentra la clase " + strings[2] + ", puede que la hayas escrito mal. Puedes consultar las clases mirando el comando /class list");
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                                    }
                                }
                            } else {
                                ServerMessage.unicastError(player, "No se encuentra el jugador " + strings[1]);
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                            }
                        } else {
                            ServerMessage.unicastError(player, "Comando Incompleto: /class give <Jugador> <UhcClass>");
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                        }
                        break;
                    case "get":
                        if (strings.length == 2) {
                            Player target = server.getPlayer(strings[1]);
                            if (target != null) {
                                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                                if (uhcPlayer != null) {
                                    if (uhcPlayer.hasUhcClass()) {
                                        ServerMessage.unicastInfo(player, target.getName() + " tiene la clase " + uhcPlayer.getUhcClass());
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                    } else {
                                        ServerMessage.unicastError(player, target.getName() + " no tiene ninguna clase aún");
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                                    }
                                } else {
                                    ServerMessage.unicastError(player, target.getName() + " no esta jugando la partida :P");
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                                }
                            } else {
                                ServerMessage.unicastError(player, "No se encuentra el jugador " + strings[1]);
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                            }
                        } else {
                            ServerMessage.unicastError(player, "Comando Incompleto: /class get <Jugador>");
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                        }
                        break;
                    case "start":
                        uhcGame.start();
                        break;
                    case "reload":
                        uhcGame.reload();
                        break;
                    case "help":
                        for (int i = 0; i < helpMessage().size(); i++) {
                            player.sendMessage(helpMessage().get(i));
                        }
                        break;
                    case "list":
                        StringBuilder msg = new StringBuilder(ServerMessage.unicastInfo(player, ChatColor.GRAY + ""));
                        for (UhcClass uhcClass : UhcClass.values()) {
                            msg.append(uhcClass).append(" ");
                        }
                        player.sendMessage(String.valueOf(msg));
                        break;
                    case "credits":
                        for (int i = 0; i < Description.creditsMessage().size(); i++) {
                            player.sendMessage(Description.creditsMessage().get(i));
                        }
                        break;
                    case "remove":
                        if (strings.length == 2) {
                            Player target = server.getPlayer(strings[1]);
                            if (target != null) {
                                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                                if (uhcPlayer != null) {
                                    if (uhcPlayer.hasUhcClass()) {
                                        ServerMessage.unicastInfo(target, ChatColor.GRAY + "Ya no eres la clase " + uhcPlayer.getUhcClass());
                                        target.playSound(target, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                                        ServerMessage.unicastSuccess(player, "Se la eliminado la clase de " + target.getName());
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.5f);
                                        for (PotionEffect potionEffect : target.getActivePotionEffects()) {
                                            target.removePotionEffect(potionEffect.getType());
                                        }
                                        uhcPlayer.setUhcClass(null);
                                        uhcGame.removePlayer(uhcPlayer.getUuid());
                                    } else {
                                        ServerMessage.unicastError(player, target.getName() + " no tiene ninguna clase aún");
                                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                                    }
                                } else {
                                    ServerMessage.unicastError(player, target.getName() + " no esta jugando la partida :P");
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                                }
                            } else {
                                ServerMessage.unicastError(player, "No se encuentra el jugador " + strings[1]);
                                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                            }
                        } else {
                            ServerMessage.unicastError(player, "Comando Incompleto: /class remove <Jugador>");
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 0.5f);
                        }
                }
            } else {
                HostMenu.open(player);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1 && commandSender instanceof Player) {
            List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(strings[0], options, completions);
            Collections.sort(completions);
            return completions;
        }
        return null;
    }
}
