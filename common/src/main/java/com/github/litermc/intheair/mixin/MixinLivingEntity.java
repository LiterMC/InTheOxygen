package com.github.litermc.intheair.mixin;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntity.class, priority = 2000)
public abstract class MixinLivingEntity extends Entity {
	private MixinLivingEntity() {
		super(null, null);
	}

	@Shadow
	protected abstract int decreaseAirSupply(int air);

	@Shadow
	protected abstract int increaseAirSupply(int air);

	@Inject(
		method = "baseTick()V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/LivingEntity;isAlive()Z",
			ordinal = 0
		)
	)
	private void baseTick(final CallbackInfo cb) {
		if (!this.isAlive()) {
			return;
		}
		if (this.tickCount % 20 == 10) {
			int air = this.getAirSupply();
			for (int i = 0; i < 16; i++) {
				air = this.decreaseAirSupply(air);
			}
			boolean hasAirSupply = !this.isEyeInFluid(FluidTags.WATER);
			if (hasAirSupply) {
				for (int i = 0; i < 5; i++) {
					air = this.increaseAirSupply(air);
				}
			}
			this.setAirSupply(Math.max(air, -20));
		}
	}
}
