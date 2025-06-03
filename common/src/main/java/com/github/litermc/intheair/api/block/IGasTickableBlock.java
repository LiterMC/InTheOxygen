package com.github.litermc.intheair.api.block;

import com.github.litermc.intheair.api.gas.GasStack;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IGasTickableBlock {
	void onGasTick(Level level, BlockPos pos, BlockState block, Direction direction, GasStack gas);
}
