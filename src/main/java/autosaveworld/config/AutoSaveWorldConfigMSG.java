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

package autosaveworld.config;

import autosaveworld.config.loader.Config;
import autosaveworld.config.loader.ConfigOption;
import autosaveworld.core.GlobalConstants;

import java.io.File;

public class AutoSaveWorldConfigMSG implements Config {

    @ConfigOption(path = "broadcast.pre")
    public final String messageSaveBroadcastPre = "&9AutoSaving";
    @ConfigOption(path = "broadcast.post")
    public final String messageSaveBroadcastPost = "&9AutoSave Complete";
    @ConfigOption(path = "broadcastbackup.pre")
    public final String messageBackupBroadcastPre = "&9AutoBackuping";
    @ConfigOption(path = "broadcastbackup.post")
    public final String messageBackupBroadcastPost = "&9AutoBackup Complete";
    @ConfigOption(path = "broadcastpurge.pre")
    public final String messagePurgeBroadcastPre = "&9AutoPurging";
    @ConfigOption(path = "broadcastpurge.post")
    public final String messagePurgeBroadcastPost = "&9AutoPurge Complete";
    @ConfigOption(path = "autorestart.restarting")
    public final String messageAutoRestart = "&9Server is restarting";
    @ConfigOption(path = "autorestart.countdown")
    public final String messageAutoRestartCountdown = "&9Server will restart in {SECONDS} seconds";
    @ConfigOption(path = "worldregen.kickmessage")
    public final String messageWorldRegenKick = "&9Server is regenerating map, please come back later";
    @ConfigOption(path = "insufficentpermissions")
    public final String messageInsufficientPermissions = "&cYou do not have access to that command.";

    @Override
    public File getFile() {
        return GlobalConstants.getMessageConfigPath();
    }

}
