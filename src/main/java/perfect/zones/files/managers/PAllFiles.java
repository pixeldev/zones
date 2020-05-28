package perfect.zones.files.managers;

import org.bukkit.configuration.file.FileConfiguration;

public class PAllFiles {

    private final PData data;
    private final PLang lang;
    private final PConfig config;
    private final PMenu menu;

    public PAllFiles(FileConfiguration config, FileConfiguration data, FileConfiguration lang, FileConfiguration menu) {
        this.data = new PData(data);
        this.menu = new PMenu(menu);
        this.config = new PConfig(config);
        this.lang = new PLang(lang);
    }

    public PData getData() {
        return data;
    }

    public PLang getLang() {
        return lang;
    }

    public PConfig getConfig() {
        return config;
    }

    public PMenu getMenu() {
        return menu;
    }
}