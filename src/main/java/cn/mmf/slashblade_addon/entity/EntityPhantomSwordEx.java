package cn.mmf.slashblade_addon.entity;

import java.util.List;

import cn.mcmod_mmf.mmlib.util.MathUtil;
import cn.mmf.slashblade_addon.specialattack.PhantomSwordsBase;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityPhantomSwordEx extends EntityBase{

    private Entity stuckEntity_ = null;	

    private double hitX_;
    private double hitY_;
    private double hitZ_;
    private float hitYaw_;
    private float hitPitch_;
	private float hitBaseYaw_;

	private PhantomSwordsBase sa_ = null;

	public static final float SPEED = 1.75f;

	private static final double AMBIT = 0.75;

    private static final DataParameter<Integer> TARGET_ENTITY_ID = EntityDataManager.<Integer>createKey(EntityPhantomSwordEx.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> INTERVAL = EntityDataManager.<Integer>createKey(EntityPhantomSwordEx.class, DataSerializers.VARINT);

	public EntityPhantomSwordEx(World worldIn)
	{
		super(worldIn);
	}

	public EntityPhantomSwordEx(World worldIn,
								EntityLivingBase thrower,
								float attackLevel,
								PhantomSwordsBase sa)
	{
		super(worldIn, thrower, attackLevel);
		this.sa_ = sa;
        setSize(0.5f, 0.5f);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();

		EntityDataManager manager = getDataManager();
        manager.register(TARGET_ENTITY_ID, 0);
		manager.register(INTERVAL, 7);
	}

    public final int getTargetEntityId()
	{
        return this.getDataManager().get(TARGET_ENTITY_ID);
    }

    public final void setTargetEntityId(int entityid)
	{
        this.getDataManager().set(TARGET_ENTITY_ID,entityid);
    }

	public final int getInterval()
	{
		return getDataManager().get(INTERVAL);
	}

	public final void setInterval(int value)
	{
		getDataManager().set(INTERVAL, value);
	}

	protected final boolean isStuck()
	{
		return stuckEntity_ != null;
	}

	protected final Entity getTargetEntity()
	{
		int targetid = getTargetEntityId();
		if (targetid == 0)
			return null;
				
		return this.world.getEntityByID(targetid);
	}

    @Override
	public void onUpdate()
	{
		if (isStuck()) {
			updateRidden();
			return;
		}

		AxisAlignedBB bb = new AxisAlignedBB(this.posX - AMBIT,
											 this.posY - AMBIT,
											 this.posZ - AMBIT,
											 this.posX + AMBIT,
											 this.posY + AMBIT,
											 this.posZ + AMBIT);
				
		if (intercept(bb, true))
			return;


		Entity target = getNearestHitEntity(bb);
		if (target != null) {
			attackToEntity(target);

		} else if (!world.getCollisionBoxes(this, getEntityBoundingBox()).isEmpty()) {
			setDead();
			return;
		}

		move();

		if (this.ticksExisted >= getLifeTime())
			setDead();
	}

    @Override
	public void updateRidden()
	{

		final Entity entity = stuckEntity_;
		if (entity == null)
			return;
    
		if (entity.isDead) {
			setDead();
			return;
		}

		if (sa_ != null)
			sa_.onSticking(entity);

		updatePositionBaseStuckEntity();		

		if (this.ticksExisted >= getLifeTime()) {
			StylishRankManager.setNextAttackType(thrower_, StylishRankManager.AttackTypes.BreakPhantomSword);
			onImpact(entity, attackLevel_*0.5f);
			setDead();
		}
	}

	private void updatePositionBaseStuckEntity()
	{
		final Entity entity = stuckEntity_;

		float r = entity.rotationYaw - hitBaseYaw_;
		double x = entity.posX + hitX_*MathUtil.getInstance().cos(r) - hitZ_*MathUtil.getInstance().sin(r);
		double y = entity.posY + hitY_;
		double z = entity.posZ + hitX_*MathUtil.getInstance().sin(r) + hitZ_*MathUtil.getInstance().cos(r);

		float pitch = entity.rotationPitch + hitPitch_;
		float yaw = entity.rotationYaw + hitYaw_;
		
		setLocationAndAngles(x, y, z,
							 MathHelper.wrapDegrees(yaw),
							 MathHelper.wrapDegrees(pitch));
	}

	private void move()
	{
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
      
		if (getInterval() < this.ticksExisted) {
			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
		} else {
			doTargeting();
		}
		setPosition(this.posX, this.posY, this.posZ);
	}

	private void doTargeting()
	{
		Entity target = getTargetEntity();
		if (target == null)
			return;

        final double dx = target.posX - posX;
        final double dz = target.posZ - posZ;
        double dy = - posY;

        if (target instanceof EntityLivingBase) {
            dy += target.posY + target.getEyeHeight()/2;
        } else {
            AxisAlignedBB bb = target.getEntityBoundingBox();
            dy += (bb.minY + bb.maxY) / 2.0;
        }

        double d = Math.sqrt(dx*dx + dz*dz);
        float yaw   = (float)Math.toDegrees(Math.atan2(dx, dz));
		float pitch = (float)Math.toDegrees(Math.atan2(dy, d));

		prevRotationYaw   = rotationYaw;
		prevRotationPitch = rotationPitch;

		rotationYaw   += MathHelper.clamp(yaw   - rotationYaw, -4.5f, 4.5f);
		rotationPitch += MathHelper.clamp(pitch - rotationPitch, -4.5f, 4.5f);

		setMotionToForward(SPEED);
	}

	protected void attackToEntity(Entity target)
	{
		StylishRankManager.setNextAttackType(thrower_, StylishRankManager.AttackTypes.PhantomSword);
		onImpact(target, attackLevel_);

		stickEntity(target);
	}

	private Entity getNearestHitEntity(AxisAlignedBB bb)
	{
		List<Entity> list = world.getEntitiesInAABBexcluding(thrower_, bb, EntitySelectorAttackable.getInstance());

		list.removeAll(alreadyHitEntity_);

		Entity target = getTargetEntity();
		if (target != null &&
			target.isEntityAlive() &&
			target.getEntityBoundingBox().intersects(bb)) {

			list.add(target);
		}

		alreadyHitEntity_.addAll(list);
      
		double d0 = 10.0;
		Entity hitEntity = null;
		for (Entity entity : list) {
			if (!entity.canBeCollidedWith())
				continue;

			double d1 = entity.getDistance(this);
			if (d1 < d0 /* || d0 == 0.0*/) {
				hitEntity = entity;
				d0 = d1;
			}
		}
		return hitEntity;
	}

	@Override
	protected boolean onImpact(Entity target, float damage)
	{
		if (super.onImpact(target, Math.max(1.0f, damage))) {
			if (sa_ != null) {
				sa_.onImpact(target);
			}
			return true;
		}
		return false;
	}

    protected void stickEntity(Entity target)
	{
        if (target == null)
			return;

		hitYaw_		= this.rotationYaw - target.rotationYaw;
		hitPitch_	= this.rotationPitch - target.rotationPitch;
		hitX_		= this.posX - target.posX;
		hitY_		= this.posY - target.posY;
		hitZ_		= this.posZ - target.posZ;
		hitBaseYaw_	= target.rotationYaw;

		stuckEntity_ = target;

		this.ticksExisted = Math.max(0, getLifeTime() - 20);
	}
}

