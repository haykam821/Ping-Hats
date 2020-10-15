package io.github.haykam821.pinghats.item;

import java.util.Collection;
import java.util.Optional;

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public abstract class PingHatItem extends ArmorItem {
	public PingHatItem(Item.Settings settings) {
		super(ArmorMaterials.LEATHER, EquipmentSlot.HEAD, settings);
	}

	public abstract Collection<ServerPlayerEntity> getTargets(ItemStack stack, LivingEntity user, ServerWorld world);

	public SoundEvent getPingSound(ItemStack stack) {
		CompoundTag tag = stack.getTag();
		if (tag != null && tag.contains("PingSound", NbtType.STRING)) {
			Optional<SoundEvent> maybeSoundEvent = Registry.SOUND_EVENT.getOrEmpty(Identifier.tryParse(tag.getString("PingSound")));
			if (maybeSoundEvent.isPresent()) {
				return maybeSoundEvent.get();
			}
		}

		return SoundEvents.BLOCK_BELL_USE;
	}

	public void ping(ItemStack stack, LivingEntity user, ServerWorld world) {
		Collection<ServerPlayerEntity> targets = this.getTargets(stack, user, world);
		SoundEvent pingSound = this.getPingSound(stack);

		for (ServerPlayerEntity target : targets) {
			target.playSound(pingSound, SoundCategory.RECORDS, 1, 1);
		}
	}

	public boolean canPing(double fallDistance, ItemStack feetStack) {
		Item feedItem = feetStack.getItem();
		if (feedItem instanceof ArmorItem && ((ArmorItem) feedItem).getMaterial() == ArmorMaterials.LEATHER) {
			return fallDistance > 5;
		}

		return true;
	}
}