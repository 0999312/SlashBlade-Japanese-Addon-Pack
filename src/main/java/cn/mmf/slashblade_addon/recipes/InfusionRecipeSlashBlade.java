package cn.mmf.slashblade_addon.recipes;

import java.util.List;

import mods.flammpfeil.slashblade.item.ItemProudSoul;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.crafting.InfusionRecipe;

public class InfusionRecipeSlashBlade extends InfusionRecipe {
	public InfusionRecipeSlashBlade(String research, ItemStack outputResult, int inst, AspectList aspects2,
			ItemStack centralItem, Object[] recipe) {
		super(research, outputResult, inst, aspects2, centralItem, recipe);
		this.sourceInput = CraftingHelper.getIngredient(centralItem);
		this.components.clear();
		for (Object in : recipe) {
			Ingredient ing = ThaumcraftApiHelper.getIngredient(in);
			if (in != null && in instanceof ItemStack) {
				if (((ItemStack) in).getItem() instanceof ItemSlashBlade
						|| ((ItemStack) in).getItem() instanceof ItemProudSoul)
					ing = CraftingHelper.getIngredient(in);
			}
			if (ing != null) {
				this.components.add(ing);
			} else {
				String ret = "Invalid infusion recipe: ";
				for (Object tmp : recipe)
					ret = ret + tmp + ", ";
				ret = ret + outputResult;
				throw new RuntimeException(ret);
			}
		}
	}

	@Override
	public boolean matches(List<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
		if (getRecipeInput() == null)
			return false;
		if (!ThaumcraftCapabilities.getKnowledge(player).isResearchKnown(this.research))
			return false;
		central.setItemDamage(OreDictionary.WILDCARD_VALUE);
		return (getRecipeInput() == Ingredient.EMPTY
				|| getRecipeInput().apply(central)) && RecipeMatcher.findMatches(input, getComponents()) != null;
	}

}
