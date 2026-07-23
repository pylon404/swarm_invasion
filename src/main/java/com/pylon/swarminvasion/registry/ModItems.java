package com.pylon.swarminvasion.registry;

import com.pylon.swarminvasion.SwarmInvasion;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class ModItems {
	
	//Following Kaupenjoe's tutorial for 1.21
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SwarmInvasion.MODID);
	
	public static final DeferredItem<DeferredSpawnEggItem> SCAMP_SPAWN_EGG = ITEMS.register("scamp_spawn_egg",
			() -> new DeferredSpawnEggItem(ModEntities.SCAMP, 0, 0, new Item.Properties()));
	
	public static final DeferredItem<Item> TESTITEM = ITEMS.register("testitem", 
			() -> new Item(new Item.Properties()));
	
	public static void register(IEventBus eventBus)
	{
		ITEMS.register(eventBus);
	}
	
}
