package cn.mmf.slashblade_addon;

import cn.mmf.slashblade_addon.entity.EntityLoader;
import cn.mmf.slashblade_addon.item.ItemLoader;
import cofh.CoFHCore;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.tileentity.DummyTileEntity;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.Thaumcraft;

public class ClientProxy extends CommonProxy {
	@Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        EntityLoader.registerRender();
        if(Loader.isModLoaded(CoFHCore.MOD_ID)){
    		if(ConfigLoader.switch_MURASAMA) ModelLoader.setCustomModelResourceLocation(ItemLoader.FPNCore, 0 , new ModelResourceLocation(SlashBlade.modid + ":" + "sphere.obj"));
    		Slashblade_model(ItemLoader.rfblade);
        }
        if(Loader.isModLoaded(Thaumcraft.MODID)) Slashblade_model(ItemLoader.tcblade);
    }
	private static final ModelResourceLocation modelLoc = new ModelResourceLocation("flammpfeil.slashblade:model/named/blade.obj");
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
