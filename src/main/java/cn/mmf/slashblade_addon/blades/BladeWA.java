package cn.mmf.slashblade_addon.blades;

import cn.mcmod.sakura.item.ItemLoader;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
	public void InitRecipes(PostInitEvent event){
	    ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1);

	    SlashBlade.addRecipe(namekatana, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namekatana), SlashBlade.getCustomBlade(namekatana), SlashBlade.getCustomBlade("slashbladeWrapper"),new Object[]{
				"  S", " W ", "B  ",
				Character.valueOf('S'), soul,
				Character.valueOf('B'), ItemLoader.KATANA,
				Character.valueOf('W'), SlashBlade.findItemStack(SlashBlade.modid,"slashbladeWrapper",1)
		}));
	    SlashBlade.addRecipe(nametachi, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",nametachi), SlashBlade.getCustomBlade(nametachi), SlashBlade.getCustomBlade("slashbladeWrapper"),new Object[]{
				"  S", " W ", "B  ",
				Character.valueOf('S'), soul,
				Character.valueOf('B'), ItemLoader.TACHI,
				Character.valueOf('W'), SlashBlade.findItemStack(SlashBlade.modid,"slashbladeWrapper",1)
		}));
	}
}
