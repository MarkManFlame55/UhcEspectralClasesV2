package online.espectral.uhcespectralclases.game;

import online.espectral.uhcespectralclases.UhcEspectralClases;
import online.espectral.uhcespectralclases.classes.*;
import online.espectral.uhcespectralclases.util.ServerMessage;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class AbilityManager {

    static UhcGame uhcGame = UhcEspectralClases.getUhcGame();

    public static void initAbilities() {
        Server server = getServer();
        for (Player player : server.getOnlinePlayers()) {
            if (uhcGame.getPlayer(player.getUniqueId()) != null) {
                UhcPlayer uhcPlayer = uhcGame.getPlayer(player.getUniqueId());
                if (uhcPlayer.getUhcClass() == null) {
                    uhcPlayer.setUhcClass(getRandomEnumValue(UhcClass.class));
                    ServerMessage.unicastError(player, "No seleccionaste una clase al comienzo, por lo que se te ha dado una aleatoria");
                }
                switch (uhcPlayer.getUhcClass()) {
                    case WITCH -> WitchClassAbility.init(uhcPlayer);
                    case BREEZE -> BreezeAbility.init(uhcPlayer);
                    case SPIDER -> SpiderAbility.init(uhcPlayer);
                    case DOLPHIN -> DolphinAbility.init(uhcPlayer);
                    case IRON_GOLEM -> IronGolemAbility.init(uhcPlayer);
                    case WARRIOR -> WarriorAbility.init(uhcPlayer);
                    case GUNNER -> GunnerAbility.init(uhcPlayer);
                    case BUILDER -> BuilderAbility.init(uhcPlayer);
                    case MINER -> MinerAbility.init(uhcPlayer);
                    case ANTMAN -> AntmanClassAbility.init(uhcPlayer);
                    case WITHER -> WitherAbility.init(uhcPlayer);
                    case BLAZE -> BlazeAbility.init(uhcPlayer);
                }
            }
        }
    }
    private static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) {
        Random random = new Random();
        int randomIndex = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[randomIndex];
    }
}
