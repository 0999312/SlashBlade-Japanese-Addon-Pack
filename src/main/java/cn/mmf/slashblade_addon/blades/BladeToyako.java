package cn.mmf.slashblade_addon.blades;

import java.util.Random;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import mods.flammpfeil.slashblade.named.event.LoadEvent.PostInitEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.entity.passive.EntityVillager.PriceInfo;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import static net.minecraft.entity.passive.EntityVillager.EmeraldForItems;
import static net.minecraft.entity.passive.EntityVillager.ListEnchantedItemForEmeralds;
import static net.minecraft.entity.passive.EntityVillager.ListItemForEmeralds;

public class BladeToyako {
	public static final String name = "flammpfeil.slashblade.named.toyako";

	@SubscribeEvent
	public void InitKatana(InitEvent event){
	     ItemStack customblade = new ItemStack(SlashBlade.bladeNamed,1,0);
	        NBTTagCompound tag = new NBTTagCompound();
	        customblade.setTagCompound(tag);
	        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
	        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(70));
	        ItemSlashBlade.setBaseAttackModifier(tag, 4F+ToolMaterial.STONE.getAttackDamage());
	        ItemSlashBlade.TextureName.set(tag, "named/toyako/toyako");
	        ItemSlashBlade.ModelName.set(tag, "named/toyako/toyako");
	        
	        SlashBlade.registerCustomItemStack(name, customblade);
	        ItemSlashBladeNamed.NamedBlades.add(name);
	}
	@SubscribeEvent
	public void registRecipe(PostInitEvent event){
		@SuppressWarnings("deprecation")
		VillagerProfession prof = VillagerRegistry.getById(3);
		VillagerCareer career = new VillagerCareer(prof, "weapon2");
		career
			.addTrade(1, new EmeraldForItems(Items.COAL, new PriceInfo(16, 24)))
			.addTrade(1, new ListItemForEmeralds(Items.IRON_AXE, new PriceInfo(6, 8)))
			.addTrade(1, new SimpleTrade(SlashBlade.getCustomBlade(name), new PriceInfo(3, 7)))
			.addTrade(2, new EmeraldForItems(Items.IRON_INGOT, new PriceInfo(7, 9)))
			.addTrade(2, new ListEnchantedItemForEmeralds(Items.IRON_SWORD, new PriceInfo(9, 10)))
			.addTrade(3, new EmeraldForItems(Items.DIAMOND, new PriceInfo(3, 4)))
			.addTrade(3, new ListEnchantedItemForEmeralds(Items.DIAMOND_SWORD, new PriceInfo(12, 15)))
			.addTrade(3, new ListEnchantedItemForEmeralds(Items.DIAMOND_AXE, new PriceInfo(9, 12)))
			;
    }
    private static class SimpleTrade implements ITradeList{
		private ItemStack item;
		private PriceInfo price;

		public SimpleTrade(ItemStack item, PriceInfo price){
			this.item = item;
			this.price = price;
		}

		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
		{
			int i = price != null ? price.getPrice(random) : 1;
			recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD, i, 0),item.copy()));
        }
	}
}
