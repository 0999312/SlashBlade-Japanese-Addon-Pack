package cn.mmf.slashblade_addon.specialeffect;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraSword;

public class ManaBurst implements ISpecialEffect
{
  public static final String EffectKey = "ManaBurst";
  
  @SubscribeEvent
  public void onUpdateItemSlashBlade(SlashBladeEvent.OnUpdateEvent event)
  {
    if (!event.isCurrent) {
      return;
    }
    if (event.world.isRemote) {
      return;
    }
    if (!(event.entity instanceof EntityPlayer)) {
      return;
    }
    EntityPlayer player = (EntityPlayer)event.entity;
    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
    
    NBTTagCompound etag = tag.getCompoundTag("SB.SEffect");
    
    int reqiredLevel = etag.getInteger("ManaBurst");
    if (reqiredLevel == 0) {
      return;
    }
    ItemSlashBlade.ComboSequence seq = ItemSlashBlade.getComboSequence(tag);
    if (seq == ItemSlashBlade.ComboSequence.None) {
      return;
    }
    if (seq == ItemSlashBlade.ComboSequence.Noutou) {
      return;
    }
    if (seq.useScabbard) {
      return;
    }
    boolean doBurst = reqiredLevel <= player.experienceLevel;
    if (!doBurst) {
      return;
    }
    if (player.swingProgressInt != 4) {
      return;
    }
    ItemStack terraSwordStack = SlashBlade.findItemStack("botania", "terraSword", 1);
    if (!ManaItemHandler.requestManaExactForTool(terraSwordStack, player, 100, true)) {
      return;
    }
    EntityManaBurst burst = ((ItemTerraSword)terraSwordStack.getItem()).getBurst(player, terraSwordStack);
    event.world.spawnEntity(burst);
  }

  	@Override
  	public int getDefaultRequiredLevel() {
	return 10;
	}

	@Override
	public String getEffectKey() {
	return EffectKey;
	}

    @Override
	public void register()
	{
		SlashBladeHooks.EventBus.register(this);
	}
}

