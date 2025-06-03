package com.github.litermc.intheair;

import net.minecraft.world.entity.Entity;

public abstract class ITAConfig {
	public static ITAConfig getInstance() {
		return TEST_INSTANCE;
	}

	/**
	 * @return minimum oxygen density to breath in g/m^3
	 */
	public abstract int getMinimumOxygenDensityForBreath();

	/**
	 * @return maximum oxygen density to breath in g/m^3
	 */
	public abstract int getMaximumOxygenDensityForBreath();

	/**
	 * @return minimum air density to live in g/m^3
	 */
	public abstract int getMinimumAirDensityToLive();

	/**
	 * @return maximum air density to live in g/m^3
	 */
	public abstract int getMaximumAirDensityToLive();

	/**
	 * @return lung volume in mB
	 */
	public abstract int getEntityLungVolume(Entity entity);

	/**
	 * @return oxygen consume rate in g/s
	 */
	public abstract int getEntityOxygenConsumeRate(Entity entity);

	private static final ITAConfig TEST_INSTANCE = new ITAConfig() {
		@Override
		public int getMinimumOxygenDensityForBreath() {
			return 1000;
		}

		@Override
		public int getMaximumOxygenDensityForBreath() {
			return 2000;
		}

		@Override
		public int getMinimumAirDensityToLive() {
			return 700;
		}

		@Override
		public int getMaximumAirDensityToLive() {
			return 2500;
		}

		@Override
		public int getEntityLungVolume(Entity entity) {
			return 10;
		}

		@Override
		public int getEntityOxygenConsumeRate(Entity entity) {
			return 8;
		}
	};
}
