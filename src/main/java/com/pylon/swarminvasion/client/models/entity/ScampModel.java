package com.pylon.swarminvasion.client.models.entity;

import com.pylon.swarminvasion.SwarmInvasion;
import com.pylon.swarminvasion.common.entity.ScampEntity;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

@OnlyIn(Dist.CLIENT)
public class ScampModel extends DefaultedEntityGeoModel<ScampEntity> {
	
	public ScampModel()
	{
		super(ResourceLocation.withDefaultNamespace(SwarmInvasion.MODID), true);
	}
	
	@Override
	public ResourceLocation getModelResource(ScampEntity scampEntity) 
	{
		return ResourceLocation.fromNamespaceAndPath(SwarmInvasion.MODID, "geo/entity/swarm_invasion_scamp.geo.json");
	}
	
	@Override 
	public ResourceLocation getTextureResource(ScampEntity scampEntity) 
	{
		return ResourceLocation.fromNamespaceAndPath(SwarmInvasion.MODID, "textures/entity/swarm_invasion_scamp.texture.png");
	}
	
	@Override
	public ResourceLocation getAnimationResource(ScampEntity scampEntity) 
	{
		return ResourceLocation.fromNamespaceAndPath(SwarmInvasion.MODID, "animations/entity/swarm_invasion_scamp.animation.json");
	}
	
}
