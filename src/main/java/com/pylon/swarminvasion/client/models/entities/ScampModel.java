package com.pylon.swarminvasion.client.models.entities;

import com.pylon.swarminvasion.common.entity.ScampEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ScampModel extends DefaultedEntityGeoModel<ScampEntity> {
	public ScampModel() {
		super(new ResourceLocation(SwarmInvasion.MODID, "scamp"));
	}
}
