package twilightforest.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import twilightforest.TFAchievementPage;
import twilightforest.block.BlockTFDeadrock;
import twilightforest.block.TFBlocks;
import twilightforest.block.enums.DeadrockVariant;
import twilightforest.world.TFGenThorns;
import twilightforest.world.TFWorld;

public class TFBiomeThornlands extends TFBiomeBase {

	private TFGenThorns tfGenThorns;


	public TFBiomeThornlands(BiomeProperties props) {
		super(props);
		
        this.topBlock = TFBlocks.deadrock.getDefaultState().withProperty(BlockTFDeadrock.VARIANT, DeadrockVariant.SURFACE);
        this.fillerBlock = TFBlocks.deadrock.getDefaultState().withProperty(BlockTFDeadrock.VARIANT, DeadrockVariant.CRACKED);

		((TFBiomeDecorator)decorator).hasCanopy = false;
		getTFBiomeDecorator().setTreesPerChunk(-999);
        this.decorator.deadBushPerChunk = 2;
        this.decorator.cactiPerChunk = -9999;
        this.spawnableCreatureList.clear();
        
        this.tfGenThorns = new TFGenThorns();
        
		this.decorator.generateLakes = false;
	}
	
	@Override
    public void decorate(World world, Random rand, BlockPos pos)
    {
        super.decorate(world, rand, pos);
        
        // add thorns!
        for (int i = 0; i < 128; i++)
        {
			int rx = pos.getX() + rand.nextInt(16) + 8;
			int rz = pos.getZ() + rand.nextInt(16) + 8;
			int ry = getGroundLevel(world, new BlockPos(rx, 0, rz));

			this.tfGenThorns.generate(world, rand, new BlockPos(rx, ry, rz));
        } 
    }

	private int getGroundLevel(World world, BlockPos pos) {
		// go from sea level up.  If we get grass, return that, otherwise return the last dirt, stone or gravel we got
		Chunk chunk = world.getChunkFromBlockCoords(pos);
		int lastDirt = TFWorld.SEALEVEL;
		for (int y = TFWorld.SEALEVEL; y < TFWorld.CHUNKHEIGHT - 1; y++) {
			Block blockID = chunk.getBlockState(new BlockPos(pos.getX(), y, pos.getZ())).getBlock();
			// grass = return immediately
			if (blockID == Blocks.GRASS) {
				return y + 1;
			}
			else if (blockID == Blocks.DIRT || blockID == Blocks.STONE || blockID == Blocks.GRAVEL || blockID == Blocks.SANDSTONE || blockID == Blocks.SAND || blockID == Blocks.CLAY || blockID == TFBlocks.deadrock) {
				lastDirt = y + 1;
			}
		}
		
		return lastDirt;
	}

	@Override
	public IBlockState getStoneReplacementState() {
		return TFBlocks.deadrock.getDefaultState().withProperty(BlockTFDeadrock.VARIANT, DeadrockVariant.SOLID);
	}
	
	@Override
	protected Achievement getRequiredAchievement() {
		return TFAchievementPage.twilightProgressGlacier;
	}
}
