package perfect.galaxy.zones.files.managers;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import perfect.galaxy.zones.files.PFiles;

import java.util.ArrayList;
import java.util.List;

public class PConfig implements PFiles {

    private final FileConfiguration config;

    public PConfig(FileConfiguration config){
        this.config = config;
    }

    @Override
    public String getString(String path) {
        return (config.contains(path)) ? config.getString(path) : "";
    }

    @Override
    public String parseColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @Override
    public List<String> getList(String path) {
        return (config.contains(path)) ? config.getStringList(path) : null;
    }

    @Override
    public List<String> parseColorList(List<String> list) {
        List<String> color = new ArrayList<>();
        for(String s : list){
            color.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return color;
    }

    @Override
    public int getInt(String path) {
        return (config.contains(path)) ? config.getInt(path) : 0;
    }

    @Override
    public boolean getBoolean(String path) {
        return (config.contains(path)) && config.getBoolean(path);
    }

    @Override
    public ConfigurationSection getConfigurationSection(String path) {
        return (config.contains(path)) ? config.getConfigurationSection(path) : null;
    }

    @Override
    public boolean contains(String path) {
        return config.contains(path);
    }

    @Override
    public void set(String path, Object value) {
        config.set(path, value);
    }
}