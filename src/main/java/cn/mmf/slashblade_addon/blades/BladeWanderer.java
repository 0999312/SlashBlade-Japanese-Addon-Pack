package cn.mmf.slashblade_addon.blades;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BladeWanderer {
	public static final String name = "flammpfeil.slashblade.named.wanderer";
	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
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
	    ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1);
		ItemStack reqiredBlade = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.doutanuki");
		SlashBlade.addRecipe(name, new ShapedOreRecipe(new ResourceLocation("flammpfeil.slashblade",name), SlashBlade.getCustomBlade(name), new Object[]{
				 "  I", "QI ", "BC ", Character.valueOf('B'), reqiredBlade , Character.valueOf('Q'),"gemQuartz", Character.valueOf('I'), ingot, Character.valueOf('C'), new ItemStack(Items.CLOCK) 
		}));
	}
}
