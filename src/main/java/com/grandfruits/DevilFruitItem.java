package com.grandfruits;

import java.util.List;
import java.util.Locale;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;

public class DevilFruitItem extends Item {
    public enum Power {
        GOMU(45), MERA(70), PIKA(80), GURA(100), HAKI(100);

        private final int cooldownTicks;

        Power(int cooldownTicks) {
            this.cooldownTicks = cooldownTicks;
        }
    }

    private final Power power;

    public DevilFruitItem(Power power, Properties properties) {
        super(properties);
        this.power = power;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            activate(serverLevel, player);
            player.getCooldowns().addCooldown(this, power.cooldownTicks);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.grandfruits." + power.name().toLowerCase(Locale.ROOT))
                .withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("tooltip.grandfruits.cooldown")
                .withStyle(ChatFormatting.DARK_GRAY));
    }

    private void activate(ServerLevel level, Player player) {
        switch (power) {
            case GOMU -> rubberPunch(level, player);
            case MERA -> flameBurst(level, player);
            case PIKA -> lightDash(level, player);
            case GURA -> quakeWave(level, player);
            case HAKI -> armamentHaki(level, player);
        }
    }

    private void rubberPunch(ServerLevel level, Player player) {
        Vec3 look = player.getLookAngle();
        AABB range = player.getBoundingBox().expandTowards(look.scale(5.2D)).inflate(1.0D);
        for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, range,
                entity -> entity != player && entity.isAlive() && !entity.isAlliedTo(player))) {
            Vec3 toTarget = target.position().subtract(player.position()).normalize();
            if (look.dot(toTarget) > 0.25D && player.hasLineOfSight(target)) {
                target.hurt(level.damageSources().playerAttack(player), 6.0F);
                target.push(look.x * 0.9D, 0.22D + Math.max(0.0D, look.y) * 0.2D, look.z * 0.9D);
            }
        }
        level.sendParticles(ParticleTypes.CRIT, player.getX() + look.x * 2.2D,
                player.getEyeY() - 0.25D + look.y, player.getZ() + look.z * 2.2D,
                18, 0.5D, 0.45D, 0.5D, 0.08D);
        player.displayClientMessage(Component.translatable("message.grandfruits.used.gomu"), true);
    }

    private void flameBurst(ServerLevel level, Player player) {
        AABB range = player.getBoundingBox().inflate(3.4D);
        for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, range,
                entity -> entity != player && entity.isAlive() && !entity.isAlliedTo(player))) {
            target.hurt(level.damageSources().playerAttack(player), 5.0F);
            target.setSecondsOnFire(3);
            Vec3 away = target.position().subtract(player.position()).normalize();
            target.push(away.x * 0.65D, 0.18D, away.z * 0.65D);
        }
        level.sendParticles(ParticleTypes.FLAME, player.getX(), player.getY() + 1.0D,
                player.getZ(), 55, 1.2D, 0.75D, 1.2D, 0.09D);
        level.sendParticles(ParticleTypes.SMOKE, player.getX(), player.getY() + 0.8D,
                player.getZ(), 18, 0.8D, 0.45D, 0.8D, 0.03D);
        player.displayClientMessage(Component.translatable("message.grandfruits.used.mera"), true);
    }

    private void lightDash(ServerLevel level, Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 7, 2));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20 * 7, 1));
        AABB range = player.getBoundingBox().inflate(2.7D);
        for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, range,
                entity -> entity != player && entity.isAlive() && !entity.isAlliedTo(player))) {
            target.hurt(level.damageSources().magic(), 3.0F);
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 1));
        }
        level.sendParticles(ParticleTypes.END_ROD, player.getX(), player.getY() + 1.0D,
                player.getZ(), 45, 1.0D, 0.8D, 1.0D, 0.12D);
        player.displayClientMessage(Component.translatable("message.grandfruits.used.pika"), true);
    }

    private void quakeWave(ServerLevel level, Player player) {
        AABB range = player.getBoundingBox().inflate(5.0D);
        for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, range,
                entity -> entity != player && entity.isAlive() && !entity.isAlliedTo(player))) {
            target.hurt(level.damageSources().playerAttack(player), 7.0F);
            Vec3 away = target.position().subtract(player.position()).normalize();
            target.push(away.x * 1.15D, 0.38D, away.z * 1.15D);
        }
        level.sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY() + 0.15D,
                player.getZ(), 70, 2.4D, 0.18D, 2.4D, 0.10D);
        level.sendParticles(ParticleTypes.CRIT, player.getX(), player.getY() + 0.8D,
                player.getZ(), 30, 1.6D, 0.6D, 1.6D, 0.08D);
        player.displayClientMessage(Component.translatable("message.grandfruits.used.gura"), true);
    }

    private void armamentHaki(ServerLevel level, Player player) {
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 12, 1));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 12, 0));
        level.sendParticles(ParticleTypes.ENCHANT, player.getX(), player.getY() + 1.0D,
                player.getZ(), 45, 0.7D, 1.0D, 0.7D, 0.8D);
        player.displayClientMessage(Component.translatable("message.grandfruits.used.haki"), true);
    }
}
