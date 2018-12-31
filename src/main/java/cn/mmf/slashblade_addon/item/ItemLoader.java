package cn.mmf.slashblade_addon.item;

import cn.mmf.slashblade_addon.ConfigLoader;
import cn.mmf.slashblade_addon.SJAP;
import cofh.CoFHCore;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import thaumcraft.Thaumcraft;

public class ItemLoader {
	public static Item FPNCore = new Item().setUnlocalizedName("fpn_core").setCreativeTab(SlashBlade.tab);
	public static Item rfblade = new ItemSlashBladeRF(ToolMaterial.IRON, 4.0F).setUnlocalizedName("slashBlade_RF");
	public static Item tcblade = new ItemSlashBladeWind(ToolMaterial.IRON, 4.0F).setUnlocalizedName("slashBlade_TC");
	public ItemLoader(FMLPreInitializationEvent event) {
		if(Loader.isModLoaded(CoFHCore.MOD_ID)){
		if(ConfigLoader.switch_MURASAMA)	register(FPNCore);
		register(rfblade);
		}
		if(Loader.isModLoaded(Thaumcraft.MODID)) register(tcblade);
	}
	private static void register(Item item)
    {
        ForgeRegistries.ITEMS.register(item.setRegistryName(item.getUnlocalizedName().substring(5)));
    }
}
