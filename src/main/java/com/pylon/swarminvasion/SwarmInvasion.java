package com.pylon.swarminvasion;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.pylon.swarminvasion.config.SwarmInvasionConfig;
import com.pylon.swarminvasion.registry.ModEntities;
import com.pylon.swarminvasion.registry.ModItems;
import com.pylon.swarminvasion.registry.ModRegisterEvents;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SwarmInvasion.MODID)
public class SwarmInvasion {
    
    public static final String MODID = "pylonswarminvasion";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public SwarmInvasion(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        //Register events like adding attributes to entities
        modEventBus.register(ModRegisterEvents.class);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (SwarmInvasion) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        
        // Register the Deferred Register to the mod event bus so entities get registered
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModItems.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, SwarmInvasionConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        

        if (SwarmInvasionConfig.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", SwarmInvasionConfig.MAGIC_NUMBER_INTRODUCTION.get(), SwarmInvasionConfig.MAGIC_NUMBER.getAsInt());

        SwarmInvasionConfig.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
        
        LOGGER.info("Swarm Invasion common setup complete");
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
        	event.accept(ModItems.SCAMP_SPAWN_EGG);
        }
    }
    
    // Client start stuff
    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
    	@SubscribeEvent
    	public static void onClientSetup(FMLClientSetupEvent event) {
    		
    	}
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("Swarm Invasion in onServerStarting method");
    }
    
    @EventBusSubscriber(modid = SwarmInvasion.MODID)
    public static class ClientModEvent
    {
    	@SubscribeEvent
    	public static void onClientSetup(FMLClientSetupEvent event)
    	{
    		
    	}
    }
}
