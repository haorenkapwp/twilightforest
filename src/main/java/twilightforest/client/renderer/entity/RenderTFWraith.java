package twilightforest.client.renderer.entity;


import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import twilightforest.TwilightForestMod;
import twilightforest.entity.EntityTFWraith;

import javax.annotation.Nonnull;

public class RenderTFWraith extends RenderBiped<EntityTFWraith> {
    private static final ResourceLocation textureWraith = new ResourceLocation(TwilightForestMod.MODEL_DIR + "ghost.png");
    private static final ResourceLocation textureCrown = new ResourceLocation(TwilightForestMod.MODEL_DIR + "ghost-crown.png");

	public RenderTFWraith(RenderManager manager, ModelBiped modelbiped, float shadowSize) {
		super(manager, modelbiped, shadowSize);
		addLayer(new LayerWraith());
	}

    @Override
	protected ResourceLocation getEntityTexture(@Nonnull EntityTFWraith wraith)
    {
        return textureCrown;
    }

    class LayerWraith implements LayerRenderer<EntityTFWraith> {
		@Override
		public void doRenderLayer(@Nonnull EntityTFWraith wraith, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
			RenderTFWraith.this.bindTexture(textureWraith);
			GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
			RenderTFWraith.this.mainModel.render(wraith, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
		}

		@Override
		public boolean shouldCombineTextures() {
			return false;
		}
	}
}
