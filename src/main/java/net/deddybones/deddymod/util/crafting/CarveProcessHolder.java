package net.deddybones.deddymod.util.crafting;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;

public class CarveProcessHolder {

    private HashMap<String, CarveEventHolder> carveMap;

    public CarveProcessHolder() {
        this.carveMap = new HashMap<>();
    }

    public void put(Player pPlayer, ItemStack pCarver, int pCarverIndex, ItemStack pCarvable, int pCarvableIndex) {
        carveMap.put(pPlayer.getStringUUID(), new CarveEventHolder(pPlayer, pCarver, pCarverIndex, pCarvable, pCarvableIndex));
    }

    public CarveEventHolder get(String pPlayerUUID) { // use to check contents
        return carveMap.get(pPlayerUUID);
    }

    public CarveEventHolder remove(String pPlayerUUID) { // use on final exection
        return carveMap.remove(pPlayerUUID);
    }

    public int numberOfEntries() {
        return carveMap.size();
    }
    public static class CarveEventHolder {

        private Player player;
        private ItemStack carver;
        private int carverIndex;
        private ItemStack carvable;
        private int carvableIndex;
        public CarveEventHolder(Player pPlayer, ItemStack pCarver, int pCarverIndex, ItemStack pCarvable, int pCarvableIndex) {
            this.player         = pPlayer;
            this.carver         = pCarver;
            this.carverIndex    = pCarverIndex;
            this.carvable       = pCarvable;
            this.carvableIndex  = pCarvableIndex;
        }

        public Player getPlayer() {
            return player;
        }

        public ItemStack getCarvable() {
            return carvable;
        }

        public ItemStack getCarver() {
            return carver;
        }

        public int getCarvableIndex() {
            return carvableIndex;
        }

        public int getCarverIndex() {
            return carverIndex;
        }
    }
}
