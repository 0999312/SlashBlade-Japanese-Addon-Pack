package cn.mmf.slashblade_addon.specialattack;

import java.util.Random;

import cn.mcmod_mmf.mmlib.util.MathUtil;
import cn.mmf.slashblade_addon.entity.EntityLightningSword;
import cn.mmf.slashblade_addon.entity.EntityPhantomSwordEx;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LightningSwords extends PhantomSwordsBase
{
	public static String AttackType = StylishRankManager.AttackTypes.registerAttackType("LightningSwords", 0.5F);

	@Override
	public String toString()
	{
		return "lightningswords";
	}

	@Override
	protected void spawnEntity(float damage, int count,
							   Entity target, EntityPlayer player)
	{

		final World world = player.world;
		final int targetId = target.getEntityId();


		final float rotUnit = 360.0f / count;
		final Random rnd = player.getRNG();

		for (int i = 0; i < count; i++) {
			EntityPhantomSwordEx entity = new EntityPhantomSwordEx(world, player, damage, this);
			entity.setLifeTime(30);
			entity.setTargetEntityId(targetId);
			entity.setColor(0x705DA8);
			entity.setInterval(7 + i / 2);

			final float rot = rotUnit*i;
			
			double x = -2.0*MathUtil.getInstance().sin(rot);
			double y =  2.0*(1.0 + rnd.nextDouble()) + 0.5;
			double z =  2.0*MathUtil.getInstance().cos(rot);

			entity.setInitialPosition(
				target.posX + x,
				target.posY + y,
				target.posZ + z,
				rot + 180,
				90.0f,
				90.0f,
				EntityPhantomSwordEx.SPEED);

			world.spawnEntity(entity);
		}

		EntityLightningSword entity = new EntityLightningSword(world, player, damage);
		entity.setColor(0xFFD700);
		entity.setInterval(7 + count / 2 + 10);
		entity.setLifeTime(40);
						
		entity.setTargetEntityId(targetId);

		entity.setInitialPosition(
			target.posX,
			target.posY + 4.5,
			target.posZ,
			180.0f,
			90.0f,
			90.0f,
			EntityPhantomSwordEx.SPEED);
		
		world.spawnEntity(entity);
	}

	public void onImpact(Entity target)
	{
		target.motionX = 0.0;
		target.motionY = 0.0;
		target.motionZ = 0.0;
	}

	public void onSticking(Entity target)
	{
		target.motionX = 0.0;
		target.motionY = 0.0;
		target.motionZ = 0.0;

	}
	
}
