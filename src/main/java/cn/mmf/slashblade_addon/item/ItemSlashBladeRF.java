package cn.mmf.slashblade_addon.item;

import java.util.List;

import cn.mcmod_mmf.mmlib.item.EnergyContainerItemWrapper;
import cn.mcmod_mmf.mmlib.item.IEnergyContainerItem;
import cn.mcmod_mmf.mmlib.util.StringUtil;
import cn.mmf.slashblade_addon.ClientProxy;
import cn.mmf.slashblade_addon.IMultiModeBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemSlashBladeRF extends ItemSlashBladeNamed implements IMultiModeBlade, IEnergyContainerItem {
	public static final TagPropertyAccessor.TagPropertyString Username = new TagPropertyAccessor.TagPropertyString(
			"Username");
	public static final TagPropertyAccessor.TagPropertyInteger MAXENERGY = new TagPropertyAccessor.TagPropertyInteger(
			"maxEnergy");
	public static final TagPropertyAccessor.TagPropertyInteger MAXTRANSFER = new TagPropertyAccessor.TagPropertyInteger(
			"maxTransfer");
	public static final TagPropertyAccessor.TagPropertyInteger ENERGYPERUSE = new TagPropertyAccessor.TagPropertyInteger(
			"energyPerUse");
	public static final TagPropertyAccessor.TagPropertyInteger ENERGYPERUSECHARGED = new TagPropertyAccessor.TagPropertyInteger(
			"energyPerUseCharged");

	public ItemSlashBladeRF(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
		super(par2EnumToolMaterial, baseAttackModifiers);
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		NBTTagCompound tag = getItemTagCompound(container);
		if (container.getTagCompound() == null)
			getItemTagCompound(container).setInteger("Energy", 0);
		int stored = Math.min(container.getTagCompound().getInteger("Energy"), getMaxEnergyStored(container));
		int receive = Math.min(maxReceive,
				Math.min(getMaxEnergyStored(container) - stored, MAXTRANSFER.get(tag, 20000)));
		if (!simulate) {
			stored += receive;
			container.getTagCompound().setInteger("Energy", stored);
		}
		return receive;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		if (container.getTagCompound() == null)
			getItemTagCompound(container).setInteger("Energy", 0);

		int stored = Math.min(container.getTagCompound().getInteger("Energy"), getMaxEnergyStored(container));
		int extract = Math.min(maxExtract, stored);
		if (!simulate) {
			stored -= extract;
			container.getTagCompound().setInteger("Energy", stored);
			if (stored == 0)
				setMode(container, 0);
		}
		return extract;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		if (container.getTagCompound() == null)
			getItemTagCompound(container).setInteger("Energy", 0);
		return Math.min(container.getTagCompound().getInteger("Energy"), getMaxEnergyStored(container));
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		NBTTagCompound tag = getItemTagCompound(container);
		return MAXENERGY.get(tag, 2000000);
	}

	@Override
	public void onModeChange(EntityPlayer player, ItemStack stack) {
		NBTTagCompound tag = getItemTagCompound(stack);
		if (isEmpowered(stack)) {
			boolean cancel = false;
			if (IsBroken.get(tag).booleanValue()) {
				cancel = true;
			}
			if (Username.exists(tag)) {
				if (!player.getName().toString().trim().equals(Username.get(tag).trim())) {
					cancel = true;
				}
			} else {
				Username.set(tag, player.getName().toString());
			}
			if (cancel) {
				player.world.playSound(null, player.getPosition(), SoundEvents.BLOCK_NOTE_BASS, SoundCategory.PLAYERS,
						1, 1.0F);
				setMode(stack, 0);
				return;
			}
			player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_LIGHTNING_THUNDER,
					SoundCategory.PLAYERS, 1, 1.0F);
			onStartupEmpowered(player, stack);
		} else {
			player.world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
					SoundCategory.PLAYERS, 1, 1.0F);
		}
	}

	public boolean isEmpowered(ItemStack stack) {
		NBTTagCompound tag = getItemTagCompound(stack);
		return getMode(stack) == 1 && getEnergyStored(stack) >= ENERGYPERUSECHARGED.get(tag, 800);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (!this.isInCreativeTab(tab))
			return;
		if (this.isInCreativeTab(CreativeTabs.COMBAT))
			return;
		for (String bladename : NamedBlades) {
			ItemStack blade = SlashBlade.getCustomBlade(bladename);
			if (blade.getItemDamage() == OreDictionary.WILDCARD_VALUE)
				blade.setItemDamage(0);
			if (!blade.isEmpty())
				subItems.add(blade);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity, EntityLivingBase player) {
		NBTTagCompound tag = getItemTagCompound(stack);
		int rank = StylishRankManager.getStylishRank(player);
		int usage = (int) (ENERGYPERUSE.get(tag, 100) * Math.pow(2.0D, rank));
		if ((isEmpowered(stack)) && (extractEnergy(stack, usage, false) == usage)) {
			entity.hurtResistantTime = 0;
			entity.attackEntityFrom(new EntityDamageSource("forge_flux", player).setDamageBypassesArmor().setFireDamage(), rank);
		}
		return super.hitEntity(stack, entity, player);
	}

	@Override
	public void onUpdate(ItemStack sitem, World par2World, Entity par3Entity, int indexOfMainSlot, boolean isCurrent) {
		NBTTagCompound tag = getItemTagCompound(sitem);
		if ((isEmpowered(sitem)) && (par3Entity != null) && (par2World.getTotalWorldTime() % 10L == 0L)
				&& ((!isCurrent) || (OnClick.get(tag).booleanValue()) || (((par3Entity instanceof EntityPlayer))))) {
			if (!par2World.isRemote) {
				int runingCost = ENERGYPERUSE.get(tag, 100);
				extractEnergy(sitem, runingCost, false);
				int rankPoint = StylishRankManager.getTotalRankPoint(par3Entity);
				int aRankPoint = (int) (StylishRankManager.RankRange * 7D);
				int rankAmount = aRankPoint - rankPoint;
				if (0 < rankAmount) {
					StylishRankManager.addRankPoint(par3Entity, rankAmount);
				}
			}

			if ((Username.exists(tag)) && (!par3Entity.getName().toString().trim().equals(Username.get(tag).trim()))) {
				setMode(sitem, 0);
			}
			double d0 = itemRand.nextGaussian() * 0.02D;
			double d1 = itemRand.nextGaussian() * 0.02D;
			double d2 = itemRand.nextGaussian() * 0.02D;
			double d3 = 10.0D;
			par2World.spawnParticle(EnumParticleTypes.PORTAL,
					par3Entity.posX + itemRand.nextFloat() * par3Entity.width * 2.0F - par3Entity.width - d0 * d3,
					par3Entity.posY, par3Entity.posZ + itemRand

							.nextFloat() * par3Entity.width * 2.0F - par3Entity.width - d2 * d3,
					d0, d1, d2);
		}
		super.onUpdate(sitem, par2World, par3Entity, indexOfMainSlot, isCurrent);
	}

	protected int getEnergyPerUse(ItemStack stack) {
		NBTTagCompound tag = getItemTagCompound(stack);
		return isEmpowered(stack) ? ENERGYPERUSECHARGED.get(tag, 800) : ENERGYPERUSE.get(tag, 100);
	}

	public void onStartupEmpowered(EntityPlayer player, ItemStack stack) {
		int rankPoint = StylishRankManager.getTotalRankPoint(player);
		int aRankPoint = (int) (StylishRankManager.RankRange * 7D);
		int rankAmount = aRankPoint - rankPoint;

		int energy = getEnergyStored(stack);
		rankAmount = Math.min(energy, rankAmount);

		int startupCost = 1000;

		World world = player.world;

		int energyUsage = startupCost;
		if (energyUsage <= energy) {
			for (int i = 0; i < 6; i++) {
				EntityDrive entityDrive = new EntityDrive(world, player, 0F, false, 0);
				entityDrive.setLocationAndAngles(player.posX, player.posY + (double) player.getEyeHeight() / 2D,
						player.posZ, player.rotationYaw + 60 * i, 0);
				entityDrive.setDriveVector(0.5f);
				entityDrive.setLifeTime(5);
				entityDrive.setIsMultiHit(false);
				entityDrive.setRoll(90.0f);
				if (entityDrive != null) {
					world.spawnEntity(entityDrive);
				}
			}
		}
		int rank = StylishRankManager.getStylishRank(player);
		energyUsage = (int) (energyUsage * Math.pow(2.0D, rank));
		if (0 < rankAmount) {
			energyUsage += rankAmount * 2;
			StylishRankManager.addRankPoint(player, rankAmount);
		}
		extractEnergy(stack, energyUsage, false);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new EnergyContainerItemWrapper(stack, this);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World arg1, List tooltip, ITooltipFlag arg3) {
		EntityPlayer par2EntityPlayer = Minecraft.getMinecraft().player;
		boolean par4 = arg3.isAdvanced();
		if (par2EntityPlayer == null)
			return;
		addInformationOwner(stack, par2EntityPlayer, tooltip, par4);
		addInformationSwordClass(stack, par2EntityPlayer, tooltip, par4);
		addInformationKillCount(stack, par2EntityPlayer, tooltip, par4);
		addInformationProudSoul(stack, par2EntityPlayer, tooltip, par4);
		addInformationSpecialAttack(stack, par2EntityPlayer, tooltip, par4);
		addInformationRepairCount(stack, par2EntityPlayer, tooltip, par4);
		addInformationRangeAttack(stack, par2EntityPlayer, tooltip, par4);
		addInformationSpecialEffec(stack, par2EntityPlayer, tooltip, par4);
		addInformationMaxAttack(stack, par2EntityPlayer, tooltip, par4);
		NBTTagCompound tag = getItemTagCompound(stack);
		if (tag.hasKey(adjustXStr)) {
			float ax = tag.getFloat(adjustXStr);
			float ay = tag.getFloat(adjustYStr);
			float az = tag.getFloat(adjustZStr);
			tooltip.add(String.format("adjust x:%.1f y:%.1f z:%.1f", ax, ay, az));
		}

		if (StringUtil.displayShiftForDetail && !StringUtil.getInstance().isShiftKeyDown()) {
			tooltip.add(I18n.format("info.cofh.holdShiftForDetails"));
		}
		if (!StringUtil.getInstance().isShiftKeyDown()) {
			return;
		}
		if (stack.getTagCompound() == null) {
			getItemTagCompound(stack).setInteger("Energy", 0);
		}
		tooltip.add(StringUtil.getInstance().localize("info.cofh.charge") + ": " + StringUtil.getInstance().getScaledNumber(getEnergyStored(stack))
				+ " / " + StringUtil.getInstance().getScaledNumber(getMaxEnergyStored(stack)) + " RF");
		tooltip.add(StringUtil.ORANGE + getEnergyPerUse(stack) + " "
				+ StringUtil.getInstance().localize("info.flammpfeil.slashblade.tool.energyPerUse") + StringUtil.END);
		tooltip.add(StringUtil.RED + StringUtil.getInstance().localize("info.flammpfeil.slashblade.tool.user") + ": "
				+ Username.get(ItemSlashBlade.getItemTagCompound(stack)));
		addEmpoweredTip(this, stack, tooltip);
	}

	public void addEmpoweredTip(IMultiModeBlade item, ItemStack stack, List<String> tooltip) {
		if (!isEmpowered(stack)) {
			tooltip.add(StringUtil.getInstance().localizeFormat("info.flammpfeil.slashblade.tool.chargeOn",
					StringUtil.getInstance().getKeyName(ClientProxy.ChangeMode.getKeyCode())));
		} else {
			tooltip.add(StringUtil.getInstance().localizeFormat("info.flammpfeil.slashblade.tool.chargeOff",
					StringUtil.getInstance().getKeyName(ClientProxy.ChangeMode.getKeyCode())));
		}
	}

}
