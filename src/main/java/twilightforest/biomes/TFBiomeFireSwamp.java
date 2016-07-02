package twilightforest.biomes;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.block.BlockTFFireJet;
import twilightforest.block.TFBlocks;
import twilightforest.world.TFGenFireJet;
import twilightforest.world.TFWorld;

public class TFBiomeFireSwamp extends TFBiomeBase {

	protected TFBiomeFireSwamp(BiomeProperties props) {
		super(props);

        getTFBiomeDecorator().setDeadBushPerChunk(2);
        getTFBiomeDecorator().setMushroomsPerChunk(8);
        getTFBiomeDecorator().setReedsPerChunk(4);
        getTFBiomeDecorator().setClayPerChunk(1);
        getTFBiomeDecorator().setTreesPerChunk(3);
        getTFBiomeDecorator().setWaterlilyPerChunk(6);
		
        getTFBiomeDecorator().canopyPerChunk = -999;
        getTFBiomeDecorator().lavaPoolChance = 0.125F;
        getTFBiomeDecorator().mangrovesPerChunk = 3;
	}
	
	@Override
    public WorldGenAbstractTree genBigTreeChance(Random random)
    {
        if (random.nextInt(3) == 0)
        {
            return new WorldGenShrub(3, 0);
        }

        return SWAMP_FEATURE;
    }

    /**
     * Decorate this biome.  This is stolen from the jungle code
     */
    @Override
    public void decorate(World par1World, Random par2Random, int mapX, int mapZ)
    {
    	super.decorate(par1World, par2Random, mapX, mapZ);

    	TFFeature nearFeature = TFFeature.getNearestFeature(mapX >> 4, mapZ >> 4, par1World);
    	if (nearFeature.areChunkDecorationsEnabled) {
    		WorldGenVines worldgenvines = new WorldGenVines();

    		for (int i = 0; i < 50; i++)
    		{
    			int j = mapX + par2Random.nextInt(16) + 8;
    			byte byte0 = (byte) TFWorld.SEALEVEL;
    			int k = mapZ + par2Random.nextInt(16) + 8;
    			worldgenvines.generate(par1World, par2Random, j, byte0, k);
    		}

    		TFGenFireJet genSmoker = new TFGenFireJet(TFBlocks.fireJet, BlockTFFireJet.META_SMOKER);

    		if (par2Random.nextInt(4) == 0)
    		{
    			int j = mapX + par2Random.nextInt(16) + 8;
    			byte byte0 = (byte) TFWorld.SEALEVEL;
    			int k = mapZ + par2Random.nextInt(16) + 8;
    			genSmoker.generate(par1World, par2Random, j, byte0, k);
    		}

    		TFGenFireJet genFireJet = new TFGenFireJet(TFBlocks.fireJet, BlockTFFireJet.META_JET_IDLE);

    		for (int i = 0; i < 1; i++)
    		{
    			int j = mapX + par2Random.nextInt(16) + 8;
    			byte byte0 = (byte) TFWorld.SEALEVEL;
    			int k = mapZ + par2Random.nextInt(16) + 8;
    			genFireJet.generate(par1World, par2Random, j, byte0, k);
    		}
    	}
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x572e23;
    }

    @Override
	@SideOnly(Side.CLIENT)
	public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x64260f;
    }
    
	@Override
	protected Achievement getRequiredAchievement() {
		return TFAchievementPage.twilightProgressLabyrinth;
	}

	@Override
	public void enforceProgession(EntityPlayer player, World world) {
		if (!world.isRemote && world.getWorldTime() % 60 == 0) {
			player.setFire(8);
		}
		// hint monster?
		if (world.rand.nextInt(4) == 0) {
			TFFeature.hydraLair.trySpawnHintMonster(world, player);
		}
	}
}
