package com.github.litermc.intheair.gas;

import com.github.litermc.intheair.api.gas.Gas;
import com.github.litermc.intheair.api.gas.GasStack;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class HydrogenGas extends Gas {
	public HydrogenGas(final Properties props) {
		super(props);
	}

	@Override
	public void onEntityTick(final Entity entity, final GasStack gas) {
	}

	@Override
	public void onBlockTick(final Level level, final BlockPos pos, final Direction direction, final GasStack gas) {
	}
}
