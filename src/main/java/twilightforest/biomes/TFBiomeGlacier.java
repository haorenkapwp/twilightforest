/**
 * 
 */
package twilightforest.biomes;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import twilightforest.TFAchievementPage;
import twilightforest.TFFeature;
import twilightforest.world.TFGenPenguins;
import twilightforest.world.TFWorld;

/**
 * @author Ben
 *
 */
public class TFBiomeGlacier extends TFBiomeBase {

	public TFBiomeGlacier(BiomeProperties props) {
		super(props);
		
		getTFBiomeDecorator().setTreesPerChunk(1);
		getTFBiomeDecorator().setGrassPerChunk(0);

        getTFBiomeDecorator().canopyPerChunk = -999;
		
        spawnableCreatureList.add(new SpawnListEntry(twilightforest.entity.passive.EntityTFPenguin.class, 10, 4, 4));

	}
	
	@Override
    public WorldGenAbstractTree genBigTreeChance(Random random)
    {
        if(random.nextInt(3) == 0)
        {
            return new WorldGenTaiga1();
        } else
        {
            return new WorldGenTaiga2(true);
        }
    }

    @Override
    public boolean getEnableSnow()
    {
        return true;
    }
    
    /**
     * Required for actual snow?
     */
    @Override
    public boolean canRain()
    {
    	return false;
    }
    
    @Override
    public void decorate(World par1World, Random par2Random, BlockPos pos)
    {
        super.decorate(par1World, par2Random, pos);
        TFGenPenguins penguins = new TFGenPenguins();

        if (par2Random.nextInt(4) == 0)
        {
            int j = pos.getX() + par2Random.nextInt(16) + 8;
            byte byte0 = (byte) TFWorld.SEALEVEL;
            int k = pos.getZ() + par2Random.nextInt(16) + 8;
            penguins.generate(par1World, par2Random, j, byte0, k);
        }
    }

	@Override
	protected Achievement getRequiredAchievement() {
		return TFAchievementPage.twilightProgressUrghast;
	}

	@Override
	public void enforceProgession(EntityPlayer player, World world) {
		if (!world.isRemote && world.getWorldTime() % 60 == 0) {
			player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 3));
		}
		// hint monster?
		if (world.rand.nextInt(4) == 0) {
			TFFeature.iceTower.trySpawnHintMonster(world, player);
		}
	}
}
