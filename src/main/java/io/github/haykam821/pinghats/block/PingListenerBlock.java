package io.github.haykam821.pinghats.block;

import java.util.Random;

import io.github.haykam821.pinghats.PingType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class PingListenerBlock extends FacingBlock {
	public static final BooleanProperty POWERED = Properties.POWERED;

	private final PingType pingType;

	public PingListenerBlock(PingType pingType, Block.Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState()
			.with(FACING, Direction.SOUTH)
			.with(POWERED, false));

		this.pingType = pingType;
	}

	public void pulse(ServerWorld world, BlockPos pos) {
		if (!world.isClient() && !world.getBlockTickScheduler().isScheduled(pos, this)) {
			world.getBlockTickScheduler().schedule(pos, this, 2);
		}
	}

	public boolean matchesPingType(PingType pingType) {
		return this.pingType == null || pingType == this.pingType;
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (state.get(POWERED)) {
			world.setBlockState(pos, state.with(POWERED, false), 2);
		} else {
			world.setBlockState(pos, state.with(POWERED, true), 2);
			world.getBlockTickScheduler().schedule(pos, this, 2);
		}
		world.updateNeighbors(pos, this);
	}

	@Override
	public boolean emitsRedstonePower(BlockState state) {
		return true;
	}

	@Override
	public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		return state.getWeakRedstonePower(world, pos, direction);
	}

	@Override
	public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		return state.get(POWERED) && state.get(FACING) == direction ? 15 : 0;
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(FACING, context.getPlayerLookDirection());
	}

	@Override
	public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, POWERED);
	}
}