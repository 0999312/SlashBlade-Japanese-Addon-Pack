package cn.mmf.slashblade_addon.blades;

import cn.mmf.slashblade_addon.recipes.RecipeNihil;
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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BladeNihil {
	
	  public static final String namenl = "flammpfeil.slashblade.named.nihil";
	  public static final String nameex = "flammpfeil.slashblade.named.nihilex";
	  public static final String nameul = "flammpfeil.slashblade.named.nihilul";
	  public static final String namebx = "flammpfeil.slashblade.named.nihilbx";
	  public static final String namecc = "flammpfeil.slashblade.named.crimsoncherry";
	  
	@SubscribeEvent
	public void initnl(InitEvent event){
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
  
        customblade.addEnchantment(Enchantments.UNBREAKING, 3);
        customblade.addEnchantment(Enchantments.SHARPNESS, 2);
        customblade.addEnchantment(Enchantments.SMITE, 1);
        customblade.addEnchantment(Enchantments.FIRE_ASPECT, 1);

        ItemSlashBladeNamed.CurrentItemName.set(tag, namenl);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(45));
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
        ItemSlashBlade.setBaseAttackModifier(tag, 8.0F);
        ItemSlashBlade.TextureName.set(tag, "named/nihil/nihil");
        ItemSlashBlade.ModelName.set(tag, "named/nihil/nihil");
        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(1));
        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(1));
        
        SlashBlade.registerCustomItemStack(namenl, customblade);
        ItemSlashBladeNamed.NamedBlades.add(namenl);

	}
	@SubscribeEvent
	public void initex(InitEvent event){
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
  
        customblade.addEnchantment(Enchantments.UNBREAKING, 2);
        customblade.addEnchantment(Enchantments.SHARPNESS, 3);
        customblade.addEnchantment(Enchantments.SMITE, 2);
        customblade.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 1);
        customblade.addEnchantment(Enchantments.FIRE_ASPECT, 2);
        customblade.addEnchantment(Enchantments.LOOTING, 1);

        ItemSlashBladeNamed.CurrentItemName.set(tag, nameex);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(60));
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
        ItemSlashBlade.setBaseAttackModifier(tag, 10.0F);
        ItemSlashBlade.TextureName.set(tag, "named/nihil/nihilex");
        ItemSlashBlade.ModelName.set(tag, "named/nihil/nihil");
        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(2));
        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(1));
        
        SlashBlade.registerCustomItemStack(nameex, customblade);
        ItemSlashBladeNamed.NamedBlades.add(nameex);
	}
	@SubscribeEvent
	public void initul(InitEvent event){
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
  
        customblade.addEnchantment(Enchantments.UNBREAKING, 3);
        customblade.addEnchantment(Enchantments.SHARPNESS, 5);
        customblade.addEnchantment(Enchantments.SMITE, 3);
        customblade.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 2);
        customblade.addEnchantment(Enchantments.FIRE_ASPECT, 2);
        customblade.addEnchantment(Enchantments.LOOTING, 3);

        ItemSlashBladeNamed.CurrentItemName.set(tag, nameul);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(70));
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
        ItemSlashBlade.setBaseAttackModifier(tag, 12.0F);
        ItemSlashBlade.TextureName.set(tag, "named/nihil/nihilul");
        ItemSlashBlade.ModelName.set(tag, "named/nihil/nihil");
        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(2));
        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(1));
        
        SlashBlade.registerCustomItemStack(nameul, customblade);
        ItemSlashBladeNamed.NamedBlades.add(nameul);
	}
	
	@SubscribeEvent
	public void initcc(InitEvent event){
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);

        customblade.addEnchantment(Enchantments.SHARPNESS, 5);
        customblade.addEnchantment(Enchantments.SMITE, 3);
        customblade.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 3);
        customblade.addEnchantment(Enchantments.FIRE_ASPECT, 2);

        ItemSlashBladeNamed.CurrentItemName.set(tag, namecc);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(65));
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
        ItemSlashBlade.setBaseAttackModifier(tag, 11.0F);
        ItemSlashBlade.TextureName.set(tag, "named/nihil/crimsoncherry");
        ItemSlashBlade.ModelName.set(tag, "named/nihil/nihil");
        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(7));
        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(1));
        
        SlashBlade.registerCustomItemStack(namecc, customblade);
        ItemSlashBladeNamed.NamedBlades.add(namecc);
	}
	
	@SubscribeEvent
	public void initbx(InitEvent event){
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
  
        customblade.addEnchantment(Enchantments.UNBREAKING, 3);
        customblade.addEnchantment(Enchantments.SHARPNESS, 5);
        customblade.addEnchantment(Enchantments.SMITE, 5);
        customblade.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 5);
        customblade.addEnchantment(Enchantments.FIRE_ASPECT, 2);

        ItemSlashBladeNamed.CurrentItemName.set(tag, namebx);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(240));
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
        ItemSlashBlade.setBaseAttackModifier(tag, 13.0F);
        ItemSlashBlade.TextureName.set(tag, "named/nihil/nihil_bx");
        ItemSlashBlade.ModelName.set(tag, "named/nihil/nihil");
        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(7));
        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(1));
        
        SlashBlade.registerCustomItemStack(namebx, customblade);
        ItemSlashBladeNamed.NamedBlades.add(namebx);
	}
	@SubscribeEvent
	public void initRecipes(PostInitEvent event){
		ItemStack nihil = SlashBlade.getCustomBlade(namenl);
		ItemStack nihilex = SlashBlade.getCustomBlade(nameex);
		ItemStack nihilul = SlashBlade.getCustomBlade(nameul);
		ItemStack nihilcc = SlashBlade.getCustomBlade(namecc);
		ItemStack nihilbx = SlashBlade.getCustomBlade(namebx);
		
	    ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
	    ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	    
		ItemStack reqblade_nl=new ItemStack(SlashBlade.weapon);
		reqblade_nl.setItemDamage(Short.MAX_VALUE);
		
		SlashBlade.addRecipe(namenl, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namenl), nihil, reqblade_nl
				, new Object[]{
						"SIS","IBI","SIS",'I',ingot,'S',sphere,'B',reqblade_nl
				}));
		ItemStack reqblade_ex=SlashBlade.getCustomBlade(namenl);
	    NBTTagCompound tag_ex = ItemSlashBlade.getItemTagCompound(reqblade_ex);
	    ItemSlashBlade.RepairCount.set(tag_ex, Integer.valueOf(1));
	    ItemSlashBlade.KillCount.set(tag_ex, Integer.valueOf(1000));
	    ItemSlashBlade.ProudSoul.set(tag_ex, Integer.valueOf(1000));
	    if (tag_ex.hasKey("ench")) {
	      tag_ex.removeTag("ench");
	    }
		SlashBlade.addRecipe(nameex, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",nameex), nihilex, reqblade_ex
				, new Object[]{
						"SNS","IBI","SDS", 'S', sphere, 'I', ingot, 'B', reqblade_ex, 'N', Items.NETHER_STAR, 'D', "blockDiamond"
				}));
		ItemStack reqblade_ul=SlashBlade.getCustomBlade(nameex);
	    NBTTagCompound tag_ul = ItemSlashBlade.getItemTagCompound(reqblade_ul);
	    ItemStack yamato = SlashBlade.getCustomBlade("flammpfeil.slashblade.named.yamato");
	    ItemSlashBlade.RepairCount.set(tag_ul, Integer.valueOf(3));
	    ItemSlashBlade.KillCount.set(tag_ul, Integer.valueOf(3000));
	    ItemSlashBlade.ProudSoul.set(tag_ul, Integer.valueOf(6500));
	    if (tag_ul.hasKey("ench")) {
	      tag_ul.removeTag("ench");
	    }
	    SlashBlade.addRecipe(nameul,new RecipeNihil( new ResourceLocation("flammpfeil.slashblade",nameul), nihilul, reqblade_ul, 1, 1, yamato, 1, 2, true,
				new Object[]{
						"SNS", "DBD", "SYS", 'S', SlashBlade.weapon, 'Y', yamato, 'B', reqblade_ul, 'N', Items.NETHER_STAR, 'D', "blockDiamond"
				}));
		ItemStack reqblade_cc=SlashBlade.getCustomBlade(nameex);
	    NBTTagCompound tag_cc = ItemSlashBlade.getItemTagCompound(reqblade_cc);
	    ItemSlashBlade.RepairCount.set(tag_cc, Integer.valueOf(3));
	    ItemSlashBlade.KillCount.set(tag_cc, Integer.valueOf(3000));
	    ItemSlashBlade.ProudSoul.set(tag_cc, Integer.valueOf(6500));
	    if (tag_cc.hasKey("ench")) {
	      tag_cc.removeTag("ench");
	    }
	    SlashBlade.addRecipe(namecc,new RecipeNihil( new ResourceLocation("flammpfeil.slashblade",namecc), nihilcc, reqblade_cc, 1, 1, nihil, 1, 0, false,
				new Object[]{
						"DSD", "DMD", "DDD", 'S', nihil, 'M', reqblade_cc, 'D', "blockDiamond"
				}));
	    
	    SlashBlade.addRecipe(namebx,new RecipeNihil( new ResourceLocation("flammpfeil.slashblade",namebx), nihilbx, nihilul, 0, 1, nihilcc, 2, 1, false,
				new Object[]{
						"DDD",
						"ACB",
						"DDD",
						'A', nihilul,
						'B', nihilcc,
						'C', SlashBlade.weapon,
						'D', "blockDiamond"
				}));
	}
}
