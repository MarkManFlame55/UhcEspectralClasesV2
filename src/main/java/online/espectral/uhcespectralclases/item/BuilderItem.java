package online.espectral.uhcespectralclases.item;

import com.fastasyncworldedit.core.FaweAPI;
import com.fastasyncworldedit.core.configuration.Settings;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.math.transform.Transform;
import com.sk89q.worldedit.math.transform.Transforms;
import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.game.BuilderStructure;
import online.espectral.uhcespectralclases.game.UhcClass;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.game.UhcPlayer;
import online.espectral.uhcespectralclases.util.ServerMessage;
import online.espectral.uhcespectralclases.util.Time;
import online.espectral.uhcespectralclases.util.worldedit.SchematicManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BuilderItem implements Listener {

    UhcGame uhcGame = UhcEspectralClases.getUhcGame();
    Plugin plugin = UhcEspectralClases.getPlugin(UhcEspectralClases.class);
    Clipboard clipboard;
    final File WALL = new File(this.plugin.getDataFolder(), "schematics/wall.schem");
    final File STAIRS = new File(this.plugin.getDataFolder(), "schematics/stairs.schem");
    final File TOWER = new File(this.plugin.getDataFolder(), "schematics/tower.schem");
    HashMap<UUID, Long> cooldown;
    long cooldownTime = Time.seconds(30);

    public BuilderItem() {
        this.cooldown = new HashMap<>();
    }


    public static ItemStack tool() {
        ItemStack itemStack = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Builder Tool Item");
        itemMeta.setDisplayName(ChatColor.BLUE + "Herramienta del Arquitecto");
        itemMeta.setMaxStackSize(1);
        itemMeta.setEnchantmentGlintOverride(true);

        List<String> itemLore = new ArrayList<>();
        itemLore.add("");
        itemLore.add(ChatColor.DARK_GRAY + "Click Izquiero para cambiar estructura");
        itemLore.add(ChatColor.DARK_GRAY + "Click Derecho para colocar estructura");
        itemMeta.setLore(itemLore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack block() {
        ItemStack itemStack = new ItemStack(Material.BRICKS, 64);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setItemName("Builder Block Item");
        itemMeta.setDisplayName(ChatColor.BLUE + "Bloques De Constructor");
        itemMeta.setEnchantmentGlintOverride(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
       if (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            ItemStack itemStack = e.getItem();
            if (itemStack != null && itemStack.isSimilar(tool())) {
                Player player = e.getPlayer();
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.BUILDER)) {
                    switch (uhcPlayer.getStructure()) {
                        case WALL:
                            uhcPlayer.setStructure(BuilderStructure.STAIR);
                            player.sendTitle("", ChatColor.BLUE + "◣", 1, 10, 1);
                            player.playSound(player, Sound.BLOCK_WOOD_PLACE, 1.0f, 1.0f);
                            break;
                        case STAIR:
                            uhcPlayer.setStructure(BuilderStructure.TOWER);
                            player.sendTitle("", ChatColor.BLUE + "♜", 1, 10, 1);
                            player.playSound(player, Sound.BLOCK_WOOD_PLACE, 1.0f, 1.0f);
                            break;
                        case TOWER:
                            uhcPlayer.setStructure(BuilderStructure.WALL);
                            player.sendTitle("", ChatColor.BLUE + "⬛", 1, 10, 1);
                            player.playSound(player, Sound.BLOCK_WOOD_PLACE, 1.0f, 1.0f);
                            break;
                    }
                }
            }
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) throws WorldEditException {
        ItemStack itemStack = e.getItemInHand();
        if (itemStack.isSimilar(tool())) {
            Player player = e.getPlayer();
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.BUILDER)) {
                if (!this.cooldown.containsKey(player.getUniqueId())) {
                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    Location pos = e.getBlock().getLocation();
                    rightClickAbility(uhcPlayer, pos);
                } else {
                    long timeElapsed = System.currentTimeMillis() - this.cooldown.get(player.getUniqueId());
                    if (timeElapsed >= cooldownTime) {
                        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        Location pos = e.getBlock().getLocation();
                        rightClickAbility(uhcPlayer, pos);
                    } else {
                        e.setCancelled(true);
                        if (uhcPlayer.canSeeCooldown()) {
                            ServerMessage.cooldownMesssage(player, timeElapsed, cooldownTime);
                        }
                    }
                }
            } else {
                e.setCancelled(true);
                ServerMessage.unicastError(player, ServerMessage.NOT_ALLOWED);
            }
        }
        if (itemStack.isSimilar(block())) {
            Player player = e.getPlayer();
            UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
            if (uhcPlayer != null && uhcPlayer.hasUhcClass() && uhcPlayer.getUhcClass().equals(UhcClass.BUILDER)) {
                int slot = player.getInventory().getHeldItemSlot();
                player.getInventory().setItem(slot, block());
            } else {
                e.setCancelled(true);
                itemStack.setAmount(0);
                ServerMessage.unicastError(player, ServerMessage.NOT_ALLOWED);
            }
        }
    }
    private void rightClickAbility(UhcPlayer uhcPlayer, Location pos) throws WorldEditException {
        Player player = uhcPlayer.getPlayer();
        World world = pos.getWorld();
        assert world != null;
        switch (uhcPlayer.getStructure()) {
            case WALL -> clipboard = SchematicManager.getClipBoard(WALL, this.plugin.getLogger());
            case STAIR -> clipboard = SchematicManager.getClipBoard(STAIRS, this.plugin.getLogger());
            case TOWER -> clipboard = SchematicManager.getClipBoard(TOWER, this.plugin.getLogger());
        }
        double rotation = 0;
        switch (player.getFacing()) {
            case NORTH -> rotation = 0;
            case WEST -> rotation = 90;
            case SOUTH -> rotation = 180;
            case EAST -> rotation = 270;
        }
        AffineTransform rotate = new AffineTransform().rotateY(rotation);
        clipboard = clipboard.transform(rotate);
        SchematicManager.pasteSchematic(clipboard, world, pos.getBlockX(), pos.getBlockY(), pos.getBlockZ());
        world.spawnParticle(Particle.WHITE_SMOKE, pos, 50, 1,1,1);
        world.playSound(pos, Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1.0f, 1.0f);
        world.playSound(pos, Sound.ITEM_TRIDENT_RETURN, 1.0f, 1.0f);
        if (!player.getGameMode().equals(GameMode.CREATIVE)) {
            int slot = player.getInventory().getHeldItemSlot();
            player.getInventory().setItem(slot, BuilderItem.tool());
        }
    }
}
