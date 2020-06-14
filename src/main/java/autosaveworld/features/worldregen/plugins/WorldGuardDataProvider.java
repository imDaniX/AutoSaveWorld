/*
  This program is free software; you can redistribute it and/or
  modify it under the terms of the GNU General Public License
  as published by the Free Software Foundation; either version 3
  of the License, or (at your option) any later version.
  <p>
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  <p>
  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package autosaveworld.features.worldregen.plugins;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.GlobalProtectedRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.World;

public class WorldGuardDataProvider extends DataProvider {

    public WorldGuardDataProvider(World world) throws Throwable {
        super(world);
    }

    @Override
    protected void init() {
        for (ProtectedRegion region : WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world)).getRegions().values()) {
            if (region instanceof GlobalProtectedRegion) {
                continue;
            }
            addChunksInBounds(
                    region.getMinimumPoint().getBlockX(),
                    region.getMinimumPoint().getBlockZ(),
                    region.getMaximumPoint().getBlockX(),
                    region.getMaximumPoint().getBlockZ()
            );
        }
    }

}