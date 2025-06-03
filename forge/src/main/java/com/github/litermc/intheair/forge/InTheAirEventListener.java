package com.github.litermc.intheair.forge;

import net.minecraftforge.event.entity.living.LivingBreatheEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class InTheAirEventListener {
	private InTheAirEventListener() {}

	@SubscribeEvent
	public static void onLivingBreatheEvent(final LivingBreatheEvent event) {
		event.setCanBreathe(true);
		event.setCanRefillAir(false);
	}
}
