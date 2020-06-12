package perfect.galaxy.zones.files.managers;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import perfect.galaxy.zones.files.PFiles;

import java.util.ArrayList;
import java.util.List;

public class PLang implements PFiles {

    private final FileConfiguration lang;

    public PLang(FileConfiguration lang){
        this.lang = lang;
    }

    @Override
    public String getString(String path) {
        return (lang.contains(path)) ? lang.getString(path) : "";
    }

    @Override
    public String parseColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @Override
    public List<String> getList(String path) {
        return (lang.contains(path)) ? lang.getStringList(path) : null;
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
        return (lang.contains(path)) ? lang.getInt(path) : 0;
    }

    @Override
    public boolean getBoolean(String path) {
        return (lang.contains(path)) && lang.getBoolean(path);
    }

    @Override
    public ConfigurationSection getConfigurationSection(String path) {
        return (lang.contains(path)) ? lang.getConfigurationSection(path) : null;
    }

    @Override
    public boolean contains(String path) {
        return lang.contains(path);
    }

    @Override
    public void set(String path, Object value) {
        lang.set(path, value);
    }
}