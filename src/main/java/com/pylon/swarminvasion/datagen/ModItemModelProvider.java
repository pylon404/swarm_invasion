package com.pylon.swarminvasion.datagen;

import com.pylon.swarminvasion.SwarmInvasion;
import com.pylon.swarminvasion.registry.ModItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, SwarmInvasion.MODID, existingFileHelper);
		
	}
	
	@Override 
	protected void registerModels()
	{
		//withExistingParent(ModItems.SCAMP_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
		//this.getBuilder("item/scamp_spawn_egg").parent(new ModelFile.UncheckedModelFile("item/template_spawn_egg"));
		this.spawnEgg(ModItems.SCAMP_SPAWN_EGG);
	}
	
	private void spawnEgg(DeferredItem<?> item) 
	{
		withExistingParent(
				item.getId().getPath(),
				mcLoc("item/template_spawn_egg")
				);
	}

}
