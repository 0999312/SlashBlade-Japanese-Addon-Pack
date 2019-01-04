package cn.mmf.slashblade_addon.blades;

import cn.mmf.slashblade_addon.specialeffect.SELoader;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.lexicon.page.PageRuneRecipe;

public class BladeTerra {
	public static final String name = "flammpfeil.slashblade.named.terra";
	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(100));
	        ItemSlashBlade.setBaseAttackModifier(tag, 4.0F + ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/terra/terra");
	        ItemSlashBlade.ModelName.set(tag, "named/terra/terra");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(39));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(3));
	        ItemSlashBlade.SummonedSwordColor.set(tag, 3524113);
	        SpecialEffects.addEffect(customblade, SELoader.ManaBurst);
	        SpecialEffects.addEffect(customblade, SELoader.ManaRepair);
	        tag.setString("RepairOreDicMaterial", "ingotTerrasteel");
	        
	        SlashBlade.registerCustomItemStack(name, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(name);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
		ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	    ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", "proudsoul", 1);
	    RecipeRuneAltar runeAltarRecipe;
	    ItemStack terraBlade = SlashBlade.findItemStack("botania", "terrasword", 1);
	    ItemStack vineBall = SlashBlade.findItemStack("botania", "vineball", 1);
	    ItemStack thornChakram = SlashBlade.findItemStack("botania", "thornchakram", 1);
	    
	    runeAltarRecipe = BotaniaAPI.registerRuneAltarRecipe(SlashBlade.getCustomBlade(name), 10000, new Object[] { terraBlade, sphere, "gaiaIngot", vineBall,"ingotGold", thornChakram, "ingotTerrasteel", soul });
	    for (LexiconEntry entry : BotaniaAPI.categoryTools.entries) {
	        if (entry.unlocalizedName.equalsIgnoreCase("terraSword"))
	        {
	          entry.setLexiconPages(new LexiconPage[] { new PageRuneRecipe("sb", runeAltarRecipe) });
	          break;
	        }
	      }
	}
}
