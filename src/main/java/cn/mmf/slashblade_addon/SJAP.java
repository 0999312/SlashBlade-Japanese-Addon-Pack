package cn.mmf.slashblade_addon;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.Thaumcraft;

import org.apache.logging.log4j.Logger;

import mods.flammpfeil.slashblade.SlashBlade;

@Mod(modid = SJAP.MODID, name = SJAP.NAME, version = SJAP.VERSION,dependencies="required-after:"+ SlashBlade.modid+";"+"after:"+Thaumcraft.MODID+";")
public class SJAP
{
    public static final String MODID = "slashblade_addon";
    public static final String NAME = "SlashBlade Japanese Addon Pack";
    public static final String VERSION = "1.0";

	@Instance(SJAP.MODID)
    public static SJAP instance;
    
	@SidedProxy(clientSide = "cn.mmf.slashblade_addon.ClientProxy",serverSide = "cn.mmf.slashblade_addon.CommonProxy")
	public static CommonProxy proxy; 
 
   
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}
