package cn.mmf.slashblade_addon.entity;

import cn.mmf.slashblade_addon.SJAP;
import cn.mmf.slashblade_addon.client.renderer.entity.RenderDriveEx;
import cn.mmf.slashblade_addon.client.renderer.entity.RenderPhantomSwordEx;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader {
	public EntityLoader() {
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "PhantomSwordEx"),EntityPhantomSwordEx.class,"PhantomSwordEx",1,SJAP.instance,250,1,true);
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "LightningSword"),EntityLightningSword.class,"LightningSword",2,SJAP.instance,250,1,true);
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "NoFireLigntningBolt"),EntityNoFireLightningBolt.class,"NoFireLigntningBolt",3,SJAP.instance,250,1,true);
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "EntityDriveEx"),EntityDriveEx.class,"EntityDriveEx",4,SJAP.instance,250,1,true);
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "EntityAquaEdge"),EntityAquaEdge.class,"EntityAquaEdge",5,SJAP.instance,250,1,true);
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "EntityFlareEdge"),EntityFlareEdge.class,"EntityFlareEdge",6,SJAP.instance,250,1,true);
		
	}
	@SideOnly(Side.CLIENT)
	public static void registerRender() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPhantomSwordEx.class,
			new IRenderFactory<EntityPhantomSwordEx>() {
        		@Override
        		public Render<? super EntityPhantomSwordEx> createRenderFor(RenderManager manager)
				{
					return new RenderPhantomSwordEx(manager);
				}
			});
        RenderingRegistry.registerEntityRenderingHandler(EntityDriveEx.class,
        		new IRenderFactory<EntityDriveEx>() {
        		@Override
        		public Render<? super EntityDriveEx> createRenderFor(RenderManager manager)
				{
					return new RenderDriveEx(manager);
				}
			});

	}
}
