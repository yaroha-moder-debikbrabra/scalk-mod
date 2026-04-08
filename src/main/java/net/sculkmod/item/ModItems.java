package net.sculkmod.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CONCENTRATED_SHARD = Registry.register(Registries.ITEM, 
        Identifier.of("sculkmod", "concentrated_echo_shard"), new Item(new Item.Settings()));
    
    public static final Item SCULK_SPYGLASS = Registry.register(Registries.ITEM, 
        Identifier.of("sculkmod", "sculk_spyglass"), new SculkSpyglass(new Item.Settings().maxCount(1)));

    public static void register() {}
}