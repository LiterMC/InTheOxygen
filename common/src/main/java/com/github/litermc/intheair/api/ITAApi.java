package com.github.litermc.intheair.api;

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

	public abstract GasStack getGasInEntity(final LivingEntity entity, final Gas gas);

	public abstract void setGasInEntity(final LivingEntity entity, final GasStack gas);

	public abstract GasStack addGasInEntity(final LivingEntity entity, final GasStack gas);

	public abstract Collection<GasStack> getGasesInEntity(final LivingEntity entity);

	public abstract void clearGasesInEntity(final LivingEntity entity);

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
