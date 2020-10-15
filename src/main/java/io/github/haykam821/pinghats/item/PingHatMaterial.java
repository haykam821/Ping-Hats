package io.github.haykam821.pinghats.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class PingHatMaterial implements ArmorMaterial {
	private final String name;

	public PingHatMaterial(String name) {
		this.name = name;
	}

	@Override
	public int getDurability(EquipmentSlot slot) {
		return 65;
	}

	@Override
	public int getEnchantability() {
		return 15;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
	}

	@Override
	public float getKnockbackResistance() {
		return 0;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return 1;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(Items.LEATHER);
	}

	@Override
	public float getToughness() {
		return 0;
	}
}