/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package autosaveworld.features.purge.weregen;

import autosaveworld.core.logging.MessageLogger;
import autosaveworld.features.purge.weregen.UtilClasses.BlockToPlaceBack;
import autosaveworld.features.purge.weregen.UtilClasses.ItemSpawnListener;
import autosaveworld.features.purge.weregen.WorldEditRegeneration.WorldEditRegenrationInterface;
import autosaveworld.utils.BukkitUtils;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.reorder.MultiStageReorder.PlacementPriority;
import com.sk89q.worldedit.math.BlockVector2;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockCategories;
import com.sk89q.worldedit.world.block.BlockStateHolder;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

@SuppressWarnings("deprecation")
public class BukkitAPIWorldEditRegeneration implements WorldEditRegenrationInterface {

    private ItemSpawnListener itemremover = new ItemSpawnListener();
    private static final Map<BlockType, PlacementPriority> priorityMap = new HashMap<>();

    // taken from WorldEdit MultiStageReorder class

    /*
     * TODO
     * Проверить, нужно ли обновить данный список
     */
    static {
        // Late
        priorityMap.put(BlockTypes.WATER, PlacementPriority.LATE);
        priorityMap.put(BlockTypes.LAVA, PlacementPriority.LATE);
        priorityMap.put(BlockTypes.SAND, PlacementPriority.LATE);
        priorityMap.put(BlockTypes.GRAVEL, PlacementPriority.LATE);

        // Late
        BlockCategories.SAPLINGS.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.LAST));
        BlockCategories.FLOWER_POTS.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.LAST));
        BlockCategories.BUTTONS.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.LAST));
        BlockCategories.ANVIL.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.LAST));
        BlockCategories.WOODEN_PRESSURE_PLATES.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.LAST));
        BlockCategories.CARPETS.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.LAST));
        BlockCategories.RAILS.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.LAST));
        BlockCategories.BEDS.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.LAST));
        BlockCategories.SMALL_FLOWERS.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.LAST));
        priorityMap.put(BlockTypes.BLACK_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.BLUE_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.BROWN_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.CYAN_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.GRAY_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.GREEN_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.LIGHT_BLUE_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.LIGHT_GRAY_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.LIME_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.MAGENTA_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.ORANGE_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.PINK_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.PURPLE_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.RED_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.WHITE_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.YELLOW_BED, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.GRASS, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.TALL_GRASS, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.ROSE_BUSH, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.DANDELION, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.BROWN_MUSHROOM, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.RED_MUSHROOM, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.FERN, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.LARGE_FERN, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.OXEYE_DAISY, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.AZURE_BLUET, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.TORCH, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.WALL_TORCH, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.FIRE, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.REDSTONE_WIRE, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.CARROTS, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.POTATOES, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.WHEAT, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.BEETROOTS, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.COCOA, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.LADDER, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.LEVER, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.REDSTONE_TORCH, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.REDSTONE_WALL_TORCH, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.SNOW, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.NETHER_PORTAL, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.END_PORTAL, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.REPEATER, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.VINE, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.LILY_PAD, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.NETHER_WART, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.PISTON, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.STICKY_PISTON, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.TRIPWIRE_HOOK, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.TRIPWIRE, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.STONE_PRESSURE_PLATE, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.COMPARATOR, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.IRON_TRAPDOOR, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.ACACIA_TRAPDOOR, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.BIRCH_TRAPDOOR, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.DARK_OAK_TRAPDOOR, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.JUNGLE_TRAPDOOR, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.OAK_TRAPDOOR, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.SPRUCE_TRAPDOOR, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.DAYLIGHT_DETECTOR, PlacementPriority.LAST);
        priorityMap.put(BlockTypes.CAKE, PlacementPriority.LAST);

        // Final
        BlockCategories.DOORS.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.FINAL));
        BlockCategories.BANNERS.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.FINAL));
        BlockCategories.SIGNS.getAll().forEach(type -> priorityMap.put(type, PlacementPriority.FINAL));
        priorityMap.put(BlockTypes.SIGN, PlacementPriority.FINAL);
        priorityMap.put(BlockTypes.WALL_SIGN, PlacementPriority.FINAL);
        priorityMap.put(BlockTypes.CACTUS, PlacementPriority.FINAL);
        priorityMap.put(BlockTypes.SUGAR_CANE, PlacementPriority.FINAL);
        priorityMap.put(BlockTypes.PISTON_HEAD, PlacementPriority.FINAL);
        priorityMap.put(BlockTypes.MOVING_PISTON, PlacementPriority.FINAL);
    }

    private static <B extends BlockStateHolder<B>> PlacementPriority getPlacementPriority(B block) {
        return priorityMap.getOrDefault(block.getBlockType(), PlacementPriority.FIRST);
    }

    @Override
    public void regenerateRegion(World world, org.bukkit.util.Vector minpoint, org.bukkit.util.Vector maxpoint) {
        BlockVector3 minbpoint = BlockVector3.at(minpoint.getX(), minpoint.getY(), minpoint.getZ());
        BlockVector3 maxbpoint = BlockVector3.at(maxpoint.getX(), maxpoint.getY(), maxpoint.getZ());
        regenerateRegion(world, minbpoint, maxbpoint);
    }

    @Override
    public void regenerateRegion(World world, BlockVector3 minpoint, BlockVector3 maxpoint) {
        BukkitWorld bw = new BukkitWorld(world);
        EditSession es = WorldEdit.getInstance().getEditSessionFactory().getEditSession(bw, Integer.MAX_VALUE);
        es.setFastMode(true);
        int maxy = bw.getMaxY() + 1;
        Region region = new CuboidRegion(bw, minpoint, maxpoint);
        LinkedList<BlockToPlaceBack> placeBackQueue = new LinkedList<>();

        // register listener that will prevent trash items from spawning
        BukkitUtils.registerListener(itemremover);

        // first save all blocks that are inside affected chunks but outside the region
        for (BlockVector2 chunk : region.getChunks()) {
            BlockVector3 min = BlockVector3.at(chunk.getBlockX() * 16, 0, chunk.getBlockZ() * 16);
            for (int x = 0; x < 16; ++x) {
                for (int y = 0; y < maxy; ++y) {
                    for (int z = 0; z < 16; ++z) {
                        BlockVector3 pt = min.add(x, y, z);
                        if (!region.contains(pt)) {
                            placeBackQueue.add(new BlockToPlaceBack(pt, es.getBlock(pt).toBaseBlock()));
                        }
                    }
                }
            }
        }

        //TODO: Set blocks that has tileentity to air first

        // regenerate all affected chunks
        for (BlockVector2 chunk : region.getChunks()) {
            try {
                world.regenerateChunk(chunk.getBlockX(), chunk.getBlockZ());
            } catch (Exception t) {
                MessageLogger.exception("Unable to regenerate chunk " + chunk.getBlockX() + " " + chunk.getBlockZ(), t);
            }
        }

        // set all blocks that were outside the region back
        for (PlaceBackStage stage : placeBackStages) {
            stage.processBlockPlaceBack(world, es, placeBackQueue);
        }

        // unregister listener that prevents item drop
        BukkitUtils.unregisterListener(itemremover);
    }

    private static PlaceBackStage[] placeBackStages = new PlaceBackStage[]{
            // normal stage place back
            new PlaceBackStage(block -> !getPlacementPriority(block).equals(PlacementPriority.LAST) && !getPlacementPriority(block).equals(PlacementPriority.FINAL)),
            // last stage place back
            new PlaceBackStage(block -> getPlacementPriority(block).equals(PlacementPriority.LAST)),
            // final stage place back
            new PlaceBackStage(block -> getPlacementPriority(block).equals(PlacementPriority.FINAL))
    };

    private static class PlaceBackStage {

        public interface PlaceBackCheck {
            boolean shouldPlaceBack(BaseBlock block);
        }

        private PlaceBackCheck check;

        public PlaceBackStage(PlaceBackCheck check) {
            this.check = check;
        }

        public void processBlockPlaceBack(World world, EditSession es, LinkedList<BlockToPlaceBack> placeBackQueue) {
            Iterator<BlockToPlaceBack> entryit = placeBackQueue.iterator();
            while (entryit.hasNext()) {
                BlockToPlaceBack blockToPlaceBack = entryit.next();
                BaseBlock block = blockToPlaceBack.getBlock();
                if (check.shouldPlaceBack(block)) {
                    BlockVector3 pt = blockToPlaceBack.getPosition();
                    try {
                        // set block to air to fix one really weird problem
                        world.getBlockAt(pt.getBlockX(), pt.getBlockY(), pt.getBlockZ()).setType(Material.AIR);
                        // set block back if it is not air
                        if (!block.getBlockType().equals(BlockTypes.AIR)) {
                            es.rawSetBlock(pt, block);
                        }
                    } catch (Exception t) {
                        MessageLogger.exception("Unable to place back block " + pt.getBlockX() + " " + pt.getBlockY() + " " + pt.getBlockZ(), t);
                    } finally {
                        entryit.remove();
                    }
                }
            }
        }

    }

}
