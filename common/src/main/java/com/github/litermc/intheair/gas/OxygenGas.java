package com.github.litermc.intheair.gas;

import com.github.litermc.intheair.api.gas.Gas;
import com.github.litermc.intheair.api.gas.GasStack;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class OxygenGas extends Gas {
	public OxygenGas(final Properties props) {
		super(props);
	}

	@Override
	public void onEntityTick(final Entity entity, final GasStack gas) {
		super.onEntityTick(entity, gas);
	}

	@Override
	public void onBlockTick(final Level level, final BlockPos pos, final BlockState block, final Direction direction, final GasStack gas) {
		super.onBlockTick(level, pos, block, direction, gas);
	}
}
