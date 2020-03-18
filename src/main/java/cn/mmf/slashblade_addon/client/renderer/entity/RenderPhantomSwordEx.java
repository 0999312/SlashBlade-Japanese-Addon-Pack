package cn.mmf.slashblade_addon.client.renderer.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import cn.mmf.slashblade_addon.entity.EntityPhantomSwordEx;

@SideOnly(Side.CLIENT)
public class RenderPhantomSwordEx extends Render<EntityPhantomSwordEx>
{
    private static double[][] vertices = {
		{   0.0000,   0.0000,  417.7431},
		{   0.0000, -44.6113,    0.0000},
		{  38.9907,   0.0000,   50.0000},
		{   0.0000,  44.6113,    0.0000},
		{ -38.9907,   0.0000,   50.0000},
		{  38.9907,   0.0000,  -50.0000},
		{ -38.9907,   0.0000,  -50.0000},
		{   0.0000,   0.0000, -214.0305},
		{ 159.1439,   0.0000,  -49.6611},
		{-159.1439,   0.0000,  -49.6611},
	};

	/**
	 * 頂点インデックス
	 */
    private static int[] indices = {
		0, 2, 1,
		0, 3, 2,
		0, 4, 3,
		0, 1, 4,
		1, 5, 7,
		5, 3, 7,
		3, 6, 7,
		6, 1, 7,
		2, 8, 1,
		5, 8, 3,
		4, 9, 3,
		6, 9, 1,
		1, 8, 5,
		1, 9, 4,
		3, 8, 2,
		3, 9, 6,
	};

    public RenderPhantomSwordEx(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityPhantomSwordEx entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GL11.glPushAttrib(GL11.GL_CURRENT_BIT |
						  GL11.GL_ENABLE_BIT |
						  GL11.GL_LIGHTING_BIT |
						  GL11.GL_COLOR_BUFFER_BIT);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

		int color = entity.getColor();
		GL11.glColor3ub((byte)((color >> 16) & 0xff),
						(byte)((color >>  8) & 0xff),
						(byte)((color      ) & 0xff));

        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(entity.rotationYaw,		0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-entity.rotationPitch, 	1.0f, 0.0f, 0.0f);
        GL11.glRotatef(entity.getRoll(),		0.0f, 0.0f, 1.0f);

        final float scale = 0.0045f;
        GL11.glScalef(scale * 0.5f, scale * 0.5f, scale);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();

        wr.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION);

        for (int i = 0; i < indices.length; i++) {
			double[] v = vertices[indices[i]];
            wr.pos(v[0], v[1], v[2]).endVertex();
        }

        tessellator.draw();

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    @Override
	protected ResourceLocation getEntityTexture(EntityPhantomSwordEx entity) {
        return null;
    }
	
}
