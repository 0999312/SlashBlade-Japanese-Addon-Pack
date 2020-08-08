package cn.mmf.slashblade_addon.specialattack;

import java.util.Random;

import cn.mcmod_mmf.mmlib.util.MathUtil;
import cn.mmf.slashblade_addon.entity.EntityPhantomSwordEx;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class RapidPhantomSwords extends PhantomSwordsBase
{
	public static String AttackType = StylishRankManager.AttackTypes.registerAttackType("RapidPhantomSwords", 0.5F);
	
	@Override
	public String toString()
	{
		return "rapidphantomswords";
	}

	@Override
	protected void spawnEntity(float damage, int count,
							   Entity target, EntityPlayer player)
	{
		World world = player.world;
		int targetId = target.getEntityId();

		Random rnd = player.getRNG();

		for (int i = 0; i < count; i++) {

			EntityPhantomSwordEx entity = new EntityPhantomSwordEx(world, player, damage, this);

			entity.setInterval(7 + i * 2);
			entity.setLifeTime(30);
			entity.setTargetEntityId(targetId);

			double d = rnd.nextDouble()*2.0 - 1.0;

			double x = 2.0 * d * MathUtil.getInstance().cos(player.rotationYaw);
			double y = 2.0 * (1.0 - Math.abs(d)) + 0.5;
			double z = 2.0 * d * MathUtil.getInstance().sin(player.rotationYaw);
			
			entity.setInitialPosition(
				player.posX + x,
				player.posY + y,
				player.posZ + z,
				player.rotationYaw,
				player.rotationPitch,
				90.0f,
				EntityPhantomSwordEx.SPEED);
              
			world.spawnEntity(entity);
		}
	}

	@Override
	public void onImpact(Entity target)
	{
		target.addVelocity(-target.motionX, 0.1, -target.motionZ);
	}
}
