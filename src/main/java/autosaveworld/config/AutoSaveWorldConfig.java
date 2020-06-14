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
import autosaveworld.config.loader.postload.AstListAppend;
import autosaveworld.config.loader.postload.DefaultCountdown;
import autosaveworld.config.loader.postload.DefaultDestFolder;
import autosaveworld.config.loader.transform.ConfSectIntHashMap;
import autosaveworld.config.loader.transform.ConfSectStringHashMap;
import autosaveworld.config.loader.transform.ListClone;
import autosaveworld.core.GlobalConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoSaveWorldConfig implements Config {

    // some global variables
    @ConfigOption(path = "var.debug")
    public final boolean varDebug = false;
    @ConfigOption(path = "var.commandsonlyfromconsole")
    public final boolean commandOnlyFromConsole = false;
    // save
    @ConfigOption(path = "save.enabled")
    public final boolean saveEnabled = false;
    @ConfigOption(path = "save.interval")
    public final int saveInterval = 900;
    @ConfigOption(path = "save.broadcast")
    public final boolean saveBroadcast = true;
    @ConfigOption(path = "save.disablestructuresaving")
    public final boolean saveDisableStructureSaving = false;
    @ConfigOption(path = "save.forceregioncachedump")
    public final boolean saveDumpRegionCache = true;
    @ConfigOption(path = "save.onplugindisable")
    public final boolean saveOnASWDisable = false;
    // backup
    @ConfigOption(path = "backup.enabled")
    public final boolean backupEnabled = false;
    @ConfigOption(path = "backup.interval")
    public final int backupInterval = 60 * 60 * 6;
    @ConfigOption(path = "backup.broadcast")
    public final boolean backupBroadcast = true;
    @ConfigOption(path = "backup.savebefore")
    public final boolean backupsaveBefore = true;
    @ConfigOption(path = "backup.rateLimit")
    public final long backupRateLimit = -1;
    // localfs backup
    @ConfigOption(path = "backup.localfs.enabled")
    public final boolean backupLFSEnabled = true;
    @ConfigOption(path = "backup.localfs.destinationfolders", transform = ListClone.class, postload = DefaultDestFolder.class)
    public final List<String> backupLFSExtFolders = new ArrayList<>();
    @ConfigOption(path = "backup.localfs.worlds", transform = ListClone.class, postload = AstListAppend.class)
    public final List<String> backupLFSBackupWorldsList = new ArrayList<>();
    @ConfigOption(path = "backup.localfs.MaxNumberOfWorldsBackups")
    public final int backupLFSMaxNumberOfWorldsBackups = 15;
    @ConfigOption(path = "backup.localfs.pluginsfolder")
    public final boolean backupLFSPluginsFolder = false;
    @ConfigOption(path = "backup.localfs.MaxNumberOfPluginsBackups")
    public final int backupLFSMaxNumberOfPluginsBackups = 15;
    @ConfigOption(path = "backup.localfs.otherfolders", transform = ListClone.class)
    public final List<String> backupLFSOtherFolders = new ArrayList<>();
    @ConfigOption(path = "backup.localfs.MaxNumberOfOtherFoldersBackups")
    public final int backupLFSMaxNumberOfOtherBackups = 15;
    @ConfigOption(path = "backup.localfs.excludefolders", transform = ListClone.class)
    public final List<String> backupLFSExcludeFolders = new ArrayList<>();
    @ConfigOption(path = "backup.localfs.zip")
    public final boolean backupLFSZipEnabled = false;
    // ftp backup
    @ConfigOption(path = "backup.ftp.enabled")
    public final boolean backupFTPEnabled = false;
    @ConfigOption(path = "backup.ftp.hostname")
    public final String backupFTPHostname = "127.0.0.1";
    @ConfigOption(path = "backup.ftp.port")
    public final int backupFTPPort = 21;
    @ConfigOption(path = "backup.ftp.login")
    public final String backupFTPUsername = "user";
    @ConfigOption(path = "backup.ftp.password")
    public final String backupFTPPassworld = "password";
    @ConfigOption(path = "backup.ftp.passive")
    public final boolean backupFTPPassive = false;
    @ConfigOption(path = "backup.ftp.path")
    public final String backupFTPPath = "asw";
    @ConfigOption(path = "backup.ftp.worlds", transform = ListClone.class, postload = AstListAppend.class)
    public final List<String> backupFTPWorldsList = new ArrayList<>();
    @ConfigOption(path = "backup.ftp.pluginsfolder")
    public final boolean backupFTPPluginsFolder = false;
    @ConfigOption(path = "backup.ftp.otherfolders", transform = ListClone.class)
    public final List<String> backupFTPOtherFolders = new ArrayList<>();
    @ConfigOption(path = "backup.ftp.excludefolders", transform = ListClone.class)
    public final List<String> backupFTPExcludeFolders = new ArrayList<>();
    @ConfigOption(path = "backup.ftp.maxNumberOfBackups")
    public final int backupFTPMaxNumberOfBackups = 4;
    @ConfigOption(path = "backup.ftp.zip")
    public final boolean backupFTPZipEnabled = false;
    // sftp backup
    @ConfigOption(path = "backup.sftp.enabled", legacypath = "backup.ftp.sftp")
    public final boolean backupSFTPEnabled = false;
    @ConfigOption(path = "backup.sftp.hostname", legacypath = "backup.ftp.hostname")
    public final String backupSFTPHostname = "127.0.0.1";
    @ConfigOption(path = "backup.sftp.port", legacypath = "backup.ftp.port")
    public final int backupSFTPPort = 22;
    @ConfigOption(path = "backup.sftp.login", legacypath = "backup.ftp.login")
    public final String backupSFTPUsername = "user";
    @ConfigOption(path = "backup.sftp.password", legacypath = "backup.ftp.password")
    public final String backupSFTPPassworld = "password";
    @ConfigOption(path = "backup.sftp.path", legacypath = "backup.ftp.path")
    public final String backupSFTPPath = "asw";
    @ConfigOption(path = "backup.sftp.worlds", legacypath = "backup.ftp.worlds", transform = ListClone.class, postload = AstListAppend.class)
    public final List<String> backupSFTPWorldsList = new ArrayList<>();
    @ConfigOption(path = "backup.sftp.pluginsfolder", legacypath = "backup.ftp.pluginsfolder")
    public final boolean backupSFTPPluginsFolder = false;
    @ConfigOption(path = "backup.sftp.otherfolders", legacypath = "backup.ftp.otherfolders", transform = ListClone.class)
    public final List<String> backupSFTPOtherFolders = new ArrayList<>();
    @ConfigOption(path = "backup.sftp.excludefolders", legacypath = "backup.ftp.excludefolders", transform = ListClone.class)
    public final List<String> backupSFTPExcludeFolders = new ArrayList<>();
    @ConfigOption(path = "backup.sftp.maxNumberOfBackups", legacypath = "backup.ftp.maxNumberOfBackups")
    public final int backupSFTPMaxNumberOfBackups = 4;
    @ConfigOption(path = "backup.sftp.zip", legacypath = "backup.ftp.zip")
    public final boolean backupSFTPZipEnabled = false;
    // script backup
    @ConfigOption(path = "backup.script.enabled")
    public final boolean backupScriptEnabled = false;
    @ConfigOption(path = "backup.script.scriptpaths", transform = ListClone.class)
    public final List<String> backupScriptPaths = new ArrayList<>();
    // dropbox backup
    @ConfigOption(path = "backup.dropbox.enabled")
    public final boolean backupDropboxEnabled = false;
    @ConfigOption(path = "backup.dropbox.token")
    public final String backupDropboxAPPTOKEN = "";
    @ConfigOption(path = "backup.dropbox.path")
    public final String backupDropboxPath = "asw";
    @ConfigOption(path = "backup.dropbox.worlds", transform = ListClone.class, postload = AstListAppend.class)
    public final List<String> backupDropboxWorldsList = new ArrayList<>();
    @ConfigOption(path = "backup.dropbox.pluginsfolder")
    public final boolean backupDropboxPluginsFolder = false;
    @ConfigOption(path = "backup.dropbox.otherfolders", transform = ListClone.class)
    public final List<String> backupDropboxOtherFolders = new ArrayList<>();
    @ConfigOption(path = "backup.dropbox.excludefolders", transform = ListClone.class)
    public final List<String> backupDropboxExcludeFolders = new ArrayList<>();
    @ConfigOption(path = "backup.dropbox.maxNumberOfBackups")
    public final int backupDropboxMaxNumberOfBackups = 4;
    @ConfigOption(path = "backup.dropbox.zip")
    public final boolean backupDropboxZipEnabled = false;
    // google drive backup
    @ConfigOption(path = "backup.googledrive.enabled")
    public final boolean backupGDriveEnabled = false;
    @ConfigOption(path = "backup.googledrive.authfilepath")
    public final String backupGDriveAuthFile = "";
    @ConfigOption(path = "backup.googledrive.rootfolderid")
    public final String backupGDRiveRootFolder = "";
    @ConfigOption(path = "backup.googledrive.path")
    public final String backupGDrivePath = "asw";
    @ConfigOption(path = "backup.googledrive.worlds", transform = ListClone.class, postload = AstListAppend.class)
    public final List<String> backupGDriveWorldsList = new ArrayList<>();
    @ConfigOption(path = "backup.googledrive.pluginsfolder")
    public final boolean backupGDrivePluginsFolder = false;
    @ConfigOption(path = "backup.googledrive.otherfolders", transform = ListClone.class)
    public final List<String> backupGDriveOtherFolders = new ArrayList<>();
    @ConfigOption(path = "backup.googledrive.excludefolders", transform = ListClone.class)
    public final List<String> backupGDriveExcludeFolders = new ArrayList<>();
    @ConfigOption(path = "backup.googledrive.maxNumberOfBackups")
    public final int backupGDriveMaxNumberOfBackups = 4;
    @ConfigOption(path = "backup.googledrive.zip")
    public final boolean backupGDriveZipEnabled = false;
    // purge
    @ConfigOption(path = "purge.enabled")
    public final boolean purgeEnabled = false;
    @ConfigOption(path = "purge.interval")
    public final int purgeInterval = 60 * 60 * 24;
    @ConfigOption(path = "purge.awaytime")
    public final long purgeAwayTime = 60 * 60 * 24 * 30;
    @ConfigOption(path = "purge.ignorednicks", transform = ListClone.class)
    public final List<String> purgeIgnoredNicks = new ArrayList<>();
    @ConfigOption(path = "purge.ignoreduuids", transform = ListClone.class)
    public final List<String> purgeIgnoredUUIDs = new ArrayList<>();
    @ConfigOption(path = "purge.broadcast")
    public final boolean purgeBroadcast = true;
    @ConfigOption(path = "purge.wg.enabled")
    public final boolean purgeWG = true;
    @ConfigOption(path = "purge.wg.regenpurgedregion")
    public final boolean purgeWGRegenRg = false;
    @ConfigOption(path = "purge.wg.noregenoverlapregion")
    public final boolean purgeWGNoregenOverlap = true;
    @ConfigOption(path = "purge.wg.removemembers")
    public final boolean purgeWGRemoveMembers = true;
    @ConfigOption(path = "purge.lwc.enabled")
    public boolean purgeLWC = true;
    @ConfigOption(path = "purge.lwc.deletepurgedblocks")
    public boolean purgeLWCDelProtectedBlocks = false;
    @ConfigOption(path = "purge.permissions.enabled")
    public final boolean purgePerms = true;
    @ConfigOption(path = "purge.permissions.savecmd")
    public String purgePermsSaveCMD = "mansave force";
    @ConfigOption(path = "purge.mywarp.enabled")
    public boolean purgeMyWarp = true;
    @ConfigOption(path = "purge.essentials.enabled")
    public final boolean purgeEssentials = true;
    @ConfigOption(path = "purge.dat.enabled")
    public final boolean purgeDat = true;
    // restart
    @ConfigOption(path = "restart.juststop")
    public final boolean restartJustStop = false;
    @ConfigOption(path = "restart.oncrash.enabled", legacypath = "crashrestart.enabled")
    public final boolean restartOncrashEnabled = false;
    @ConfigOption(path = "restart.oncrash.scriptpath", legacypath = "crashrestart.scriptpath")
    public final String restartOnCrashScriptPath = "";
    @ConfigOption(path = "restart.oncrash.timeout", legacypath = "crashrestart.timeout")
    public final long restartOnCrashTimeout = 60;
    @ConfigOption(path = "restart.oncrash.checkerstartdelay", legacypath = "crashrestart.startdelay")
    public final int restartOnCrashCheckerStartDelay = 20;
    @ConfigOption(path = "restart.oncrash.runonnonpluginstop", legacypath = "crashrestart.runonnonpluginstop")
    public final boolean restartOnCrashOnNonAswStop = false;
    @ConfigOption(path = "restart.auto.enabled", legacypath = "autorestart.enabled")
    public final boolean autoRestart = false;
    @ConfigOption(path = "restart.auto.broadcast", legacypath = "autorestart.broadcast")
    public final boolean autoRestartBroadcast = true;
    @ConfigOption(path = "restart.auto.scriptpath", legacypath = "autorestart.scriptpath")
    public final String autoRestartScriptPath = "";
    @ConfigOption(path = "restart.auto.time", legacypath = "autorestart.time")
    public final List<String> autoRestartTimes = new ArrayList<>();
    @ConfigOption(path = "restart.auto.countdown.enabled", legacypath = "autorestart.countdown.enabled")
    public final boolean autoRestartCountdown = true;
    @ConfigOption(path = "restart.auto.countdown.broadcastonsecond", legacypath = "autorestart.countdown.broadcastonsecond", transform = ListClone.class, postload = DefaultCountdown.class)
    public final List<Integer> autoRestartCountdownSeconds = new ArrayList<>();
    @ConfigOption(path = "restart.auto.commands", transform = ListClone.class, legacypath = "autorestart.commands")
    public final List<String> autoRestartPreStopCommmands = new ArrayList<>();
    // consolecmmand
    @ConfigOption(path = "consolecommand.timemode.enabled")
    public final boolean ccTimesModeEnabled = false;
    @ConfigOption(path = "consolecommand.timemode.times", transform = ConfSectStringHashMap.class)
    public final Map<String, List<String>> ccTimesModeCommands = new HashMap<>();
    @ConfigOption(path = "consolecommand.intervalmode.enabled")
    public final boolean ccIntervalsModeEnabled = false;
    @ConfigOption(path = "consolecommand.intervalmode.intervals", transform = ConfSectIntHashMap.class)
    public final Map<Integer, List<String>> ccIntervalsModeCommands = new HashMap<>();
    // worldregen
    @ConfigOption(path = "worldregen.newseed")
    public final boolean worldRegenRemoveSeedData = false;
    @ConfigOption(path = "worldregen.preserveradius")
    public final int worldRegenPreserveRadius = 0;
    @ConfigOption(path = "worldregen.savewg")
    public final boolean worldRegenSaveWG = true;
    @ConfigOption(path = "worldregen.savefactions")
    public boolean worldRegenSaveFactions = true;
    @ConfigOption(path = "worldregen.savegp")
    public final boolean worldRegenSaveGP = true;
    @ConfigOption(path = "worldregen.savetowny")
    public boolean worldregenSaveTowny = true;
    @ConfigOption(path = "worldregen.savepstones")
    public boolean worldregenSavePStones = true;
    // network watcher
    @ConfigOption(path = "networkwatcher.mainthreadnetaccess.warn")
    public final boolean networkWatcherWarnMainThreadAcc = true;
    @ConfigOption(path = "networkwatcher.mainthreadnetaccess.interrupt")
    public final boolean networkWatcherInterruptMainThreadNetAcc = false;

    @Override
    public File getFile() {
        return GlobalConstants.getMainConfigPath();
    }

}