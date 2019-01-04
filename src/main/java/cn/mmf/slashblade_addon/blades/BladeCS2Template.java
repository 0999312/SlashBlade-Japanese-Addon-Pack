package cn.mmf.slashblade_addon.blades;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BladeCS2Template {
	public static final String name = "SlashBladeTemplate.blue";

	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 75);
	        ItemSlashBlade.setBaseAttackModifier(tag, 7F);
	        ItemSlashBlade.AttackAmplifier.set(tag, 0.04F);
	        
	        ItemSlashBlade.TextureName.set(tag, "wa/template");
	        ItemSlashBlade.ModelName.set(tag, "wa/model");
	        
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(3));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	        
	        SlashBlade.registerCustomItemStack(name, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(name);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1);
		SlashBlade.addRecipe(name, new ShapedOreRecipe(new ResourceLocation("flammpfeil.slashblade",name), SlashBlade.getCustomBlade(name), new Object[]{
				 "DCS", "CS ", "GP ", Character.valueOf('P'),"string", Character.valueOf('G'), "stickWood", Character.valueOf('S'), soul ,
				 'D',"dyeBlue",'C',"blockCoal"
		}));
	}
}
