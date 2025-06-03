package com.github.litermc.intheair.api.entity;

import com.github.litermc.intheair.api.gas.GasStack;

public interface IGasTickableEntity {
	void onGasTick(GasStack gas);
}
