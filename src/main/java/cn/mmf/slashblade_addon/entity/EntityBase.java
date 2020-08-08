package cn.mmf.slashblade_addon.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.mcmod_mmf.mmlib.util.MathUtil;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorDestructable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

abstract class EntityBase extends Entity implements IThrowableEntity {

	protected EntityLivingBase thrower_ = null;

	protected ItemStack blade_ = ItemStack.EMPTY;

	protected List<Entity> alreadyHitEntity_ = new ArrayList<Entity>();

	protected float attackLevel_ = 0.0f;

	private static final DataParameter<Integer> LIFETIME = EntityDataManager.<Integer>createKey(EntityBase.class,
			DataSerializers.VARINT);

	private static final DataParameter<Float> ROLL = EntityDataManager.<Float>createKey(EntityBase.class,
			DataSerializers.FLOAT);

	private static final DataParameter<Integer> COLOR = EntityDataManager.<Integer>createKey(EntityBase.class,
			DataSerializers.VARINT);

	public EntityBase(World worldIn) {
		super(worldIn);
	}

	public EntityBase(World worldIn, EntityLivingBase thrower, float attackLevel) {
		super(worldIn);

		this.ticksExisted = 0;

		this.thrower_ = thrower;
		this.attackLevel_ = attackLevel;

		this.blade_ = thrower.getHeldItem(EnumHand.MAIN_HAND);
		if (!blade_.isEmpty() && !(blade_.getItem() instanceof ItemSlashBlade))
			blade_ = ItemStack.EMPTY;

		alreadyHitEntity_.add(thrower);
		alreadyHitEntity_.add(thrower.getRidingEntity());
		alreadyHitEntity_.addAll(thrower.getPassengers());
	}

	@Override
	protected void entityInit() {
		EntityDataManager manager = getDataManager();
		manager.register(ROLL, 0.0f);
		manager.register(LIFETIME, 20);
		manager.register(COLOR, 0x3333ff);
	}

	public void setInitialPosition(double x, double y, double z, float yaw, float pitch, float roll, float speed) {
		this.prevPosX = this.lastTickPosX = x;
		this.prevPosY = this.lastTickPosY = y;
		this.prevPosZ = this.lastTickPosZ = z;

		this.prevRotationYaw = this.rotationYaw = MathHelper.wrapDegrees(-yaw);
		this.prevRotationPitch = this.rotationPitch = MathHelper.wrapDegrees(-pitch);
		setRoll(roll);

		setMotionToForward(speed);

		setPosition(x, y, z);
	}

	protected void setMotionToForward(float speed) {
		this.motionX = MathUtil.getInstance().sin(rotationYaw) * MathUtil.getInstance().cos(rotationPitch) * speed;
		this.motionY = MathUtil.getInstance().sin(rotationPitch) * speed;
		this.motionZ = MathUtil.getInstance().cos(rotationYaw) * MathUtil.getInstance().cos(rotationPitch) * speed;
	}

	protected boolean onImpact(Entity target, float damage) {
		return onImpact(target, damage, "directMagic");
	}

	protected boolean onImpact(Entity target, float damage, String source) {
		DamageSource ds = new EntityDamageSource(source, thrower_).setDamageBypassesArmor().setMagicDamage();

		return onImpact(target, damage, ds);
	}

	protected boolean onImpact(Entity target, float damage, DamageSource ds) {
		target.hurtResistantTime = 0;
		target.attackEntityFrom(ds, damage);

		if (blade_.isEmpty() || !(target instanceof EntityLivingBase))
			return false;

		blade_.getItem().hitEntity(blade_, (EntityLivingBase) target, thrower_);
		return true;
	}

	protected boolean intercept(AxisAlignedBB area, boolean fragile) {

		List<Entity> list = world.getEntitiesInAABBexcluding(thrower_, area, EntitySelectorDestructable.getInstance());

		list.removeAll(alreadyHitEntity_);
		alreadyHitEntity_.addAll(list);

		if (blade_.isEmpty())
			return false;

		for (Entity target : list) {
			if (!isDestruction(target))
				continue;

			target.motionX = 0.0;
			target.motionY = 0.0;
			target.motionZ = 0.0;
			target.setDead();

			spawnExplodeParticle(target);

			StylishRankManager.setNextAttackType(thrower_, StylishRankManager.AttackTypes.DestructObject);
			StylishRankManager.doAttack(thrower_);

			if (fragile) {
				setDead();
				return true;
			}
		}
		return false;
	}

	private boolean isDestruction(Entity target) {
		if (target instanceof EntityArrow)
			return !isSameThrower(((EntityArrow) target).shootingEntity);
		if (target instanceof IThrowableEntity)
			return !isSameThrower(((IThrowableEntity) target).getThrower());
		if (target instanceof EntityThrowable)
			return !isSameThrower(((EntityThrowable) target).getThrower());

		if (target instanceof EntityFireball) {
			if (isSameThrower(((EntityFireball) target).shootingEntity)) {
				return false;
			} else {
				return !target.attackEntityFrom(DamageSource.causeMobDamage(thrower_), attackLevel_);
			}
		}

		return true;
	}

	private boolean isSameThrower(Entity targetThrower) {
		return targetThrower != null && targetThrower.getEntityId() == thrower_.getEntityId();
	}

	private void spawnExplodeParticle(Entity entity) {
		Random rand = this.rand;

		for (int var1 = 0; var1 < 10; var1++) {

			double xSpeed = rand.nextGaussian() * 0.02;
			double ySpeed = rand.nextGaussian() * 0.02;
			double zSpeed = rand.nextGaussian() * 0.02;

			double rx = rand.nextDouble();
			double ry = rand.nextDouble();
			double rz = rand.nextDouble();

			world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL,
					entity.posX + (rx * 2 - 1) * entity.width - xSpeed * 10.0,
					entity.posY + (ry) * entity.height - ySpeed * 10.0,
					entity.posZ + (rz * 2 - 1) * entity.width - zSpeed * 10.0, xSpeed, ySpeed, zSpeed);
		}
	}

	protected static void coolDownEnderman(EntityEnderman entity) {
		entity.setAttackTarget(null);

		for (EntityAITasks.EntityAITaskEntry task : entity.targetTasks.taskEntries) {
			if (task.priority == 1) {
				task.action.resetTask();
			}
		}
	}

	public final float getRoll() {
		return this.getDataManager().get(ROLL);
	}

	public final void setRoll(float roll) {
		this.getDataManager().set(ROLL, roll);
	}

	public final int getLifeTime() {
		return this.getDataManager().get(LIFETIME);
	}

	public final void setLifeTime(int lifetime) {
		this.getDataManager().set(LIFETIME, lifetime);
	}

	public final int getColor() {
		return getDataManager().get(COLOR);
	}

	public final void setColor(int value) {
		getDataManager().set(COLOR, value);
	}

	public Random getRand() {
		return this.rand;
	}

	@Override
	public boolean isOffsetPositionInLiquid(double x, double y, double z) {
		return false;
	}

	@Override
	public void move(MoverType type, double x, double y, double z) {
	}

	@Override
	protected void dealFireDamage(int amount) {
	}

	@Override
	public boolean handleWaterMovement() {
		return false;
	}

	@Override
	public boolean isInsideOfMaterial(Material materialIn) {
		return false;
	}

	@Override
	public boolean isInLava() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getBrightnessForRender() {

		float f1 = 0.5f;

		int i = super.getBrightnessForRender();
		int j = i & 255;
		int k = i >> 16 & 255;
		j += (int) (f1 * 15.0f * 16.0f);

		if (j > 240)
			j = 240;

		return j | k << 16;
	}

	@Override
	public float getBrightness() {
		float f1 = super.getBrightness();

		float f2 = 0.9f;
		f2 = f2 * f2 * f2 * f2;
		return f1 * (1.0f - f2) + f2;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}

	@Override
	public void setPortal(BlockPos pos) {
	}

	@Override
	public boolean isBurning() {
		return false;
	}

	@Override
	public void setInWeb() {

	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return pass == 1;
	}

	@Override
	public Entity getThrower() {
		return this.thrower_;
	}

	@Override
	public void setThrower(Entity entity) {
		this.thrower_ = (EntityLivingBase) entity;

	}

}
