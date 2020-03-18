package cn.mmf.slashblade_addon.blades;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BladeYukari {
	public static final String nameyukari = "flammpfeil.slashblade.named.yukari";
	public static final String nametboen = "flammpfeil.slashblade.named.tboen";
	@SubscribeEvent
	public void InitYukari(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        
	        customblade.addEnchantment(Enchantments.POWER, 1);
	        
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, nameyukari);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(50));
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	        ItemSlashBlade.setBaseAttackModifier(tag,  4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(8));
	        ItemSlashBlade.SummonedSwordColor.set(tag, Integer.valueOf(10635427));
	        ItemSlashBlade.TextureName.set(tag, "named/yukari/texture");
	        ItemSlashBlade.ModelName.set(tag, "named/yukari/model");
	        
	        SlashBlade.registerCustomItemStack(nameyukari, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(nameyukari);
	}
	@SubscribeEvent
	public void InitTboen(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, nametboen);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(70));
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/tboen/texture");
	        ItemSlashBlade.ModelName.set(tag, "named/tboen/model");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(4));
	        ItemSlashBlade.SummonedSwordColor.set(tag, Integer.valueOf(16746632));
	        
	        SlashBlade.registerCustomItemStack(nametboen, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(nametboen);
	}
	
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	    ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	    ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", "proudsoul", 1);
	    
	    ItemStack yukari = SlashBlade.getCustomBlade(nameyukari);
	    ItemStack tboen = SlashBlade.getCustomBlade(nametboen);
	    
	    ItemStack reqblade_yukari = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yuzukitukumo");
	    ItemStack reqblade_tboen = new ItemStack(SlashBlade.bladeWhiteSheath);
	    
	    NBTTagCompound reqtag_yukati = ItemSlashBlade.getItemTagCompound(reqblade_yukari);
	    
	    reqblade_yukari.addEnchantment(Enchantments.FIRE_ASPECT, 1);
	    ItemSlashBlade.KillCount.set(reqtag_yukati, 1000);
	    
	    SlashBlade.addRecipe(nameyukari, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",nameyukari), yukari, reqblade_yukari, new Object[]{
	    		"ISI", "SBS", "ISI", Character.valueOf('I'), ingot, Character.valueOf('S'), sphere, Character.valueOf('B'), reqblade_yukari 
	    }));
	    SlashBlade.addRecipe(nametboen, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",nametboen), tboen, reqblade_tboen, new Object[]{
	    		"SSS", "SBS", "SSS", Character.valueOf('S'), soul, Character.valueOf('B'), reqblade_tboen  
	    }));
	}
}
