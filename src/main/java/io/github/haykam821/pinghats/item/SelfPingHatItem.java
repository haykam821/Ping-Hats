package io.github.haykam821.pinghats.item;

import java.util.Collection;

import com.google.common.collect.Lists;

import io.github.haykam821.pinghats.PingType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class SelfPingHatItem extends PingHatItem {
	public SelfPingHatItem(ArmorMaterial material, Item.Settings settings) {
		super(PingType.SELF, material, settings);
	}

	@Override
	public Collection<ServerPlayerEntity> getTargets(ItemStack stack, LivingEntity user, ServerWorld world) {
		if (user instanceof ServerPlayerEntity) {
			return Lists.newArrayList((ServerPlayerEntity) user);
		}
		return Lists.newArrayList();
	}
}