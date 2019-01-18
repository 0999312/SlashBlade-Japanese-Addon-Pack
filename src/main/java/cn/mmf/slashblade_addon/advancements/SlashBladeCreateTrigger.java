package cn.mmf.slashblade_addon.advancements;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;

import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class SlashBladeCreateTrigger implements ICriterionTrigger<SlashBladeCreateTrigger.Instance>
{
    private static final ResourceLocation ID = new ResourceLocation(SlashBlade.modid,"slashblade_create");
    private final Map<PlayerAdvancements, SlashBladeCreateTrigger.Listeners> listeners = Maps.<PlayerAdvancements, SlashBladeCreateTrigger.Listeners>newHashMap();

    public ResourceLocation getId()
    {
        return ID;
    }

    public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<SlashBladeCreateTrigger.Instance> listener)
    {
        SlashBladeCreateTrigger.Listeners recipeunlockedtrigger$listeners = this.listeners.get(playerAdvancementsIn);

        if (recipeunlockedtrigger$listeners == null)
        {
            recipeunlockedtrigger$listeners = new SlashBladeCreateTrigger.Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, recipeunlockedtrigger$listeners);
        }

        recipeunlockedtrigger$listeners.add(listener);
    }

    public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<SlashBladeCreateTrigger.Instance> listener)
    {
        SlashBladeCreateTrigger.Listeners recipeunlockedtrigger$listeners = this.listeners.get(playerAdvancementsIn);

        if (recipeunlockedtrigger$listeners != null)
        {
            recipeunlockedtrigger$listeners.remove(listener);

            if (recipeunlockedtrigger$listeners.isEmpty())
            {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
    }

    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn)
    {
        this.listeners.remove(playerAdvancementsIn);
    }

    /**
     * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
     */
    public SlashBladeCreateTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
    {
        ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(json, "recipe"));
        IRecipe irecipe = CraftingManager.getRecipe(resourcelocation);

        if (irecipe == null)
        {
            throw new JsonSyntaxException("Unknown recipe '" + resourcelocation + "'");
        }
        else if(!(irecipe.getRecipeOutput().getItem() instanceof ItemSlashBlade)){
        	throw new JsonSyntaxException("Unknown recipe out_put '" + resourcelocation + "'");
        }
        else
        {
            return new SlashBladeCreateTrigger.Instance(irecipe);
        }
    }

    public void trigger(EntityPlayerMP player, IRecipe recipe)
    {
        SlashBladeCreateTrigger.Listeners recipeunlockedtrigger$listeners = this.listeners.get(player.getAdvancements());

        if (recipeunlockedtrigger$listeners != null)
        {
            recipeunlockedtrigger$listeners.trigger(recipe);
        }
    }

    public static class Instance extends AbstractCriterionInstance
        {
            private final IRecipe recipe;

            public Instance(IRecipe recipe)
            {
                super(SlashBladeCreateTrigger.ID);
                this.recipe = recipe;
            }

            public boolean test(IRecipe recipe)
            {
                return this.recipe == recipe && recipe.getRecipeOutput().getItem() instanceof ItemSlashBlade;
            }
        }

    static class Listeners
        {
            private final PlayerAdvancements playerAdvancements;
            private final Set<ICriterionTrigger.Listener<SlashBladeCreateTrigger.Instance>> listeners = Sets.<ICriterionTrigger.Listener<SlashBladeCreateTrigger.Instance>>newHashSet();

            public Listeners(PlayerAdvancements playerAdvancementsIn)
            {
                this.playerAdvancements = playerAdvancementsIn;
            }

            public boolean isEmpty()
            {
                return this.listeners.isEmpty();
            }

            public void add(ICriterionTrigger.Listener<SlashBladeCreateTrigger.Instance> listener)
            {
                this.listeners.add(listener);
            }

            public void remove(ICriterionTrigger.Listener<SlashBladeCreateTrigger.Instance> listener)
            {
                this.listeners.remove(listener);
            }

            public void trigger(IRecipe recipe)
            {
                List<ICriterionTrigger.Listener<SlashBladeCreateTrigger.Instance>> list = null;

                for (ICriterionTrigger.Listener<SlashBladeCreateTrigger.Instance> listener : this.listeners)
                {
                    if (((SlashBladeCreateTrigger.Instance)listener.getCriterionInstance()).test(recipe))
                    {
                        if (list == null)
                        {
                            list = Lists.<ICriterionTrigger.Listener<SlashBladeCreateTrigger.Instance>>newArrayList();
                        }

                        list.add(listener);
                    }
                }

                if (list != null)
                {
                    for (ICriterionTrigger.Listener<SlashBladeCreateTrigger.Instance> listener1 : list)
                    {
                        listener1.grantCriterion(this.playerAdvancements);
                    }
                }
            }
        }
}