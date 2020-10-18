package io.github.haykam821.pinghats;

import io.github.haykam821.pinghats.block.ModBlocks;
import io.github.haykam821.pinghats.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static final String MOD_ID = "pinghats";

	private static final Identifier PING_RECEIVED_ID = new Identifier(MOD_ID, "item.ping_hat.ping_received");
	public static final SoundEvent PING_RECEIVED = new SoundEvent(PING_RECEIVED_ID);

	private static final Identifier PING_LISTENERS_ID = new Identifier(MOD_ID, "ping_listeners");
	public static final Tag<Block> PING_LISTENERS = TagRegistry.block(PING_LISTENERS_ID);

	@Override
	public void onInitialize() {
		Registry.register(Registry.SOUND_EVENT, PING_RECEIVED_ID, PING_RECEIVED);

		ModBlocks.register();
		ModItems.register();
	}
}