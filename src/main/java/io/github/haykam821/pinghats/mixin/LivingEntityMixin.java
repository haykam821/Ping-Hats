package io.github.haykam821.pinghats.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.haykam821.pinghats.item.PingHatItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	private LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow
	public abstract ItemStack getEquippedStack(EquipmentSlot slot);

	@Inject(method = "fall", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;fall(DZLnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)V", shift = At.Shift.BEFORE))
	private void playPingOnFall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition, CallbackInfo ci) {
		if (!this.world.isClient() && this.fallDistance > 0.4f && onGround) {
			ItemStack headStack = this.getEquippedStack(EquipmentSlot.HEAD);
			if (headStack != null && headStack.getItem() instanceof PingHatItem) {
				PingHatItem pingHat = (PingHatItem) headStack.getItem();
				if (pingHat.canPing(this.fallDistance, this.getEquippedStack(EquipmentSlot.FEET))) {
					pingHat.ping(headStack, (LivingEntity) (Object) this, (ServerWorld) this.world);
				}
			}
		}
	}
}