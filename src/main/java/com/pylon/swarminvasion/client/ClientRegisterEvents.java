package com.pylon.swarminvasion.client;

import com.pylon.swarminvasion.SwarmInvasion;
import com.pylon.swarminvasion.client.render.entity.ScampRenderer;
import com.pylon.swarminvasion.registry.ModEntities;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = SwarmInvasion.MODID, value = Dist.CLIENT)
public class ClientRegisterEvents {

	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
	{
		event.registerEntityRenderer(ModEntities.SCAMP.get(), ScampRenderer::new);
	}
	
}
