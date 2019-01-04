package cn.mmf.slashblade_addon;

import cn.mmf.slashblade_addon.client.renderer.entity.RenderDriveEx;
import cn.mmf.slashblade_addon.client.renderer.entity.RenderPhantomSwordEx;
import cn.mmf.slashblade_addon.entity.EntityDriveEx;
import cn.mmf.slashblade_addon.entity.EntityPhantomSwordEx;
import cn.mmf.slashblade_addon.item.ItemLoader;
import cofh.CoFHCore;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.tileentity.DummyTileEntity;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.Thaumcraft;

public class ClientProxy extends CommonProxy {
	@Override
	    public void preInit(FMLPreInitializationEvent event)
	    {
	        super.preInit(event);
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
	        if(Loader.isModLoaded(CoFHCore.MOD_ID)){
	    		if(ConfigLoader.switch_MURASAMA)ModelLoader.setCustomModelResourceLocation(ItemLoader.FPNCore, 0 , new ModelResourceLocation(SlashBlade.modid + ":" + "sphere.obj"));
	        Slashblade_model(ItemLoader.rfblade);
	        }
	        if(Loader.isModLoaded(Thaumcraft.MODID)) Slashblade_model(ItemLoader.tcblade);
	    }
		static final ModelResourceLocation modelLoc = new ModelResourceLocation("flammpfeil.slashblade:model/named/blade.obj");
    	@SuppressWarnings("deprecation")
		public static void Slashblade_model(Item item) {
    		ModelLoader.setCustomModelResourceLocation(item, 0, modelLoc);
    		ForgeHooksClient.registerTESRItemStack(item, 0, DummyTileEntity.class);
    	}

	    @Override
	    public void init(FMLInitializationEvent event)
	    {
	        super.init(event);
	        
	    }

	    @Override
	    public void postInit(FMLPostInitializationEvent event)
	    {
	        super.postInit(event);
	       
	    }

}
