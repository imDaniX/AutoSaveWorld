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

package autosaveworld.features.purge.plugins.wg;

import autosaveworld.core.logging.MessageLogger;
import autosaveworld.features.purge.taskqueue.Task;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import java.util.LinkedList;
import java.util.UUID;

public class DomainClearTask implements Task {

    private final ProtectedRegion region;

    public DomainClearTask(ProtectedRegion region) {
        this.region = region;
    }

    private final LinkedList<UUID> uuids = new LinkedList<>();
    private final LinkedList<String> names = new LinkedList<>();

    public void add(UUID uuid) {
        uuids.add(uuid);
    }

    public void add(String name) {
        names.add(name);
    }

    public boolean hasPlayersToClear() {
        return (uuids.size() != 0) || (names.size() != 0);
    }

    public int getPlayersToClearCount() {
        return uuids.size() + names.size();
    }

    @Override
    public boolean doNotQueue() {
        return false;
    }

    @Override
    public void performTask() {
        MessageLogger.debug("Cleaning domain for region " + region.getId());
        DefaultDomain owners = region.getOwners();
        for (UUID uuid : uuids) {
            owners.removePlayer(uuid);
        }
        for (String name : names) {
            owners.removePlayer(name);
        }
        region.setOwners(owners);
        DefaultDomain members = region.getMembers();
        for (UUID uuid : uuids) {
            members.removePlayer(uuid);
        }
        for (String name : names) {
            members.removePlayer(name);
        }
        region.setMembers(members);
    }

}
