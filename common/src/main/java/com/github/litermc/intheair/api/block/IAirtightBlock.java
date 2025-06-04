package com.github.litermc.intheair.api.block;

import net.minecraft.core.Direction;

public interface IAirtightBlock {
	AirFlow getAirFlow(Direction face);
}
