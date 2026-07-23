package com.pylon.swarminvasion.registry;

import java.util.function.Supplier;

import com.pylon.swarminvasion.SwarmInvasion;
import com.pylon.swarminvasion.common.entity.ScampEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.resources.ResourceLocation;

public class ModEntities {
	
	//Thanks again to Kaupenjoe for his tutorials on all this stuff
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
			DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, SwarmInvasion.MODID);
	
	/*
	 * I was rather displeased at how un-documented making an entity is on this version of NeoForge. 
	 * And even the documentation that does exist (i.e. Kaupenjoe's videos) tend to be out of date 
	 * when you make stuff 2 years past their release. I had to look at open-source code for other
	 * mods on this version and even they vary in their implementation, possibly due to 
	 * being coded targeted at being multi-platform (like Fabric). 
	 */ 
	public static final ResourceLocation SCAMP_LOCATION = ResourceLocation.fromNamespaceAndPath(SwarmInvasion.MODID, "scamp");
	
	// The .sized part should be the hitbox. I thought it would be 3 dimensions.
	public static final Supplier<EntityType<ScampEntity>> SCAMP = 
			ENTITY_TYPES.register("scamp", () -> EntityType.Builder.of(ScampEntity::new, MobCategory.MONSTER)
			.sized(1.5f, 1.25f)
			.build(SCAMP_LOCATION.toString()));
	
	public static void register(IEventBus eventBus) 
	{
		ENTITY_TYPES.register(eventBus);
	}
	
}
