package com.github.litermc.intheair.item;

import com.github.litermc.intheair.Constants;
import com.github.litermc.intheair.fluid.ITAFluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public final class ITAItems {
	public static final Item HYDROGEN_BUCKET = register("hydrogen_bucket", new BucketItem(ITAFluids.HYDROGEN, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final Item OXYGEN_BUCKET = register("oxygen_bucket", new BucketItem(ITAFluids.OXYGEN, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

	private ITAItems() {}

	private static final <T extends Item> T register(String name, T item) {
		return register(new ResourceLocation(Constants.MOD_ID, name), item);
	}

	private static final <T extends Item> T register(ResourceLocation id, T item) {
		return item;
	}
}
