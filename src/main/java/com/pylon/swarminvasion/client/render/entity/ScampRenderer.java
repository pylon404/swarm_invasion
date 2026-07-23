package com.pylon.swarminvasion.client.render.entity;

import com.pylon.swarminvasion.client.models.entity.ScampModel;
import com.pylon.swarminvasion.common.entity.ScampEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ScampRenderer extends GeoEntityRenderer<ScampEntity> {

	public ScampRenderer(EntityRendererProvider.Context context) 
	{
		super(context, new ScampModel());
	}
	
}
