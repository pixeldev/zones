package perfect.zones.files.managers;

import org.bukkit.plugin.Plugin;
import perfect.zones.files.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PFilesManager {

    private static Plugin plugina = null;

    static Map<String, Files> files = new HashMap<>();

    public static void setPlugin(Plugin plugin) {
        plugina = plugin;
    }

    public static void addFile(String name) {
        files.put(name, new Files(name + ".yml", "", plugina.getResource(name + ".yml"), plugina));
    }

    public static void addFile(String name, String path) {
        files.put(name, new Files(name + ".yml", path, plugina.getResource(name + ".yml"), plugina));
    }

    public static Files getFiles(String name) {
        return files.get(name);
    }

    public static void reloadAll() {
        for (Files f : files.values()) {
            f.reload();
        }
    }

    public static void loadConfigFiler(String path) {
        File folder = new File(path);
        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".yml")) {
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    String name = file.getName().replace(".yml", "");
                    files.put(name, new Files(file.getName(), "", inputStream, plugina));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}