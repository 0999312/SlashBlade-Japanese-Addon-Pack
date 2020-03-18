package cn.mmf.slashblade_addon.blades;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BladeLaemmle {
	public static final String name = "flammpfeil.slashblade.named.laemmle";

	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.SHARPNESS, 3);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 80);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/laemmle/lem");
	        ItemSlashBlade.ModelName.set(tag, "named/laemmle/blade");
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        SlashBlade.registerCustomItemStack(name, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(name);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack required = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.muramasa");
		ItemStack potion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM),PotionTypes.STRONG_STRENGTH);
		SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",name), SlashBlade.getCustomBlade(name),required, new Object[]{
				 "XGO", "GBG", "QGX", Character.valueOf('X'), potion, Character.valueOf('G'),"ingotGold", Character.valueOf('O'), "obsidian", Character.valueOf('Q'),"blockQuartz", Character.valueOf('B'), required 
		}));
	}
}
