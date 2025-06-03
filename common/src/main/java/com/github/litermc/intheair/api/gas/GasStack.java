package com.github.litermc.intheair.api.gas;

import com.github.litermc.intheair.gas.Gases;
import com.github.litermc.intheair.platform.PlatformHelper;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public final class GasStack {
	public static final GasStack EMPTY = new GasStack(null, 0);

	private final Gas gas;
	private int mass;

	public GasStack(Gas gas) {
		this(gas, 1);
	}

	public GasStack(Gas gas, int mass) {
		this.gas = gas;
		this.mass = mass;
	}

	public Gas getGas() {
		return this.gas;
	}

	public int getMass() {
		return this.mass;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

	public boolean isEmpty() {
		return this == EMPTY || this.gas == Gases.AIR || this.mass == 0;
	}

	public void grow(int mass) {
		this.mass += mass;
	}

	public void shrink(int mass) {
		this.mass -= mass;
	}

	public GasStack split(int mass) {
		mass = Math.min(mass, this.mass);
		final GasStack splited = this.copyWithMass(mass);
		this.shrink(mass);
		return splited;
	}

	public GasStack copy() {
		return new GasStack(this.gas, this.mass);
	}

	public GasStack copyAndClear() {
		if (this.isEmpty()) {
			return EMPTY;
		}
		final GasStack copied = this.copy();
		this.setMass(0);
		return copied;
	}

	public GasStack copyWithMass(int mass) {
		return new GasStack(this.gas, mass);
	}

	@Override
	public int hashCode() {
		return this.gas.hashCode() * 31 + this.mass;
	}

	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof GasStack stack && stack.gas == this.gas && stack.mass == this.mass);
	}

	@Override
	public String toString() {
		return this.mass + " " + this.gas;
	}

	public CompoundTag writeToNBT(CompoundTag data) {
		data.putString("Gas", this.gas.getRegistryName().toString());
		data.putInt("Mass", this.mass);
		return data;
	}

	public static GasStack readFromNBT(CompoundTag data) {
		Gas gas = PlatformHelper.get().tryGetRegistryObject(Gas.REGISTRY_KEY, new ResourceLocation(data.getString("Gas")));
		if (gas == null) {
			gas = Gases.AIR;
		}
		final int mass = data.getInt("Mass");
		return new GasStack(gas, mass);
	}
}
