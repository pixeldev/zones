package perfect.galaxy.zones.files.managers;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import perfect.galaxy.zones.files.PFiles;

import java.util.ArrayList;
import java.util.List;

public class PData implements PFiles {

    private final FileConfiguration data;

    public PData(FileConfiguration data){
        this.data = data;
    }

    @Override
    public String getString(String path) {
        return (data.contains(path)) ? data.getString(path) : "";
    }

    @Override
    public String parseColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @Override
    public List<String> getList(String path) {
        return (data.contains(path)) ? data.getStringList(path) : null;
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
        return (data.contains(path)) ? data.getInt(path) : 0;
    }

    @Override
    public boolean getBoolean(String path) {
        return (data.contains(path)) && data.getBoolean(path);
    }

    @Override
    public ConfigurationSection getConfigurationSection(String path) {
        return (data.contains(path)) ? data.getConfigurationSection(path) : null;
    }

    @Override
    public boolean contains(String path) {
        return data.contains(path);
    }

    @Override
    public void set(String path, Object value) {
        data.set(path, value);
    }
}