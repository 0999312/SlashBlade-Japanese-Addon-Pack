package cn.mmf.slashblade_addon.blades;

import cn.mcmod.sakura.block.BlockLoader;
import cn.mcmod.sakura.item.ItemLoader;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BladeBamboo {
	public static final String namekatana = "wrap.BambooMod.katana";

	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namekatana);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(45));
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F);
            ItemSlashBlade.TextureName.set(tag,"BambooKatana");
	        SlashBlade.registerCustomItemStack(namekatana, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namekatana);
	}

	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
	    ItemStack soul = SlashBlade.findItemStack("flammpfeil.slashblade", SlashBlade.ProudSoulStr, 1);

	    SlashBlade.addRecipe(namekatana, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namekatana), SlashBlade.getCustomBlade(namekatana), SlashBlade.getCustomBlade("slashbladeWrapper"),new Object[]{
				"  S", " W ", "B  ",
				Character.valueOf('S'), soul,
				Character.valueOf('B'), ItemLoader.SAKURAKATANA,
				Character.valueOf('W'), SlashBlade.findItemStack(SlashBlade.modid,"slashbladeWrapper",1)
		}));
	}
	@SubscribeEvent
	public void InitFoxRecipes(PostInitEvent event){
	    String nameWhite = "flammpfeil.slashblade.named.fox.white";
	    String nameBlack = "flammpfeil.slashblade.named.fox.black";
	    
	    ItemStack foxbladeReqired =SlashBlade.getCustomBlade(namekatana);
	    foxbladeReqired.addEnchantment(Enchantments.LOOTING,1);
	    NBTTagCompound reqTag1 = ItemSlashBlade.getItemTagCompound(foxbladeReqired);
        ItemSlashBlade.KillCount.set(reqTag1,199);
        ItemSlashBlade.ProudSoul.set(reqTag1,1000);
        ItemSlashBlade.RepairCount.set(reqTag1,1);
	    ItemStack fox = SlashBlade.findItemStack(SlashBlade.modid,nameWhite, 1);

	    ItemStack wheat = (Loader.isModLoaded("tofucraft"))
	    		?SlashBlade.findItemStack("tofucraft","foodset", 1):new ItemStack(Items.WHEAT,1);
	    		if((Loader.isModLoaded("tofucraft")))wheat.setItemDamage(10);
	    SlashBlade.addRecipe(nameWhite, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,nameWhite),fox, foxbladeReqired,
	    		new Object[]{"DAD", "DBD", "DHD",
	    				Character.valueOf('H'), wheat,
	    				Character.valueOf('A'), SlashBlade.findItemStack(SlashBlade.modid,SlashBlade.ProudSoulStr,1),
	    				Character.valueOf('B'), foxbladeReqired,
	    				Character.valueOf('D'), BlockLoader.KITUNEBI
	    		}));

	    ItemStack foxblade2Reqired =SlashBlade.getCustomBlade(namekatana);
	    foxblade2Reqired.addEnchantment(Enchantments.SMITE,1);
	    NBTTagCompound reqTag2 = ItemSlashBlade.getItemTagCompound(foxblade2Reqired);
        ItemSlashBlade.KillCount.set(reqTag2,199);
        ItemSlashBlade.ProudSoul.set(reqTag2,1000);
        ItemSlashBlade.RepairCount.set(reqTag2,1);
	    ItemStack fox2 = SlashBlade.findItemStack(SlashBlade.modid,"flammpfeil.slashblade.named.fox.black", 1);
	    SlashBlade.addRecipe(nameBlack, new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,nameBlack),fox2, foxblade2Reqired,
	    		new Object[]{"DAD", "DBD", "DHD",
	    				Character.valueOf('H'), wheat,
	    				Character.valueOf('A'), SlashBlade.findItemStack(SlashBlade.modid,SlashBlade.ProudSoulStr,1),
	    				Character.valueOf('B'), foxbladeReqired,
	    				Character.valueOf('D'), BlockLoader.KITUNEBI
	    		}));
	}
}
