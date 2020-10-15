package io.github.haykam821.pinghats.item;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class HerePingHatItem extends PingHatItem {
	public HerePingHatItem(Item.Settings settings) {
		super(settings);
	}

	@Override
	public Collection<ServerPlayerEntity> getTargets(ItemStack stack, LivingEntity user, ServerWorld world) {
		return new ArrayList<>(world.getEntitiesByClass(ServerPlayerEntity.class, user.getBoundingBox().expand(32), EntityPredicates.VALID_LIVING_ENTITY));
	}
}