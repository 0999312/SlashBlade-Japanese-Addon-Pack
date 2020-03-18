package cn.mmf.slashblade_addon.blades;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BladeBladeMaster {
	public static final String namegreenmist = "flammpfeil.slashblade.named.blademaster.greenmist";
	public static final String nameaquablaze = "flammpfeil.slashblade.named.blademaster.aquablaze";
	public static final String namemoonlightcherry = "flammpfeil.slashblade.named.blademaster.moonlightcherry";

	@SubscribeEvent
	public void Initgreenmist(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.POWER, 3);
	        customblade.addEnchantment(Enchantments.FORTUNE, 3);
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namegreenmist);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 60);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/blademaster/greenmist");
	        ItemSlashBlade.ModelName.set(tag, "named/blademaster/blademaster");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(30));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        SlashBlade.registerCustomItemStack(namegreenmist, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namegreenmist);
	}
	@SubscribeEvent
	public void Initquablaze(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.FIRE_PROTECTION, 1);
	        customblade.addEnchantment(Enchantments.FIRE_ASPECT, 2);
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	        ItemSlashBladeNamed.CurrentItemName.set(tag, nameaquablaze);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 60);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/blademaster/aquablaze");
	        ItemSlashBlade.ModelName.set(tag, "named/blademaster/blademaster");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(31));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        SlashBlade.registerCustomItemStack(nameaquablaze, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(nameaquablaze);
	}
	@SubscribeEvent
	public void Initmoonlightcherry(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.THORNS, 1);
	        customblade.addEnchantment(Enchantments.SMITE, 5);
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namemoonlightcherry);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 60);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/blademaster/greenmist");
	        ItemSlashBlade.ModelName.set(tag, "named/blademaster/blademaster");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(32));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        SlashBlade.registerCustomItemStack(namemoonlightcherry, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namemoonlightcherry);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.SphereBladeSoulStr, 1);
	    
	    ItemStack reqiredBlade_base = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.muramasa");
	    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(reqiredBlade_base);
	    ItemSlashBlade.ProudSoul.set(tag, Integer.valueOf(10000));
	    ItemSlashBlade.KillCount.set(tag, Integer.valueOf(1000));
	    ItemSlashBlade.RepairCount.set(tag, Integer.valueOf(25));
	    
	    ItemStack reqblade_1 =reqiredBlade_base.copy();
	    reqblade_1.addEnchantment(Enchantments.POWER, 1);
	    ItemStack reqblade_2 =reqiredBlade_base.copy();
	    reqblade_2.addEnchantment(Enchantments.FIRE_PROTECTION, 1);
	    ItemStack reqblade_3 =reqiredBlade_base.copy();
	    reqblade_3.addEnchantment(Enchantments.THORNS, 1);
	    
		SlashBlade.addRecipe(namegreenmist, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namegreenmist), SlashBlade.getCustomBlade(namegreenmist),reqblade_1, new Object[]{
				 "SRE", "RE ", "BGC", Character.valueOf('B'), reqblade_1, Character.valueOf('E'),"blockEmerald", Character.valueOf('R'),"blockRedstone", Character.valueOf('G'),"blockGold", Character.valueOf('C'), "gemQuartz", Character.valueOf('S'), sphere 
		}));
		SlashBlade.addRecipe(nameaquablaze, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",nameaquablaze), SlashBlade.getCustomBlade(nameaquablaze),reqblade_2, new Object[]{
				"SRW", "RL ", "BGC", Character.valueOf('B'), reqblade_2, Character.valueOf('W'), new ItemStack(Items.WATER_BUCKET), Character.valueOf('L'), new ItemStack(Items.LAVA_BUCKET), Character.valueOf('R'),"blockRedstone", Character.valueOf('G'),"blockGold", Character.valueOf('C'), "gemQuartz", Character.valueOf('S'), sphere 
		}));
		SlashBlade.addRecipe(namemoonlightcherry, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namemoonlightcherry), SlashBlade.getCustomBlade(namemoonlightcherry),reqblade_3, new Object[]{
				 "SRW", "RL ", "BGC", Character.valueOf('B'), reqblade_3, Character.valueOf('W'),"blockQuartz", Character.valueOf('L'),"glowstone", Character.valueOf('R'), "blockRedstone", Character.valueOf('G'),"blockGold", Character.valueOf('C'), "gemQuartz", Character.valueOf('S'), sphere 
		}));
	}
}
