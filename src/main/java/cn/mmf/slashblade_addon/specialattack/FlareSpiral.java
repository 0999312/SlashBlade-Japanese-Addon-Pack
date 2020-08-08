package cn.mmf.slashblade_addon.specialattack;

import java.util.List;

import cn.mcmod_mmf.mmlib.util.MathUtil;
import cn.mmf.slashblade_addon.entity.EntityFlareEdge;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlareSpiral extends SpecialAttackBase
{
	public static String AttackType = StylishRankManager.AttackTypes.registerAttackType("FlareSpiral", 0.5F);

	private static final int COST = 40;

	private static final int NO_COST_DAMAGE = 10;

	@Override
	public String toString()
	{
		return "flarespiral";
	}

	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player)
	{
		World world = player.world;
    
		world.playEvent(player, 1009,
						new BlockPos(player.posX, player.posY, player.posZ),
						0);

		None.spawnParticle(EnumParticleTypes.FLAME, player, 20, 5.0);
   
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
		if (!world.isRemote) {

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
      
			final int count = 5 + rank;
			final float rotUnit = 360.0f / count;

			for (int i = 0; i < count; i++) {
				EntityFlareEdge entity = new EntityFlareEdge(world, player, damage);
				entity.setLifeTime(15);

				final float rot = rotUnit*i;

				float yaw = player.rotationYaw + rot;
				float pitch = -30*(float)MathUtil.getInstance().cos(rot - 60);
				if (pitch > 0.0f)
					pitch = 1.0f;
				
				double x = MathUtil.getInstance().cos(yaw);
				double y = 0.7*MathUtil.getInstance().sin(rot - 60);
				double z = MathUtil.getInstance().sin(yaw);
				
				entity.setInitialPosition(
					player.posX + x,
					player.posY + y + 0.5f,
					player.posZ + z,
					yaw,
					pitch,
					90.0f - 30.0f*(float)MathUtil.getInstance().cos(rot + 30),
					0.3f);

				world.spawnEntity(entity);
			}
		}

		ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Battou);

	}
}
