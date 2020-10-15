package io.github.haykam821.pinghats.item;

import io.github.haykam821.pinghats.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ModItems {
	PING_SUBWOOFER("ping_subwoofer", new Item(new Item.Settings().group(ItemGroup.MATERIALS))),
	DIRECT_PING_HAT("direct_ping_hat", new DirectPingHatItem(new PingHatMaterial("direct_ping"), new Item.Settings().group(ItemGroup.COMBAT))),
	EVERYONE_PING_HAT("everyone_ping_hat", new EveryonePingHatItem(new PingHatMaterial("everyone_ping"), new Item.Settings().group(ItemGroup.COMBAT))),
	HERE_PING_HAT("here_ping_hat", new HerePingHatItem(new PingHatMaterial("here_ping"), new Item.Settings().group(ItemGroup.COMBAT))),
	SELF_PING_HAT("self_ping_hat", new SelfPingHatItem(new PingHatMaterial("self_ping"), new Item.Settings().group(ItemGroup.COMBAT))),
	SOMEONE_PING_HAT("someone_ping_hat", new SomeonePingHatItem(new PingHatMaterial("someone_ping"), new Item.Settings().group(ItemGroup.COMBAT)));

	private final Item item;

	private ModItems(String path, Item item) {
		Identifier id = new Identifier(Main.MOD_ID, path);

		this.item = item;
		Registry.register(Registry.ITEM, id, item);
	}

	public Item getItem() {
		return this.item;
	}

	public static void register() {
		return;
	}
}