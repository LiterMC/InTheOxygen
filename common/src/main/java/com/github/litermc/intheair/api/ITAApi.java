package com.github.litermc.intheair.api;

import com.github.litermc.intheair.accessor.ILivingEntityLungAccessor;
import com.github.litermc.intheair.api.gas.Gas;
import com.github.litermc.intheair.api.gas.GasStack;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Collection;

public abstract class ITAApi {
	public static ITAApi getInstance() {
		return null;
	}

	private ITAApi() {}

	public GasStack getGasInEntity(final LivingEntity entity, final Gas gas) {
		return ((ILivingEntityLungAccessor) (entity)).ita$getGasInEntity(gas);
	}

	public void setGasInEntity(final LivingEntity entity, final GasStack gas) {
		((ILivingEntityLungAccessor) (entity)).ita$setGasInEntity(gas);
	}

	public GasStack addGasInEntity(final LivingEntity entity, final GasStack gas) {
		return ((ILivingEntityLungAccessor) (entity)).ita$addGasInEntity(gas);
	}

	public Collection<GasStack> getGasesInEntity(final LivingEntity entity) {
		return ((ILivingEntityLungAccessor) (entity)).ita$getGasesInEntity();
	}

	public void clearGasesInEntity(final LivingEntity entity) {
		((ILivingEntityLungAccessor) (entity)).ita$clearGasesInEntity();
	}

	public abstract GasStack getGasInBlock(final Level level, final BlockPos pos, final Direction direction, final Gas gas);

	public abstract void setGasInBlock(final Level level, final BlockPos pos, final Direction direction, final GasStack gas);

	public abstract GasStack addGasInBlock(final Level level, final BlockPos pos, final Direction direction, final GasStack gas);

	public Collection<GasStack> getGasesInBlock(final Level level, final BlockPos pos) {
		return getGasesInBlock(level, pos, null);
	}

	public abstract Collection<GasStack> getGasesInBlock(final Level level, final BlockPos pos, final Direction direction);

	public void clearGasesInBlock(final Level level, final BlockPos pos) {
		clearGasesInBlock(level, pos, null);
	}

	public abstract void clearGasesInBlock(final Level level, final BlockPos pos, final Direction direction);
}
