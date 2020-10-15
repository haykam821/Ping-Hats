package io.github.haykam821.pinghats.block;

import java.util.function.Function;

import io.github.haykam821.pinghats.Main;
import io.github.haykam821.pinghats.PingType;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ModBlocks {
	DIRECT_PING_LISTENER("direct_ping_listener", new PingListenerBlock(PingType.DIRECT, getObserverBlockSettings(MaterialColor.YELLOW)), ItemGroup.REDSTONE),
	EVERYONE_PING_LISTENER("everyone_ping_listener", new PingListenerBlock(PingType.EVERYONE, getObserverBlockSettings(MaterialColor.RED)), ItemGroup.REDSTONE),
	HERE_PING_LISTENER("here_ping_listener", new PingListenerBlock(PingType.HERE, getObserverBlockSettings(MaterialColor.ORANGE)), ItemGroup.REDSTONE),
	PING_LISTENER("ping_listener", new PingListenerBlock(null, getObserverBlockSettings(MaterialColor.GRAY)), ItemGroup.REDSTONE),
	SELF_PING_LISTENER("self_ping_listener", new PingListenerBlock(PingType.SELF, getObserverBlockSettings(MaterialColor.BLUE)), ItemGroup.REDSTONE),
	SOMEONE_PING_LISTENER("someone_ping_listener", new PingListenerBlock(PingType.SOMEONE, getObserverBlockSettings(MaterialColor.PURPLE)), ItemGroup.REDSTONE);

	private final Block block;
	private final BlockItem item;

	private ModBlocks(String path, Block block, BlockItem item) {
		Identifier id = new Identifier(Main.MOD_ID, path);

		this.block = block;
		Registry.register(Registry.BLOCK, id, this.block);

		this.item = item;
		Registry.register(Registry.ITEM, id, this.item);
	}

	private ModBlocks(String path, Block block, Function<Block, BlockItem> itemFunction) {
		this(path, block, itemFunction.apply(block));
	}

	private ModBlocks(String path, Block block, ItemGroup group) {
		this(path, block, new BlockItem(block, new Item.Settings().group(group)));
	}

	public Block getBlock() {
		return this.block;
	}

	public BlockItem getItem() {
		return this.item;
	}

	public static void register() {
		return;
	}

	private static Block.Settings getObserverBlockSettings(MaterialColor color) {
		return FabricBlockSettings.copyOf(Blocks.OBSERVER).materialColor(color);
	}
}