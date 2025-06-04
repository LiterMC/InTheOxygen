package com.github.litermc.intheair.api.gas;

import com.github.litermc.intheair.Constants;
import com.github.litermc.intheair.api.block.IGasTickableBlock;
import com.github.litermc.intheair.api.entity.IGasTickableEntity;
import com.github.litermc.intheair.platform.PlatformHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public abstract class Gas {
	public static final ResourceKey<Registry<Gas>> REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation(Constants.MOD_ID, "gas"));

	private final Fluid fluid;
	private final int fluidDensity;
	private final Block block;
	private final int blockDensity;

	public Gas(Properties props) {
		this.fluid = props.fluid;
		this.fluidDensity = props.fluidDensity;
		this.block = props.block;
		this.blockDensity = props.blockDensity;
	}

	/**
	 * @return Fluid form of the gas, or {@link Fluids#EMPTY} if no fluid form is presented.
	 */
	public final Fluid getFluidForm() {
		return this.fluid;
	}

	/**
	 * @return The mass (g) of 1B fluid. Will return {@code 0} if no fluid form is presented.
	 */
	public final int getFluidDensity() {
		return this.fluidDensity;
	}

	/**
	 * @return Block form of the gas, or {@link Blocks#AIR} if no block form is presented.
	 */
	public final Block getBlockForm() {
		return this.block;
	}

	/**
	 * @return The mass (g) of a block. Will return {@code 0} if no block form is presented.
	 */
	public final int getBlockDensity() {
		return this.blockDensity;
	}

	public ResourceLocation getRegistryName() {
		return PlatformHelper.get().getRegistryKey(REGISTRY_KEY, this);
	}

	public void onEntityTick(final Entity entity, final GasStack gas) {
		if (entity instanceof IGasTickableEntity tickable) {
			tickable.onGasTick(gas);
		}
	}

	public void onBlockTick(final Level level, final BlockPos pos, final BlockState block, final Direction direction, final GasStack gas) {
		if (block.getBlock() instanceof IGasTickableBlock tickable) {
			tickable.onGasTick(level, pos, block, direction, gas);
		}
	}

	public static class Properties {
		Fluid fluid = Fluids.EMPTY;
		int fluidDensity = 0;
		Block block = Blocks.AIR;
		int blockDensity = 0;

		/**
		 * @param fluid Fluid form of the gas
		 * @param fluidDensity The mass (g) of 1B fluid.
		 */
		public Properties withFluidForm(Fluid fluid, int fluidDensity) {
			this.fluid = fluid;
			this.fluidDensity = fluidDensity;
			return this;
		}

		public Properties withoutFluidForm() {
			this.fluid = Fluids.EMPTY;
			this.fluidDensity = 0;
			return this;
		}

		/**
		 * @param block Block form of the gas
		 * @param blockDensity The mass (g) of a block.
		 */
		public Properties withBlockForm(Block block, int blockDensity) {
			this.block = block;
			this.blockDensity = blockDensity;
			return this;
		}

		public Properties withoutBlockForm() {
			this.block = Blocks.AIR;
			this.blockDensity = 0;
			return this;
		}
	}
}
