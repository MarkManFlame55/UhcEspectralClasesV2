package online.espectral.uhcespectralclases;

import online.espectral.uhcespectralclases.classes.*;
import online.espectral.uhcespectralclases.commands.classCommand;
import online.espectral.uhcespectralclases.game.UhcGame;
import online.espectral.uhcespectralclases.gui.HostMenu;
import online.espectral.uhcespectralclases.gui.SelectorMenu;
import online.espectral.uhcespectralclases.item.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class UhcEspectralClases extends JavaPlugin {

    private static UhcGame uhcGame;

    @Override
    public void onEnable() {
        //Generar la partida
        uhcGame = new UhcGame(this.getServer());
        //Carpeta para los Schematics de la clase BUILDER
        createFolders();
        //Comandos
        getCommand("class").setExecutor(new classCommand());
        //Eventos Pasivos sobre el servidor
        getServer().getPluginManager().registerEvents(new PlayerMilkPrevnt(), this);
        getServer().getPluginManager().registerEvents(new ClassHurtSounds(), this);
        getServer().getPluginManager().registerEvents(new ClassDeathRemoval(), this);
        //Cosas de Menus
        getServer().getPluginManager().registerEvents(new SelectorMenu(), this);
        getServer().getPluginManager().registerEvents(new HostMenu(), this);
        //Eventos relacionados con las clases y sus habilidades
        getServer().getPluginManager().registerEvents(new AntmanItem(), this);
        getServer().getPluginManager().registerEvents(new SelectorItem(), this);
        getServer().getPluginManager().registerEvents(new WitchWandItem(), this);
        getServer().getPluginManager().registerEvents(new WarriorItem(), this);
        getServer().getPluginManager().registerEvents(new WarriorAbility(), this);
        getServer().getPluginManager().registerEvents(new DolphinAbility(), this);
        getServer().getPluginManager().registerEvents(new CobwebBallItem(), this);
        getServer().getPluginManager().registerEvents(new SpiderAbility(), this);
        getServer().getPluginManager().registerEvents(new WitherAbility(), this);
        getServer().getPluginManager().registerEvents(new WitherSwordItem(), this);
        getServer().getPluginManager().registerEvents(new IronGolemAbility(), this);
        getServer().getPluginManager().registerEvents(new GolemHammerItem(), this);
        getServer().getPluginManager().registerEvents(new MinerPickaxeItem(), this);
        getServer().getPluginManager().registerEvents(new BlazeItem(), this);
        getServer().getPluginManager().registerEvents(new BlazeAbility(), this);
        getServer().getPluginManager().registerEvents(new BreezeWandItem(), this);
        getServer().getPluginManager().registerEvents(new BreezeAbility(), this);
        getServer().getPluginManager().registerEvents(new CannonItem(), this);
        getServer().getPluginManager().registerEvents(new BuilderItem(), this);
        //Crafteos Custom
        RecipeManager.init();
    }

    @Override
    public void onDisable() {
        uhcGame.stop();
    }

    public static UhcGame getUhcGame() {
        return uhcGame;
    }

    private void createFolders() {
        File folder = new File(this.getDataFolder(), "schematics");
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

}
