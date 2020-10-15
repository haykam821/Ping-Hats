package io.github.haykam821.pinghats.item;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Lists;

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DirectPingHatItem extends PingHatItem {
	public DirectPingHatItem(Item.Settings settings) {
		super(settings);
	}

	@Override
	public Collection<ServerPlayerEntity> getTargets(ItemStack stack, LivingEntity user, ServerWorld world) {
		CompoundTag tag = stack.getTag();
		if (tag != null) {
			if (tag.containsUuid("Target")) {
				UUID targetUuid = tag.getUuid("Target");
				Entity target = world.getEntity(targetUuid);

				if (target instanceof ServerPlayerEntity) {
					return Lists.newArrayList((ServerPlayerEntity) target);
				}
			}
		}

		return Lists.newArrayList();
	}

	private void setTarget(PlayerEntity target, ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();

		tag.putUuid("Target", target.getUuid());
		tag.putString("TargetName", target.getGameProfile().getName());
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (user.isSneaking()) {
			ItemStack stack = user.getStackInHand(hand);

			this.setTarget(user, stack);
			user.incrementStat(Stats.USED.getOrCreateStat(this));

			return TypedActionResult.success(stack);
		}

		return super.use(world, user, hand);
	}
	
	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity target, Hand hand) {
		if (user.isSneaking() && target instanceof PlayerEntity) {
			this.setTarget((PlayerEntity) target, user.getStackInHand(hand));
			user.incrementStat(Stats.USED.getOrCreateStat(this));

			return ActionResult.SUCCESS;
		}

		return super.useOnEntity(stack, user, target, hand);
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		CompoundTag tag = stack.getTag();
		if (tag != null && tag.contains("TargetName", NbtType.STRING)) {
			tooltip.add(new TranslatableText("item.pinghats.direct_ping_hat.target", tag.getString("TargetName")));
		}
	}
}