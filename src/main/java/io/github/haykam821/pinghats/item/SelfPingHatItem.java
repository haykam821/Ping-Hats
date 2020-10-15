package io.github.haykam821.pinghats.item;

import java.util.Collection;

import com.google.common.collect.Lists;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class SelfPingHatItem extends PingHatItem {
	public SelfPingHatItem(Item.Settings settings) {
		super(settings);
	}

	@Override
	public Collection<ServerPlayerEntity> getTargets(ItemStack stack, LivingEntity user, ServerWorld world) {
		if (user instanceof ServerPlayerEntity) {
			return Lists.newArrayList((ServerPlayerEntity) user);
		}
		return Lists.newArrayList();
	}
}