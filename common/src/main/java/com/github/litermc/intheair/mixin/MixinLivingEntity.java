package com.github.litermc.intheair.mixin;

import com.github.litermc.intheair.ITAConfig;
import com.github.litermc.intheair.accessor.ILivingEntityLungAccessor;
import com.github.litermc.intheair.api.gas.Gas;
import com.github.litermc.intheair.api.gas.GasStack;
import com.github.litermc.intheair.gas.Gases;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Mixin(value = LivingEntity.class, priority = 2000)
public abstract class MixinLivingEntity extends Entity implements ILivingEntityLungAccessor {
	private MixinLivingEntity() {
		super(null, null);
	}

	@Unique
	protected Map<Gas, GasStack> lung = new HashMap<>();

	@Shadow
	protected abstract int decreaseAirSupply(int air);

	@Shadow
	protected abstract int increaseAirSupply(int air);

	@Override
	public GasStack ita$getGasInEntity(final Gas gas) {
		return this.lung.computeIfAbsent(gas, (k) -> new GasStack(k, 0));
	}

	@Override
	public void ita$setGasInEntity(final GasStack gas) {
		if (gas.isEmpty()) {
			this.lung.remove(gas.getGas());
			return;
		}
		this.lung.compute(gas.getGas(), (k, v) -> {
			if (v == null) {
				return gas.copy();
			}
			v.setMass(gas.getMass());
			return v;
		});
	}

	@Override
	public GasStack ita$addGasInEntity(final GasStack gas) {
		return this.lung.compute(gas.getGas(), (k, v) -> {
			if (v == null) {
				return gas.copy();
			}
			v.grow(gas.getMass());
			return v;
		});
	}

	@Override
	public Collection<GasStack> ita$getGasesInEntity() {
		return Collections.unmodifiableCollection(this.lung.values());
	}

	@Override
	public void ita$clearGasesInEntity() {
		this.lung.clear();
	}

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
			final GasStack oxygen = this.ita$getGasInEntity(Gases.OXYGEN);
			final int needsOxygen = ITAConfig.getInstance().getEntityOxygenConsumeRate(this);
			if (oxygen.getMass() >= needsOxygen) {
				oxygen.shrink(needsOxygen);
				for (int i = 0; i < 5; i++) {
					air = this.increaseAirSupply(air);
				}
			}
			this.setAirSupply(Math.max(air, -20));
		}
	}

	@Inject(method = "addAdditionalSaveData", at = @At("RETURN"))
	private void addAdditionalSaveData(final CompoundTag data, final CallbackInfo cb) {
		final ListTag lungData = new ListTag();
		for (final GasStack gas : this.lung.values()) {
			if (!gas.isEmpty()) {
				lungData.add(gas.writeToNBT(new CompoundTag()));
			}
		}
		data.put("Lung", lungData);
	}

	@Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
	private void readAdditionalSaveData(final CompoundTag data, final CallbackInfo cb) {
		final ListTag lungData = data.getList("Lung", Tag.TAG_COMPOUND);
		this.lung.clear();
		for (final Tag gasData : lungData) {
			final GasStack gas = GasStack.readFromNBT((CompoundTag) (gasData));
			this.lung.put(gas.getGas(), gas);
		}
	}
}
