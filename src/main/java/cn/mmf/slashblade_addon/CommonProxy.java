package cn.mmf.slashblade_addon;

import cn.mmf.slashblade_addon.entity.EntityLoader;
import cn.mmf.slashblade_addon.item.ItemLoader;
import cn.mmf.slashblade_addon.specialeffect.SELoader;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;


public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event)
	{
		 new ConfigLoader(event);	
		 new SELoader();
		 new ItemLoader(event);
		 new BladeLoader(event);
	}
    public void init(FMLInitializationEvent event)
    { 
    	new EntityLoader();

    	if(Loader.isModLoaded(Thaumcraft.MODID)){
   		 ThaumcraftApi.registerResearchLocation(new ResourceLocation(SlashBlade.modid+":research/research.json"));
    	}
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }
	}
