package perfect.galaxy.zones.files;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public interface PFiles {

    String getString (String path);
    String parseColor (String s);
    List<String> getList (String path);
    List<String> parseColorList (List < String > list);
    int getInt(String path);
    boolean getBoolean(String path);
    ConfigurationSection getConfigurationSection(String path);
    boolean contains(String path);
    void set(String path, Object value);

}