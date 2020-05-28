package perfect.zones.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;

public class Files {

    File file;
    File path;
    FileConfiguration configuration;
    Plugin plugin;
    InputStream defaults;

    public Files(String name, String path, InputStream defaults, Plugin plugin){
        this.plugin = plugin;
        this.defaults = defaults;
        this.file = new File(this.plugin.getDataFolder() + path, name);
        this.path = new File(this.plugin.getDataFolder() + path);
        this.configuration = new YamlConfiguration();
        createFile();
    }

    public void save(){
        try {
            String str = this.configuration.saveToString();
            FileWriter fw = new FileWriter(this.file);
            fw.write(str);
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void createFile(){
        if(!this.path.exists())
            this.path.mkdirs();
        if(this.defaults != null)
            try {
                if(!this.file.exists()){
                    InputStream in = this.defaults;
                    OutputStream out = new FileOutputStream(this.file);
                    byte[] buf = new byte[this.defaults.available()];
                    int len;
                    while ((len = in.read(buf)) > 0){
                        out.write(buf, 0 ,len);
                    }
                    out.close();
                    in.close();
                }
                this.configuration = YamlConfiguration.loadConfiguration(this.file);
            } catch (IOException e){
                this.plugin.getLogger().severe("Error while writing configuration file... " + this.file.getName() + "!");
                this.plugin.getLogger().severe("Disabling...");
                this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
                e.printStackTrace();
            }
    }

    public void reload(){
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration getData(){
        return this.configuration;
    }
}