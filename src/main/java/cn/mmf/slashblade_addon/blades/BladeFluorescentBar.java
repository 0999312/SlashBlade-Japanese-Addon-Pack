package cn.mmf.slashblade_addon.blades;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
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
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BladeFluorescentBar {
	public static final String name = "flammpfeil.slashblade.named.fluorescentbar";

	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.UNBREAKING, 3);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, ToolMaterial.DIAMOND.getMaxUses());
	        ItemSlashBlade.setBaseAttackModifier(tag, 2F);
	        ItemSlashBlade.TextureName.set(tag, "named/fluorescentbar/fluorescentbar");
	        ItemSlashBlade.ModelName.set(tag, "named/fluorescentbar/fluorescentbar");
	        
	        SlashBlade.registerCustomItemStack(name, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(name);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1);
		SlashBlade.addRecipe(name, new ShapedOreRecipe(new ResourceLocation("flammpfeil.slashblade",name), SlashBlade.getCustomBlade(name), new Object[]{
				 " PS", "PGP", "SP ", Character.valueOf('P'),"paper", Character.valueOf('G'), "blockGlass", Character.valueOf('S'), soul 
		}));
	}
}
