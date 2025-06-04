package com.github.litermc.intheair.gas;

import com.github.litermc.intheair.Constants;
import com.github.litermc.intheair.api.gas.Gas;
import com.github.litermc.intheair.fluid.ITAFluids;

import net.minecraft.resources.ResourceLocation;

public final class Gases {
	public static final Gas AIR = register("air", new AirGas(new Gas.Properties()));

	public static final Gas HYDROGEN = register("hydrogen", new HydrogenGas(new Gas.Properties().withFluidForm(ITAFluids.HYDROGEN, 70850)));
	public static final Gas OXYGEN = register("oxygen", new OxygenGas(new Gas.Properties().withFluidForm(ITAFluids.HYDROGEN, 1141000)));

	private Gases() {}

	private static final <T extends Gas> T register(String name, T gas) {
		return register(new ResourceLocation(Constants.MOD_ID, name), gas);
	}

	private static final <T extends Gas> T register(ResourceLocation id, T gas) {
		return gas;
	}
}
