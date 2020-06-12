package perfect.galaxy.zones.utils;

import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;

public class PSaveInventory {

    private ItemStack[] inventory;
    private ItemStack[] armor;
    private GameMode gameMode;
    private boolean fly;

    public PSaveInventory(ItemStack[] inventory, ItemStack[] armor, GameMode gameMode, boolean fly) {
        this.inventory = inventory;
        this.armor = armor;
        this.gameMode = gameMode;
        this.fly = fly;
    }

    public ItemStack[] getInventory() {
        return inventory;
    }

    public void setInventory(ItemStack[] inventory) {
        this.inventory = inventory;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public void setArmor(ItemStack[] armor) {
        this.armor = armor;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public boolean isFly() {
        return fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }
}