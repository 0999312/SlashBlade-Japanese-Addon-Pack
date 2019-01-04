package cn.mmf.slashblade_addon.specialattack;

import java.util.List;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class SpiralEdge	extends SpecialAttackBase
{
	public static String AttackType = StylishRankManager.AttackTypes.registerAttackType("SpiralEdge", 0.5f);

	private static final int COST = 40;

	private static final int NO_COST_DAMAGE = 10;
	
	@Override
	public String toString()
	{
		return "spiraledge";
	}

	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player)
	{
		World world = player.world;
		if (world.isRemote)
			return;

		player.playSound(SoundEvents.ENTITY_BLAZE_HURT, 0.2f, 0.6f);

		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
		if (!ItemSlashBlade.ProudSoul.tryAdd(tag, -COST, false))
			ItemSlashBlade.damageItem(stack, NO_COST_DAMAGE, player);
      
		ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();

		AxisAlignedBB bb = player.getEntityBoundingBox()
			.expand(5.0, 0.25, 5.0);
      
		List<Entity> list = world.getEntitiesInAABBexcluding(player, bb, EntitySelectorAttackable.getInstance());

		if (!stack.isEmpty()) {
			if (!list.isEmpty())
				StylishRankManager.setNextAttackType(player, AttackType);
      
			for (Entity curEntity : list) {
				blade.attackTargetEntity(stack, curEntity, player, true);
				player.onCriticalHit(curEntity);
			}
		}
      

		int rank = StylishRankManager.getStylishRank(player);

		float damage = blade.getBaseAttackModifiers(tag) / 2.0f;
		if (rank >= 5) {
			int level = Math.max(1, EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack));
			damage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.25f + level / 5.0f);
		}
      
		final int maxCount = 5 + rank;
		final double radBaseRot = Math.toRadians(player.rotationYaw);
		final double radRot = 2.0*Math.PI / maxCount;

		for (int i = 0; i < maxCount; i++) {
			EntityDrive entity = new EntityDrive(world, player, damage, false, 0.0f);

			double x = player.posX + Math.cos(radBaseRot + radRot*i);
			double y = player.posY + 0.7*Math.sin(-Math.PI/3.0 + radRot*i);
			double z = player.posZ + Math.sin(radBaseRot + radRot*i);
			float yaw = player.rotationYaw +(float)Math.toDegrees(radRot*i);
			float pitch = (float)(Math.cos(-Math.PI/3.0 + radRot*i));
			if (pitch < 0.0f)
				pitch = 1.0f;
			else
				pitch *= -30.0f;
			
			entity.setLocationAndAngles(x, y, z, yaw, pitch);
        
			entity.setDriveVector(0.3F);
			entity.setLifeTime(15);
			entity.setIsMultiHit(false);
			entity.setRoll(90.0f - 30.0f*(float)Math.cos(Math.PI/6 + radRot*i));

			world.spawnEntity(entity);
		}

		ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Battou);
	}
}
