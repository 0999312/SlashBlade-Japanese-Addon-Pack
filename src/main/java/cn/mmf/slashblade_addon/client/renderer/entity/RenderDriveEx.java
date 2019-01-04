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

import cn.mmf.slashblade_addon.entity.EntityDriveEx;

@SideOnly(Side.CLIENT)
public class RenderDriveEx extends Render<EntityDriveEx>
{

    private static double[][] vertices = {
		{  0.00,  1.00, -0.50},
        {  0.00,  0.75,  0.00},
        {  0.10,  0.60, -0.15},
        {  0.00,  0.50, -0.25},
        { -0.10,  0.60, -0.15},
        {  0.00,  0.00,  0.25},
        {  0.25,  0.00,  0.00},
        {  0.00,  0.00, -0.25},
        { -0.25,  0.00,  0.00},
        {  0.00, -0.75,  0.00},
        {  0.10, -0.60, -0.15},
        {  0.00, -0.50, -0.25},
        { -0.10, -0.60, -0.15},
        {  0.00, -1.00, -0.50},
	};

    private static int[] indices = {
		 0,  1,  2,  3,
         0,  3,  4,  1,
         1,  5,  6,  2,
         3,  2,  6,  7,
         3,  7,  8,  4,
         1,  4,  8,  5,
         6,  5,  9, 10,
         6, 10, 11,  7,
         8,  7, 11, 12,
         8, 12,  9,  5,
        10,  9, 13, 11,
        12, 11, 13,  9,
	};

    public RenderDriveEx(RenderManager renderManager)
	{
        super(renderManager);
    }

    @Override
    public void doRender(EntityDriveEx entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
		GL11.glPushAttrib(GL11.GL_CURRENT_BIT |
						  GL11.GL_ENABLE_BIT |
						  GL11.GL_LIGHTING_BIT |
						  GL11.GL_COLOR_BUFFER_BIT);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		
        float lifetime = entity.getLifeTime();
        float ticks = entity.ticksExisted;
        float alpha = (float)Math.pow((lifetime - Math.min(lifetime,ticks)) / lifetime, 2.0);
		
		int color = entity.getColor();
		GL11.glColor4f(((color >> 16) & 0xff)/255.0f,
					   ((color >>  8) & 0xff)/255.0f,
					   ((color      ) & 0xff)/255.0f,
					   alpha);
		


        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(entity.rotationYaw,		0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-entity.rotationPitch, 	1.0f, 0.0f, 0.0f);
        GL11.glRotatef(entity.getRoll(),		0.0f, 0.0f, 1.0f);
        GL11.glScalef(0.25f, 1, 1);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder wr = tessellator.getBuffer();

        wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

        for (int i = 0; i < indices.length; i++) {
			double[] v = vertices[indices[i]];
            wr.pos(v[0], v[1], v[2]).endVertex();
        }

        tessellator.draw();

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    @Override
	protected ResourceLocation getEntityTexture(EntityDriveEx entity)
	{
        return null;
    }
		
}
