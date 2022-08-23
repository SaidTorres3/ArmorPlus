package com.sofodev.armorplus.registry.entities.arrows;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static com.sofodev.armorplus.utils.Utils.setRL;
import static net.minecraft.item.Items.ARROW;

public abstract class APArrowEntity extends AbstractArrowEntity {

    private EntityType<? extends APArrowEntity> type;
    private ArrowProperty prop;

    public APArrowEntity(EntityType<? extends APArrowEntity> type, World world) {
        super(type, world);
        this.type = type;
    }

    public APArrowEntity(EntityType<? extends APArrowEntity> type, World world, ArrowProperty property) {
        super(type, world);
        this.type = type;
        this.setProp(property);
    }

    public APArrowEntity(EntityType<? extends APArrowEntity> type, double x, double y, double z, World world, ArrowProperty property) {
        super(type, x, y, z, world);
        this.type = type;
        this.setProp(property);
        this.setPos(x, y, z);
    }

    public APArrowEntity(EntityType<? extends APArrowEntity> type, LivingEntity shooter, World world, ArrowProperty property) {
        super(type, shooter, world);
        this.type = type;
        this.setProp(property);
        this.setOwner(shooter);
        if (shooter instanceof PlayerEntity) {
            this.pickup = AbstractArrowEntity.PickupStatus.ALLOWED;
        }
    }

    public APArrowEntity(EntityType<? extends APArrowEntity> type, FMLPlayMessages.SpawnEntity packet, World world, ArrowProperty property) {
        this(type, packet.getPosX(), packet.getPosY(), packet.getPosZ(), world, property);
        this.type = type;
        this.setRot(packet.getHeadYaw(), packet.getPitch());
        this.setUUID(packet.getUuid());
        this.setId(packet.getEntityId());
        this.setDeltaMovement(packet.getVelX(), packet.getVelY(), packet.getVelZ());
    }

    public APArrowEntity setProp(ArrowProperty prop) {
        this.prop = prop;
        return this;
    }

    public ArrowProperty getProp() {
        return prop;
    }

    @Override
    public void setBaseDamage(double damageIn) {
        super.setBaseDamage(this.prop.getDmg());
    }

    @Override
    public double getBaseDamage() {
        return super.getBaseDamage();
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getProp());
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected ItemStack getPickupItem() {
        ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(setRL(prop.getName() + "_arrow")));
        return stack.isEmpty() ? new ItemStack(ARROW) : stack;
    }

    @Override
    public EntityType<?> getType() {
        return this.type;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
        super.doPostHurtEffects(target);
        if (!level.isClientSide && target != getEntity()) {
            try {
                this.prop.hit(target);
            } catch (NullPointerException e) {
                
            }
        }
    }

}