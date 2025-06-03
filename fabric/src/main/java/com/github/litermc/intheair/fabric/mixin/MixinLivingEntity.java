package com.github.litermc.intheair.fabric.mixin;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntity.class, priority = 2000)
public abstract class MixinLivingEntity extends Entity {
	private MixinLivingEntity() {
		super(null, null);
	}

	@ModifyVariable(
		method = "baseTick()V",
		at = @At("STORE"),
		ordinal = 0,
		slice = @Slice(
			from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;canBreatheUnderwater()Z")
		)
	)
	private boolean setCanBreath(final boolean original) {
		return true;
	}

	@Redirect(
		method = "baseTick()V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/LivingEntity;setAirSupply(I)V",
			ordinal = 0
		),
		slice = @Slice(
			from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;decreaseAirSupply(I)I")
		)
	)
	private void setDecreasedAirSupply(final LivingEntity entity, final int newAirSupply) {
	}

	@Redirect(
		method = "baseTick()V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/entity/LivingEntity;setAirSupply(I)V",
			ordinal = 0
		),
		slice = @Slice(
			from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;increaseAirSupply(I)I")
		)
	)
	private void setIncreasedAirSupply(final LivingEntity self, final int newAirSupply) {
		if (this.getAirSupply() != -20) {
			return;
		}
		this.setAirSupply(0);
		final Vec3 speed = this.getDeltaMovement();

		for (int i = 0; i < 8; i++) {
			double x = this.random.nextDouble() - this.random.nextDouble();
			double y = this.random.nextDouble() - this.random.nextDouble();
			double z = this.random.nextDouble() - this.random.nextDouble();
			this.level().addParticle(ParticleTypes.BUBBLE, this.getX() + x, this.getY() + y, this.getZ() + z, speed.x, speed.y, speed.z);
		}

		this.hurt(this.damageSources().drown(), 2f);
	}
}
