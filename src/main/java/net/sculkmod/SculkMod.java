package net.sculkmod;
import net.fabricmc.api.ModInitializer;
import net.sculkmod.item.ModItems;

public class SculkMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ModItems.register();
    }
}