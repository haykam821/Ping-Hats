package io.github.haykam821.pinghats;

import io.github.haykam821.pinghats.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	public static final String MOD_ID = "pinghats";

	private static final Identifier PING_RECEIVED_ID = new Identifier(MOD_ID, "item.ping_hat.ping_received");
	public static final SoundEvent PING_RECEIVED = new SoundEvent(PING_RECEIVED_ID);

	@Override
	public void onInitialize() {
		ModItems.register();
	}
}