package cn.mmf.slashblade_addon;

import cn.mcmod.sakura.SakuraMain;
import cn.mmf.slashblade_addon.blades.BladeBamboo;
import cn.mmf.slashblade_addon.blades.BladeBladeMaster;
import cn.mmf.slashblade_addon.blades.BladeCS2Template;
import cn.mmf.slashblade_addon.blades.BladeDarkRaven;
import cn.mmf.slashblade_addon.blades.BladeFluorescentBar;
import cn.mmf.slashblade_addon.blades.BladeFrostWolf;
import cn.mmf.slashblade_addon.blades.BladeKamuy;
import cn.mmf.slashblade_addon.blades.BladeKirisaya;
import cn.mmf.slashblade_addon.blades.BladeLaemmle;
import cn.mmf.slashblade_addon.blades.BladeMURASAMA;
import cn.mmf.slashblade_addon.blades.BladeNihil;
import cn.mmf.slashblade_addon.blades.BladeTerra;
import cn.mmf.slashblade_addon.blades.BladeToyako;
import cn.mmf.slashblade_addon.blades.BladeWA;
import cn.mmf.slashblade_addon.blades.BladeWanderer;
import cn.mmf.slashblade_addon.blades.BladeWandererRF;
import cn.mmf.slashblade_addon.blades.BladeYukari;
import cn.mmf.slashblade_addon.blades.BladeZephyr;
import cn.mmf.slashblade_addon.specialattack.SAloader;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.Thaumcraft;

public class BladeLoader {
	public BladeLoader(FMLPreInitializationEvent event) {
		SlashBlade.InitEventBus.register(new SAloader());
		if(ConfigLoader.switch_Nihil)
		SlashBlade.InitEventBus.register(new BladeNihil());
		if(ConfigLoader.switch_DarkRaven)
		SlashBlade.InitEventBus.register(new BladeDarkRaven());
		
		if(ConfigLoader.switch_Toyako)
		SlashBlade.InitEventBus.register(new BladeToyako());
		if(ConfigLoader.switch_FluorescentBar)
		SlashBlade.InitEventBus.register(new BladeFluorescentBar());
		if(ConfigLoader.switch_Yukari)
		SlashBlade.InitEventBus.register(new BladeYukari());
		if(ConfigLoader.switch_Kamuy)
		SlashBlade.InitEventBus.register(new BladeKamuy());
		if(ConfigLoader.switch_Kirisaya)
		SlashBlade.InitEventBus.register(new BladeKirisaya());
		if(ConfigLoader.switch_FrostWolf)
		SlashBlade.InitEventBus.register(new BladeFrostWolf());
		if(ConfigLoader.switch_Laemmle)
		SlashBlade.InitEventBus.register(new BladeLaemmle());
		if(ConfigLoader.switch_BladeMaster)
		SlashBlade.InitEventBus.register(new BladeBladeMaster());
		if(ConfigLoader.switch_CS2)
		SlashBlade.InitEventBus.register(new BladeCS2Template());
		if(ConfigLoader.switch_MURASAMA)
		SlashBlade.InitEventBus.register(new BladeMURASAMA());
		
		if(Loader.isModLoaded("botania")){
		if(ConfigLoader.switch_Terra)
		SlashBlade.InitEventBus.register(new BladeTerra());
		}
		if(ConfigLoader.switch_Wanderer){
			SlashBlade.InitEventBus.register(new BladeWanderer());
			if(ConfigLoader.switch_MURASAMA)
				SlashBlade.InitEventBus.register(new BladeWandererRF());
		}
		

		if(Loader.isModLoaded(Thaumcraft.MODID)){
		if(ConfigLoader.switch_Zephyr)
		SlashBlade.InitEventBus.register(new BladeZephyr());
		}
		if(Loader.isModLoaded(SakuraMain.MODID)){
			if(ConfigLoader.switch_WA)
				SlashBlade.InitEventBus.register(new BladeWA());
			if(ConfigLoader.switch_Bamboo)
				SlashBlade.InitEventBus.register(new BladeBamboo());
				
		}
	}

}
