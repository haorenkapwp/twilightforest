package twilightforest.world;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import twilightforest.block.BlockTFPlant;
import twilightforest.block.TFBlocks;
import twilightforest.block.enums.PlantVariant;


public class TFGenPlantRoots extends TFGenerator
{
    @Override
    public boolean generate(World par1World, Random par2Random, BlockPos pos)
    {
        int copyX = pos.getX();
        int copyZ = pos.getZ();
        
        for (; pos.getY() > 5; pos = pos.down())
        {
            if (par1World.isAirBlock(pos) && BlockTFPlant.canPlaceRootBelow(par1World, pos.up()) && par2Random.nextInt(6) > 0)
            {
                par1World.setBlockState(pos, TFBlocks.plant.getDefaultState().withProperty(BlockTFPlant.VARIANT, PlantVariant.ROOT_STRAND), 2);
            }
            else
            {
                pos = new BlockPos(
                        copyX + par2Random.nextInt(4) - par2Random.nextInt(4),
                        pos.getY(),
                        copyZ + par2Random.nextInt(4) - par2Random.nextInt(4)
                );
            }
        }

        return true;
    }
}
