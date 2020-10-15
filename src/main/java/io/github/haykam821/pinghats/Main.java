package io.github.haykam821.pinghats;

import io.github.haykam821.pinghats.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
	public static final String MOD_ID = "pinghats";

	@Override
	public void onInitialize() {
		ModItems.register();
	}
}