package cn.mmf.slashblade_addon.blades;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
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

public class BladeFrostWolf {
	public static final String nameA = "flammpfeil.slashblade.named.frostwolfa";
	public static final String nameB = "flammpfeil.slashblade.named.frostwolfb";
	@SubscribeEvent
	public void InitA(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, nameA);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 50);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/frostwolf/frostwolfa");
	        ItemSlashBlade.ModelName.set(tag, "named/frostwolf/frostwolfa");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(30));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        SlashBlade.registerCustomItemStack(nameA, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(nameA);
	}
	@SubscribeEvent
	public void InitB(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, nameB);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 50);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/frostwolf/frostwolfb");
	        ItemSlashBlade.ModelName.set(tag, "named/frostwolf/frostwolfb");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(32));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        SlashBlade.registerCustomItemStack(nameB, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(nameB);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.SphereBladeSoulStr, 1);
		SlashBlade.addRecipe(nameA, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",nameA), SlashBlade.getCustomBlade(nameA),SlashBlade.getCustomBlade("flammpfeil.slashblade.named.doutanuki"),new Object[]{
				" IL", "CS ", "BQ ", Character.valueOf('L'),"dyeBlue", Character.valueOf('S'), sphere, Character.valueOf('B'), SlashBlade.getCustomBlade("flammpfeil.slashblade.named.doutanuki"), Character.valueOf('Q'),"gemQuartz", Character.valueOf('I'), new ItemStack(Blocks.ICE), Character.valueOf('C'), new ItemStack(Blocks.SNOW)
				}));
		SlashBlade.addRecipe(nameB, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",nameB), SlashBlade.getCustomBlade(nameB),SlashBlade.getCustomBlade("flammpfeil.slashblade.named.muramasa"),new Object[]{
				" IL", "CS ", "BQ ", Character.valueOf('L'),"dyeBlue", Character.valueOf('S'), sphere, Character.valueOf('B'), SlashBlade.getCustomBlade("flammpfeil.slashblade.named.muramasa"), Character.valueOf('Q'),"gemQuartz", Character.valueOf('I'), new ItemStack(Blocks.ICE), Character.valueOf('C'), new ItemStack(Blocks.SNOW)
				}));
	}
}
