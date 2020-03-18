package cn.mmf.slashblade_addon.blades;

import cn.mmf.slashblade_addon.SJAP;
import cn.mmf.slashblade_addon.item.ItemLoader;
import cn.mmf.slashblade_addon.recipes.InfusionRecipeSlashBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Enchantments;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

public class BladeZephyr {
	public static final String name = "flammpfeil.slashblade.named.zephyr";
	public static final String namewindeater = "flammpfeil.slashblade.named.windeater";
	@SubscribeEvent
	public void InitZephyr(InitEvent event){
	     ItemStack customblade = new ItemStack(ItemLoader.tcblade,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.POWER, 5);
	        customblade.addEnchantment(Enchantments.FEATHER_FALLING, 5);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(70));
	        ItemSlashBlade.TextureName.set(tag, "zephyr/tex");
	        ItemSlashBlade.ModelName.set(tag, "zephyr/model");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(1));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	        EnumInfusionEnchantment.addInfusionEnchantment(customblade, EnumInfusionEnchantment.ARCING, 3);
	        SlashBlade.registerCustomItemStack(name, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(name);
	}
	@SubscribeEvent
	public void InitWindeater(InitEvent event){
	     ItemStack customblade = new ItemStack(ItemLoader.tcblade,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namewindeater);
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(70));
	        ItemSlashBlade.TextureName.set(tag, "zephyr/tex");
	        ItemSlashBlade.ModelName.set(tag, "zephyr/model");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(1));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	        EnumInfusionEnchantment.addInfusionEnchantment(customblade, EnumInfusionEnchantment.ARCING, 3);
	        SlashBlade.registerCustomItemStack(namewindeater, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namewindeater);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
		 ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1);
		 ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.SphereBladeSoulStr, 1);
		 ItemStack isESW = new ItemStack(ItemsTC.elementalSword,1,32767);
		 EnumInfusionEnchantment.addInfusionEnchantment(isESW, EnumInfusionEnchantment.ARCING, 2);
		 ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(SJAP.MODID,namewindeater), 
				new InfusionRecipeSlashBlade("WINDEATER", SlashBlade.getCustomBlade(namewindeater), 1,
						new AspectList().add(Aspect.AIR, 8).add(Aspect.AVERSION, 8).add(Aspect.ENERGY, 8)
						,isESW,
						new Object[]{
								ConfigItems.AIR_CRYSTAL, soul,"oreDiamond", soul, sphere, soul,"bone", soul 
		}));
		ItemStack ReqBlade = SlashBlade.getCustomBlade(namewindeater);
	    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(ReqBlade);
	    ItemSlashBlade.KillCount.set(tag, Integer.valueOf(100));
	    SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",name), SlashBlade.getCustomBlade(name), ReqBlade, new Object[]{
	    		" X ", "XBX", " X ", Character.valueOf('X'), soul, Character.valueOf('B'), ReqBlade
	    }));
	}
}
