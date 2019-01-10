package cn.mmf.slashblade_addon.specialattack;

import java.util.Random;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class None extends SpecialAttackBase
{
	public static String AttackType = StylishRankManager.AttackTypes.registerAttackType("Provoke", -1.0f);

	private static final int COST = 10;
	
	@Override
	public String toString()
	{
		return "none";
	}

	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player)
	{
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
		if (ItemSlashBlade.ProudSoul.tryAdd(tag, -COST, false)) {

			spawnParticle(EnumParticleTypes.SPELL_WITCH, player, 20, 1.0);
			StylishRankManager.setNextAttackType(player, AttackType);
			StylishRankManager.doAttack(player);
	
		} else {
			spawnParticle(EnumParticleTypes.SMOKE_LARGE, player, 20, 1.0);
		}
	}

	public static void spawnParticle(EnumParticleTypes type, EntityPlayer player, int num, double rate)
	{

		World world = player.world;
		Random rand = player.getRNG();
		
		for (int i = 0; i < num; i++) {
			double xSpeed = rand.nextGaussian() * 0.02;
			double ySpeed = rand.nextGaussian() * 0.02;
			double zSpeed = rand.nextGaussian() * 0.02;

			double rx = rand.nextDouble();
			double rz = rand.nextDouble();
			
			world.spawnParticle(
				type,
				player.posX + ((rx*2 - 1)*player.width  - xSpeed * 10.0)*rate,
				player.posY,
				player.posZ + ((rz*2 - 1)*player.width  - zSpeed * 10.0)*rate,
				xSpeed, ySpeed, zSpeed);
		}
	}
}
