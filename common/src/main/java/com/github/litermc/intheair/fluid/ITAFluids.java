package com.github.litermc.intheair.fluid;

import com.github.litermc.intheair.Constants;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

public final class ITAFluids {
	public static final FlowingFluid HYDROGEN = register("hydrogen", new HydrogenFluid.Source());
	public static final FlowingFluid HYDROGEN_FLOWING = register("hydrogen_flowing", new HydrogenFluid.Flowing());
	public static final FlowingFluid OXYGEN = register("oxygen", new OxygenFluid.Source());
	public static final FlowingFluid OXYGEN_FLOWING = register("oxygen_flowing", new OxygenFluid.Flowing());

	private ITAFluids() {}

	private static final <T extends Fluid> T register(String name, T fluid) {
		return register(new ResourceLocation(Constants.MOD_ID, name), fluid);
	}

	private static final <T extends Fluid> T register(ResourceLocation id, T fluid) {
		return fluid;
	}
}
