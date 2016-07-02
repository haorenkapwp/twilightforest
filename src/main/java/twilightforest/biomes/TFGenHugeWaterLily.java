package twilightforest.biomes;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import twilightforest.block.TFBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;


/**
 * Generate huge lily pads
 * 
 * @author Ben
 *
 */
public class TFGenHugeWaterLily extends WorldGenerator
{

	@Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
    	for (int i = 0; i < 4; i++) {
			BlockPos pos_ = pos.add(
					random.nextInt(8) - random.nextInt(8),
					random.nextInt(4) - random.nextInt(4),
					random.nextInt(8) - random.nextInt(8)
			);

    		if (shouldPlacePadAt(world, pos_)) {
    			world.setBlockState(pos_, TFBlocks.hugeWaterLily.getDefaultState());
    		}
    	}

        return true;
    }

	private boolean shouldPlacePadAt(World world, BlockPos pos) {
		return world.isAirBlock(pos) && world.getBlockState(pos.down()).getMaterial() == Material.WATER;
	}
}
