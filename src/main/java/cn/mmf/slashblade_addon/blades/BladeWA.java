package cn.mmf.slashblade_addon.blades;

import cn.mmf.slashblade_addon.recipes.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BladeWA {
	public static final String namekatana = "flammpfeil.slashblade.named.wa.katana";
	public static final String nametachi = "flammpfeil.slashblade.named.wa.tachi";

	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namekatana);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(457));
	        ItemSlashBlade.setBaseAttackModifier(tag, 3F);
	        ItemSlashBlade.TextureName.set(tag, "wa/waA");
	        ItemSlashBlade.ModelName.set(tag, "wa/model");
	        
	        SlashBlade.registerCustomItemStack(namekatana, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namekatana);
	}
	@SubscribeEvent
	public void InitTachi(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, nametachi);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(457));
	        ItemSlashBlade.setBaseAttackModifier(tag,3F);
	        ItemSlashBlade.TextureName.set(tag, "wa/waB");
	        ItemSlashBlade.ModelName.set(tag, "wa/model");
	        
	        SlashBlade.registerCustomItemStack(nametachi, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(nametachi);
	}
	
	@SubscribeEvent
	public void InitKatana_NoSheath(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namekatana);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(457));
	        ItemSlashBlade.setBaseAttackModifier(tag, 3F);
	        ItemSlashBlade.TextureName.set(tag, "wa/waA");
	        ItemSlashBlade.ModelName.set(tag, "named/muramasa/muramasa");
	        ItemSlashBlade.IsNoScabbard.set(tag, true);
	        
	        SlashBlade.registerCustomItemStack(namekatana+"_noSheath", customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namekatana+"_noSheath");
	}
	@SubscribeEvent
	public void InitTachi_NoSheath(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, nametachi);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(457));
	        ItemSlashBlade.setBaseAttackModifier(tag,3F);
	        ItemSlashBlade.TextureName.set(tag, "wa/waB");
	        ItemSlashBlade.ModelName.set(tag, "named/muramasa/muramasa");
	        ItemSlashBlade.IsNoScabbard.set(tag, true);
	        
	        SlashBlade.registerCustomItemStack(nametachi+"_noSheath", customblade);
	        ItemSlashBladeNamed.NamedBlades.add(nametachi+"_noSheath");
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.IngotBladeSoulStr, 1);
	    ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1);
		SlashBlade.addRecipe(namekatana+"_noSheath", new ShapedOreRecipe(new ResourceLocation("flammpfeil.slashblade",namekatana+"_noSheath"), SlashBlade.getCustomBlade(namekatana+"_noSheath"), new Object[]{
				"  I"," I ","S  ",'S',"stickWood",'I',ingot
		}));
		SlashBlade.addRecipe(nametachi+"_noSheath", new ShapedOreRecipe(new ResourceLocation("flammpfeil.slashblade",nametachi+"_noSheath"), SlashBlade.getCustomBlade(nametachi+"_noSheath"), new Object[]{
				" II","II ","S  ",'S',"stickWood",'I',ingot
		}));
	    SlashBlade.addRecipe(namekatana, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namekatana), SlashBlade.getCustomBlade(namekatana),SlashBlade.getCustomBlade(namekatana+"_noSheath"),new Object[]{
				"  S", " W ", "B  ", Character.valueOf('S'), soul, Character.valueOf('B'), SlashBlade.getCustomBlade(namekatana+"_noSheath"),Character.valueOf('W'), new ItemStack(SlashBlade.wrapBlade)
				}));
		SlashBlade.addRecipe(nametachi, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",nametachi), SlashBlade.getCustomBlade(nametachi),SlashBlade.getCustomBlade(nametachi+"_noSheath"),new Object[]{
				"  S", " W ", "B  ", Character.valueOf('S'), soul, Character.valueOf('B'), SlashBlade.getCustomBlade(nametachi+"_noSheath"),Character.valueOf('W'), new ItemStack(SlashBlade.wrapBlade)
				}));
	}
}
