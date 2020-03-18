package cn.mmf.slashblade_addon.blades;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BladeKamuy {
	public static final String namebase = "flammpfeil.slashblade.named.kamuy.base";
	public static final String namewater = "flammpfeil.slashblade.named.kamuy.water";
	public static final String namefire = "flammpfeil.slashblade.named.kamuy.fire";
	public static final String namelightning = "flammpfeil.slashblade.named.kamuy.lightning";

	@SubscribeEvent
	public void InitBase(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.LOOTING, 1);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namebase);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 60);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/kamuy/kamuy");
	        ItemSlashBlade.ModelName.set(tag, "named/kamuy/kamuy");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(35));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));

	        SlashBlade.registerCustomItemStack(namebase, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namebase);
	}
	@SubscribeEvent
	public void InitWater(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.UNBREAKING, 3);
	        customblade.addEnchantment(Enchantments.LOOTING, 3);
	        customblade.addEnchantment(Enchantments.KNOCKBACK, 2);
	        customblade.addEnchantment(Enchantments.RESPIRATION, 1);

	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namewater);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 60);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/kamuy/water");
	        ItemSlashBlade.ModelName.set(tag, "named/kamuy/kamuy");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(36));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        SlashBlade.registerCustomItemStack(namewater, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namewater);
	}
	@SubscribeEvent
	public void InitFire(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.UNBREAKING, 3);
	        customblade.addEnchantment(Enchantments.FIRE_ASPECT, 2);
	        customblade.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 5);
	        customblade.addEnchantment(Enchantments.BLAST_PROTECTION, 1);
	        
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namefire);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 60);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/kamuy/fire");
	        ItemSlashBlade.ModelName.set(tag, "named/kamuy/kamuy");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(37));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));

	        SlashBlade.registerCustomItemStack(namefire, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namefire);
	}
	@SubscribeEvent
	public void InitLightning(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        customblade.addEnchantment(Enchantments.UNBREAKING, 3);
	        customblade.addEnchantment(Enchantments.SMITE, 5);
	        customblade.addEnchantment(Enchantments.FEATHER_FALLING, 4);
	        
	        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
	        ItemSlashBladeNamed.CurrentItemName.set(tag, namelightning);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, 60);
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.DIAMOND.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/kamuy/lightning");
	        ItemSlashBlade.ModelName.set(tag, "named/kamuy/kamuy");
	        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(38));
	        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
	        
	        SlashBlade.registerCustomItemStack(namelightning, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(namelightning);
	}
	@SubscribeEvent
	public void InitRecipes(PostInitEvent event){
        ItemStack sphere = SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.SphereBladeSoulStr, 1);

        ItemStack blade_base = SlashBlade.getCustomBlade(namebase);
        ItemStack blade_water = SlashBlade.getCustomBlade(namewater);
        ItemStack blade_fire = SlashBlade.getCustomBlade(namefire);
        ItemStack blade_lightning = SlashBlade.getCustomBlade(namelightning);
        
		ItemStack reqblade_base = new ItemStack(SlashBlade.weapon);
		NBTTagCompound reqtag_base = ItemSlashBlade.getItemTagCompound(reqblade_base);
		ItemSlashBlade.RepairCount.set(reqtag_base, 5);
		ItemSlashBlade.KillCount.set(reqtag_base, 1000);
		ItemSlashBlade.ProudSoul.set(reqtag_base, 1000);
		reqblade_base.addEnchantment(Enchantments.LOOTING, 1);
		SlashBlade.addRecipe(namebase, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namebase), blade_base, reqblade_base, new Object[]{
				"SQS","IKI","SBS",'S', sphere,'K', reqblade_base,'Q', "gemQuartz",'I', "blockIron",'B', Items.BOOK
		}));
		ItemStack reqblade = SlashBlade.getCustomBlade(namebase);
		NBTTagCompound reqtag = ItemSlashBlade.getItemTagCompound(reqblade);
		ItemSlashBlade.RepairCount.set(reqtag, 20);
		ItemSlashBlade.KillCount.set(reqtag, 2000);
		ItemSlashBlade.ProudSoul.set(reqtag, 5000);
		SlashBlade.addRecipe(namewater, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namewater), blade_water, reqblade, new Object[]{
				"S8S","4K6","S2S",
				'S', sphere,'K', reqblade,'8',"blockLapis",'4', Blocks.ICE,'6', Blocks.SNOW,'2', Items.WATER_BUCKET
		}));
		SlashBlade.addRecipe(namefire, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namefire), blade_fire, reqblade, new Object[]{
				"S8S","4K6","S2S",
				'S', sphere,'K', reqblade,'8',"blockRedstone",'4',Items.FIRE_CHARGE,'6', Items.BLAZE_ROD,'2', Items.LAVA_BUCKET
		}));
		SlashBlade.addRecipe(namelightning, new RecipeAwakeBlade(new ResourceLocation("flammpfeil.slashblade",namelightning), blade_lightning, reqblade, new Object[]{
				"S8S","4K6","S2S",
				'S', sphere,'K', reqblade,'8',"blockIron",'4', "blockGold",'6', "blockDiamond",'2', "blockEmerald"
		}));
	}
}
