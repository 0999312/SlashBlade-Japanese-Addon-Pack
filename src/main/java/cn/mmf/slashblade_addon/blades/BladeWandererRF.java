package cn.mmf.slashblade_addon.blades;

import cn.mmf.slashblade_addon.item.ItemLoader;
import cn.mmf.slashblade_addon.recipes.RecipeRestartUserRF;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BladeWandererRF {
	public static final String name = "flammpfeil.slashblade.named.wanderer.rfblade";
	@SubscribeEvent
	public void InitKatanaRF(InitEvent event){
	     	ItemStack customblade = new ItemStack(ItemLoader.rfblade,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(60));
	        ItemSlashBlade.setBaseAttackModifier(tag, 4.0F + ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/wanderer/wanderer");
	        ItemSlashBlade.ModelName.set(tag, "named/wanderer/wanderer");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(30));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(1));
	        
	        SlashBlade.registerCustomItemStack(name, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(name);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1);
	    SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",name), SlashBlade.getCustomBlade(name),SlashBlade.getCustomBlade("flammpfeil.slashblade.named.wanderer"),new Object[]{
	    		"  I","QI ","BC ",
	    		Character.valueOf('B'), SlashBlade.getCustomBlade("flammpfeil.slashblade.named.wanderer"),
	    		Character.valueOf('Q'), "gemQuartz",
	    		Character.valueOf('I'), "dustRedstone",
	    		Character.valueOf('C'), ItemLoader.FPNCore
				}));
		SlashBlade.addRecipe("wanderer_restart", new RecipeRestartUserRF(new ResourceLocation("wanderer_restart"), SlashBlade.getCustomBlade(name),SlashBlade.getCustomBlade(name),new Object[]{
				"S", "B", "I", 
			      Character.valueOf('I'), "blockQuartz", 
			      Character.valueOf('B'), SlashBlade.getCustomBlade("flammpfeil.slashblade",name), 
			      Character.valueOf('S'), soul
				}));
	}
}
