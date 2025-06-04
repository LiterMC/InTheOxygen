package com.github.litermc.intheair.api.block;

public enum AirFlow {
	/**
	 * Gas does not go through the surface at all
	 */
	NONE,
	/**
	 * Gas only outflow through the surface to another block
	 */
	OUTFLOW,
	/**
	 * Gas only inflow through the surface from another block
	 */
	INFLOW,
	/**
	 * Gas are free to flow in any direction through the surface.
	 */
	BOTH;
}
