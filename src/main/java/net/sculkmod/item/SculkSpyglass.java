package net.sculkmod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpyglassItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class SculkSpyglass extends SpyglassItem {
    private int vibrationCount = 0;

    public SculkSpyglass(Settings settings) { super(settings); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        this.vibrationCount = 0;
        return super.use(world, user, hand);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            Box box = player.getBoundingBox().expand(15.0);
            List<Entity> targets = world.getOtherEntities(player, box);
            for (Entity entity : targets) {
                if (entity instanceof LivingEntity target && !target.isSneaking()) {
                    // Проверка движения (звук ходьбы/действий)
                    if (target.getX() != target.prevX  target.getY() != target.prevY  target.isHandActive()) {
                        target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 60, 0, false, false));
                        vibrationCount++;
                    }
                }
            }
            if (vibrationCount > 20) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40, 10)); // Белый экран
                player.getItemCooldownManager().set(this, 100); // Кулдаун 5 сек
                player.stopUsingItem();
            }
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) player.getItemCooldownManager().set(this, 100);
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }
}