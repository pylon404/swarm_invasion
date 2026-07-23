package com.pylon.swarminvasion.common.entity;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class ScampEntity extends Monster implements GeoEntity {
	
	//GeckoLib said put this at the top of the entity class
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	
	public ScampEntity(EntityType<? extends Monster> entityType, Level level) 
	{
		super(entityType, level);
		
	}
	
	@Override
	protected void registerGoals() 
	{
		//Float/swim in liquids
		this.goalSelector.addGoal(0, new FloatGoal(this));
		//Target and attack targets
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0F, false));
		//Stroll around but avoid water
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0F));
		
		//Add Players as targets
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<Player>(this, Player.class, false));
	}
	
	public static AttributeSupplier.Builder createAttributes() 
	{
		/*
		 * I wish there was an easier way to just have flat damage reduction. 
		 * I'll look into that later, but coding my own damage reduction because
		 * Minecraft made theirs complicated seems a little pointless. Maybe I could 
		 * look at other mods to see if they did something similar.
		 */
		return Mob.createMobAttributes()
				.add(Attributes.FOLLOW_RANGE, 32.0)
				.add(Attributes.ATTACK_DAMAGE, 8.0)
				.add(Attributes.ATTACK_SPEED, 1.0)
				.add(Attributes.MAX_HEALTH, 20.0)
				.add(Attributes.MOVEMENT_SPEED, 0.30)
				.add(Attributes.ARMOR, 8.0)
				.add(Attributes.ARMOR_TOUGHNESS, 2.0)
				.add(Attributes.STEP_HEIGHT, 1.2)
				.add(Attributes.SAFE_FALL_DISTANCE, 6.0);
				
	}
	
	@Override
	public void registerControllers(ControllerRegistrar controllers) 
	{
		/*
		 * I named the animations in the Blockbench model according to GeckoLib's default
		 * animations so hopefully they will be called without issue. Feels weird putting 
		 * the transition length where I did but that's how to add it in I guess.
		 */
		//GeckoLib's generic walk/idle controller. Simple if/else check of movement if I'm not mistaken.
		controllers.add(DefaultAnimations.genericWalkIdleController(this).transitionLength(2));
		//Use GeckoLib's attack.strike slot for the attack animation
		controllers.add(new AnimationController<>(this, "attack.strike", state -> {
			if(ScampEntity.this.swinging)
				return state.setAndContinue(DefaultAnimations.ATTACK_STRIKE);
			state.resetCurrentAnimation();
			
			return PlayState.STOP;
		}).transitionLength(2));

	}

	//GeckoLib said put this in here
	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

}
