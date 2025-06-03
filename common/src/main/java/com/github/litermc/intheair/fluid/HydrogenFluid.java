package com.github.litermc.intheair.fluid;

import com.github.litermc.intheair.api.fluid.IEvaporableFluid;
import com.github.litermc.intheair.api.gas.Gas;
import com.github.litermc.intheair.gas.Gases;
import com.github.litermc.intheair.item.ITAItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class HydrogenFluid extends WaterFluid implements IEvaporableFluid {
	@Override
	public Fluid getFlowing() {
		return ITAFluids.HYDROGEN_FLOWING;
	}

	@Override
	public Fluid getSource() {
		return ITAFluids.HYDROGEN;
	}

	@Override
	public Item getBucket() {
		return ITAItems.HYDROGEN_BUCKET;
	}

	@Override
	public boolean isSame(Fluid other) {
		return other == this.getFlowing() || other == this.getSource();
	}

	@Override
	public boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid other, Direction dir) {
		return false;
	}

	@Override
	public Gas getGasForm() {
		return Gases.HYDROGEN;
	}

	public static class Flowing extends HydrogenFluid {
		@Override
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(FlowingFluid.LEVEL);
		}

		@Override
		public int getAmount(FluidState state) {
			return state.getValue(FlowingFluid.LEVEL);
		}

		@Override
		public boolean isSource(FluidState state) {
			return false;
		}
	}

	public static class Source extends HydrogenFluid {
		@Override
		public int getAmount(FluidState state) {
			return 8;
		}

		@Override
		public boolean isSource(FluidState state) {
			return true;
		}
	}
}
