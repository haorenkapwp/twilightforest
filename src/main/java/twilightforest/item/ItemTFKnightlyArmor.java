package twilightforest.item;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import twilightforest.TwilightForestMod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import twilightforest.client.ModelRegisterCallback;

import javax.annotation.Nonnull;

public class ItemTFKnightlyArmor extends ItemArmor implements ModelRegisterCallback {

	public ItemTFKnightlyArmor(ItemArmor.ArmorMaterial material, EntityEquipmentSlot slot) {
		super(material, 0, slot);
		this.setCreativeTab(TFItems.creativeTab);
	}

	@Nonnull
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.RARE;
	}

	@Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String layer)
    {
		if (slot == EntityEquipmentSlot.LEGS)
		{
			return TwilightForestMod.ARMOR_DIR + "knightly_2.png";
		}
		else
		{
			return TwilightForestMod.ARMOR_DIR + "knightly_1.png";
		}
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> list)
    {
    	ItemStack istack = new ItemStack(item);
    	//istack.addEnchantment(TFEnchantment.reactFire, 2);
        list.add(istack);
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped original)
    {
        return TwilightForestMod.proxy.getKnightlyArmorModel(armorSlot);
    }
}
