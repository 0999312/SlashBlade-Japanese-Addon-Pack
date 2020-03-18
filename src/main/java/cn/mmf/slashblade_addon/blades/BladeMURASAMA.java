package cn.mmf.slashblade_addon.blades;

import cn.mmf.slashblade_addon.item.ItemLoader;
import cn.mmf.slashblade_addon.recipes.RecipeRestartUserRF;
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
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BladeMURASAMA {
	public static final String name = "flammpfeil.slashblade.named.murasamablade";

	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(ItemLoader.rfblade,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        
	        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(250));
	        ItemSlashBlade.setBaseAttackModifier(tag, 4.0F + ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/murasamablade/murasama");
	        ItemSlashBlade.ModelName.set(tag, "named/murasamablade/murasama");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(20));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	        
	        SlashBlade.registerCustomItemStack(name, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(name);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
		 ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1);
		ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.SphereBladeSoulStr, 1);
		SlashBlade.addRecipe("FPNCore", new ShapedOreRecipe(new ResourceLocation("flammpfeil.slashblade","FPNCore"), ItemLoader.FPNCore, new Object[]{
				"SQS", "QNQ", "SQS", 
			      Character.valueOf('S'), sphere, 
			      Character.valueOf('Q'), "blockQuartz", 
			      Character.valueOf('N'), "netherStar"
		}));
	    SlashBlade.addRecipe(name, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",name), SlashBlade.getCustomBlade(name),SlashBlade.getCustomBlade("flammpfeil.slashblade.named.muramasa"),new Object[]{
	    		 " RI", "RBG", "SL ", 
	    	      Character.valueOf('I'), "ingotIron", 
	    	      Character.valueOf('R'), "blockRedstone", 
	    	      Character.valueOf('L'), new ItemStack(Blocks.LEVER), 
	    	      Character.valueOf('B'), SlashBlade.getCustomBlade("flammpfeil.slashblade.named.muramasa"), 
	    	      Character.valueOf('G'), "gunpowder", 
	    	      Character.valueOf('S'), ItemLoader.FPNCore 
				}));
		SlashBlade.addRecipe("murasama_restart", new RecipeRestartUserRF(new ResourceLocation("flammpfeil.slashblade","murasama_restart"), SlashBlade.getCustomBlade(name),SlashBlade.getCustomBlade(name),new Object[]{
				"S", "B", "I", 
			      Character.valueOf('I'), "blockQuartz", 
			      Character.valueOf('B'), SlashBlade.getCustomBlade(name), 
			      Character.valueOf('S'), soul
				}));
	}
}
