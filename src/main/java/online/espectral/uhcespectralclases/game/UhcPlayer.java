package online.espectral.uhcespectralclases.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
import java.util.UUID;

public class UhcPlayer {
    private final UUID uuid;
    private UhcClass uhcClass;
    private Player player;
    private UhcGame game;
    private boolean scaleState;
    private boolean sizeChanged;
    private boolean seeCooldown = true;
    private boolean enraged;
    private boolean blazeAcive;
    private WandAbility ability = WandAbility.HEAL;
    private BuilderStructure structure = BuilderStructure.WALL;
    private Collection<PotionEffect> savedEffects;

    public UhcPlayer(UUID uuid) {
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(uuid);
    }
    public UhcGame getGame() {
        return this.game;
    }
    public void setGame(UhcGame game) {
        this.game = game;
    }
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
    public UUID getUuid() {
        return this.uuid;
    }
    public UhcClass getUhcClass() {
        return this.uhcClass;
    }
    public void setUhcClass(UhcClass uhcClass) {
        this.uhcClass = uhcClass;
    }
    public boolean hasUhcClass() {
        return uhcClass != null;
    }
    public boolean getScaleState() {
        return this.scaleState;
    }
    public void setScaleState(boolean state) {
        this.scaleState = state;
    }
    public void setSeeCooldown(boolean canSee) {
        this.seeCooldown = canSee;
    }
    public boolean canSeeCooldown() {
        return this.seeCooldown;
    }
    public WandAbility getAbility() {
        return this.ability;
    }
    public void setAbility(WandAbility ability) {
        this.ability = ability;
    }
    public void setSizeChanged(boolean sizeChanged) {
        this.sizeChanged = sizeChanged;
    }
    public boolean isSizeChanged() {
        return this.sizeChanged;
    }
    public boolean isEnraged() {
        return this.enraged;
    }
    public void setEnraged(boolean enraged) {
        this.enraged = enraged;
    }
    public boolean isBlazeActive() {
        return this.blazeAcive;
    }
    public void setBlazeAcive(boolean blazeAcive) {
        this.blazeAcive = blazeAcive;
    }
    public BuilderStructure getStructure() {
        return this.structure;
    }
    public void setStructure(BuilderStructure structure) {
        this.structure = structure;
    }
    public void setDefaultConfig() {
        this.structure = BuilderStructure.WALL;
        this.ability = WandAbility.HEAL;
        this.seeCooldown = true;
    }
    public void saveEffects(Collection<PotionEffect> savedEffects) {
        this.savedEffects = savedEffects;
    }
    public Collection<PotionEffect> getSavedEffects() {
        return this.savedEffects;
    }
}
