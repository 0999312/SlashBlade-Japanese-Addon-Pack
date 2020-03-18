package cn.mmf.slashblade_addon.blades;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.event.DropEventHandler;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BladeDarkRaven {
	public static final String namedr = "flammpfeil.slashblade.named.darkraven";
	public static final String namesc = "flammpfeil.slashblade.named.snowcrow";
	@SubscribeEvent
	public void InitDR(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namedr);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(80));
	        ItemSlashBlade.setBaseAttackModifier(tag, 4.0F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/darkraven/darkraven");
	        ItemSlashBlade.ModelName.set(tag, "named/darkraven/darkraven");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(2));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        SlashBlade.registerCustomItemStack(namedr, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namedr);
	}
	@SubscribeEvent
	public void InitSC(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namesc);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(60));
	        ItemSlashBlade.setBaseAttackModifier(tag, 4.0F+ToolMaterial.IRON.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/darkraven/snowcrow");
	        ItemSlashBlade.ModelName.set(tag, "named/darkraven/snowcrow");
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        SlashBlade.registerCustomItemStack(namesc, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namesc);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
		ItemStack darkraven = SlashBlade.getCustomBlade(namedr);
		ItemStack snowcrow = SlashBlade.getCustomBlade(namesc);
		
		ItemStack doutanuki = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.doutanuki");
		
	    float fact = 0.05F;
	    try
	    {
	      SlashBlade.mainConfiguration.load();
	      
	      Property prop = SlashBlade.mainConfiguration.get("general", "DarkRavenDropRate", fact);
	      fact = (float)prop.getDouble();
	    }
	    finally
	    {
	      SlashBlade.mainConfiguration.save();
	    }
	    DropEventHandler.registerEntityDrop(new ResourceLocation("twilightforest", "raven"), fact, darkraven);
	    SlashBlade.addRecipe(namesc, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namesc), snowcrow, doutanuki, new Object[] { " FQ", "SQ ", "B  ", Character.valueOf('Q'),"blockQuartz", Character.valueOf('F'), new ItemStack(Items.FEATHER), Character.valueOf('S'), new ItemStack(Items.SNOWBALL), Character.valueOf('B'), doutanuki 
	    		}));
	}
	
}
