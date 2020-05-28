package perfect.zones.commands;

import me.fixeddev.ebcm.parametric.CommandClass;
import me.fixeddev.ebcm.parametric.annotation.ACommand;
import me.fixeddev.ebcm.parametric.annotation.Injected;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@ACommand(names = {"perfectzones", "pzones", "pz"})
public class PrincipalZonesCMD implements CommandClass {

    @ACommand(names = "")
    public boolean mainCommand(@Injected(true)CommandSender sender){
        if(!(sender instanceof Player)){
            sender.sendMessage("Sorry! This command is only available for players!");
            return false;
        }
    }

}