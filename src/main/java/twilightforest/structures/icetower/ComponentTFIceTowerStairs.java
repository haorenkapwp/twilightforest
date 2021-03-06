package twilightforest.structures.icetower;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import twilightforest.structures.StructureTFComponent;
import twilightforest.structures.lichtower.ComponentTFTowerWing;

public class ComponentTFIceTowerStairs extends ComponentTFTowerWing {
	
	public ComponentTFIceTowerStairs() { }


	public ComponentTFIceTowerStairs(int index, int x, int y, int z, int size, int height, EnumFacing direction) {
		super(index, x, y, z, size, height, direction);
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void buildComponent(StructureComponent parent, List list, Random rand) {
		if (parent != null && parent instanceof StructureTFComponent) {
			this.deco = ((StructureTFComponent)parent).deco;
		}
	}

	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb) {
		
		for (int x = 1; x < this.size; x++) {
		
			this.placeStairs(world, sbb, x, 1 - x, 5, 2);

			for (int z = 0; z <= x; z++) {
				
				if (z > 0 && z <= this.size / 2) {
					this.placeStairs(world, sbb, x, 1 - x, 5 - z, 2);
					this.placeStairs(world, sbb, x, 1 - x, 5 + z, 2);
				}

				if (x <= this.size / 2) {
					this.placeStairs(world, sbb, z, 1 - x, 5 - x, 1);
					this.placeStairs(world, sbb, z, 1 - x, 5 + x, 3);
				}

			}
			
		}

		this.setBlockState(world, deco.blockState, 0, 0, 5, sbb);

		
		return true;
	}

	private void placeStairs(World world, StructureBoundingBox sbb, int x, int y, int z, int stairMeta) {
		BlockPos pos = new BlockPos(x, y, z);
		if (this.getBlockStateFromPos(world, x, y, z, sbb).getBlock().isReplaceable(world, pos)) {
			this.setBlockState(world, deco.blockState, x, y, z, sbb);
			//this.setBlockState(world, deco.stairID, this.getStairMeta(stairMeta), x, y, z, sbb);
			this.setBlockState(world, deco.blockState, x, y - 1, z, sbb);
		}
	}
}
