package com.github.litermc.intheair.accessor;

import com.github.litermc.intheair.api.gas.Gas;
import com.github.litermc.intheair.api.gas.GasStack;

import java.util.Collection;

public interface ILivingEntityLungAccessor {
	GasStack ita$getGasInEntity(final Gas gas);

	void ita$setGasInEntity(final GasStack gas);

	GasStack ita$addGasInEntity(final GasStack gas);

	Collection<GasStack> ita$getGasesInEntity();

	void ita$clearGasesInEntity();
}
