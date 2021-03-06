package twilightforest.structures.stronghold;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTFStrongholdUpperTIntersection extends StructureTFStrongholdComponent {


	public ComponentTFStrongholdUpperTIntersection() {}

	public ComponentTFStrongholdUpperTIntersection(int i, EnumFacing facing, int x, int y, int z) {
		super(i, facing, x, y, z);
	}

	@Override
	public StructureBoundingBox generateBoundingBox(EnumFacing facing, int x, int y, int z)
	{
		return StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, -2, -1, 0, 5, 5, 5, facing);
	}
	
	@Override
	public void buildComponent(StructureComponent parent, List list, Random random) {
		super.buildComponent(parent, list, random);

		// make a random component to the left
		addNewUpperComponent(parent, list, random, Rotation.COUNTERCLOCKWISE_90, 5, 1, 2);

		// make a random component to the right
		addNewUpperComponent(parent, list, random, Rotation.CLOCKWISE_90, -1, 1, 2);
	}

	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb) {
        if (this.isLiquidInStructureBoundingBox(world, sbb))
        {
            return false;
        }
        else
        {
        	placeUpperStrongholdWalls(world, sbb, 0, 0, 0, 4, 4, 4, rand, deco.randomBlocks);

        	// entrance doorway
        	placeSmallDoorwayAt(world, rand, 2, 2, 1, 0, sbb);

        	// left turn doorway
        	placeSmallDoorwayAt(world, rand, 3, 4, 1, 2, sbb);

        	// right turn doorway
        	placeSmallDoorwayAt(world, rand, 1, 0, 1, 2, sbb);

        	return true;
        }
	}

	@Override
	public boolean isComponentProtected() {
		return false;
	}
}
