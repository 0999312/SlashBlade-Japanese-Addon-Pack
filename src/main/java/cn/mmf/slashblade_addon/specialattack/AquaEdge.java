package cn.mmf.slashblade_addon.specialattack;

import cn.mmf.slashblade_addon.entity.EntityAquaEdge;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class AquaEdge extends SpecialAttackBase
{
	public static String AttackType = StylishRankManager.AttackTypes.registerAttackType("AquaEdge", 0.5F);

	private static final int COST = 10;

	private static final int NO_COST_DAMAGE = 5;

	private static final float SPEED = 1.5f;
	private static final int LIFE_TIME = 10;
	@Override
	public String toString()
	{
		return "aquaedge";
	}
 
	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player)
	{
		World world = player.world;
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
    
		player.playSound(SoundEvents.ENTITY_PLAYER_SWIM, 1.0f, 1.5f);

		None.spawnParticle(EnumParticleTypes.WATER_SPLASH, player, 30, 2.0);
    
		if (player.isBurning()) {
			player.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE,
							 0.7f,
							 1.2f + 0.8f*player.getRNG().nextFloat());
			player.extinguish();
		}
    
		if (!world.isRemote) {
			if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -COST, false))
				ItemSlashBlade.damageItem(stack, NO_COST_DAMAGE, player);
      
			ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();

			int rank = StylishRankManager.getStylishRank(player);
			float damage = blade.getBaseAttackModifiers(tag);
			if (rank >= 5) {
				int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
				damage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.5f + level / 5.0f);
			}

			EntityAquaEdge entity = new EntityAquaEdge(world, player, damage);
			entity.setLifeTime(LIFE_TIME);
			entity.setInitialPosition(
				player.posX,
				player.posY + entity.getYOffset(),
				player.posZ,
				player.rotationYaw,
				player.rotationPitch,
				90.0f - ItemSlashBlade.ComboSequence.Iai.swingDirection,
				SPEED);
			
			world.spawnEntity(entity);
		}
    
		ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Iai);
	}
}
