package autosaveworld.features.purge.plugins.essentials;

import autosaveworld.core.logging.MessageLogger;
import autosaveworld.features.purge.ActivePlayersList;
import autosaveworld.features.purge.DataPurge;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.UserMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class EssentialsPurge extends DataPurge {

    public EssentialsPurge(ActivePlayersList activeplayerslist) {
        super("Essentials", activeplayerslist);
    }

    @Override
    public void doPurge() {
        UserMap map = JavaPlugin.getPlugin(Essentials.class).getUserMap();
        for (UUID uuid : map.getAllUniqueUsers()) {
            if (!activeplayerslist.isActiveUUID(uuid)) {
                MessageLogger.debug("Essentials user " + uuid + " is inactive. Removing entry and file.");
                map.getUser(uuid).reset();
                incDeleted();
            }
        }
    }

}
