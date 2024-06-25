package online.espectral.uhcespectralclases.util.worldedit;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public final class  SchematicManager {

    // IMPLEMENTACIÓN DE WORLD EDIT POR PARTE DE CarlosDiamon https://github.com/carlosdiamon

    public static Clipboard getClipBoard(
            @NotNull final File file,
            @NotNull final Logger logger
    ) {
        if (!file.exists()) {
            logger.warning("The schematic file" + file.getName() + " does not exist");
            return null;
        }

        final var format = ClipboardFormats.findByFile(file);

        if (format == null) {
            logger.warning("The schematic file " + file.getName() + " is not a valid schematic");
            return null;
        }

        try (final var reader = format.getReader(new FileInputStream(file))) {
            return reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Region pasteSchematic(
            @NotNull final Clipboard clipboard,
            @NotNull final org.bukkit.World world,
            final int posX,
            final int posY,
            final int posZ
    ) {
        final var worldSq = getWorld(world);
        try (
                final var editSession = WorldEdit.getInstance()
                        .newEditSession(worldSq)
        ) {
            final var location = BlockVector3.at(posX, posY, posZ);
            final var clipboardHolder = new ClipboardHolder(clipboard);
            final var operation = clipboardHolder
                    .createPaste(editSession)
                    .to(location)
                    .copyBiomes(false)
                    .copyEntities(true)
                    .ignoreAirBlocks(true)
                    //.ignoreStructureVoidBlocks(true)
                    .build();

            Operations.complete(operation);
            return new CuboidRegion(worldSq, location, location.add(clipboard.getDimensions()));
        } catch (WorldEditException e) {
            throw new RuntimeException(e);
        }
    }
    public static World getWorld(@NotNull final org.bukkit.World world) {
        return new BukkitWorld(world);
    }
}

