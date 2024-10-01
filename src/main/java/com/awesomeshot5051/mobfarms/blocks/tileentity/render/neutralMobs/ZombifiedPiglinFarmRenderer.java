package com.awesomeshot5051.mobfarms.blocks.tileentity.render.neutralMobs;

import com.awesomeshot5051.mobfarms.blocks.tileentity.render.RendererBase;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.awesomeshot5051.mobfarms.blocks.tileentity.neutralMobs.ZombifiedPiglinFarmTileentity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.Zombie;

import java.lang.ref.WeakReference;

public class ZombifiedPiglinFarmRenderer extends RendererBase<ZombifiedPiglinFarmTileentity> {

    private WeakReference<Zombie> zombieCache = new WeakReference<>(null);
    private WeakReference<ZombieRenderer> zombieRendererCache = new WeakReference<>(null);
    private WeakReference<ZombifiedPiglin> zombifiedPiglinCache = new WeakReference<>(null);
    private WeakReference<PiglinRenderer> piglinRendererCache = new WeakReference<>(null);

    public ZombifiedPiglinFarmRenderer(BlockEntityRendererProvider.Context renderer) {
        super(renderer);
    }

    @Override
    public void render(ZombifiedPiglinFarmTileentity farm, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        super.render(farm, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
        matrixStack.pushPose();

        // Retrieve or create Zombie entity and renderer
        Zombie zombie = zombieCache.get();
        if (zombie == null) {
            zombie = new Zombie(minecraft.level);
            zombieCache = new WeakReference<>(zombie);
        }

        ZombieRenderer zombieRenderer = zombieRendererCache.get();
        if (zombieRenderer == null) {
            zombieRenderer = new ZombieRenderer(createEntityRenderer());
            zombieRendererCache = new WeakReference<>(zombieRenderer);
        }

        // Retrieve or create ZombifiedPiglin entity and renderer
        ZombifiedPiglin zombifiedPiglin = zombifiedPiglinCache.get();
        if (zombifiedPiglin == null) {
            zombifiedPiglin = new ZombifiedPiglin(EntityType.ZOMBIFIED_PIGLIN, minecraft.level);
            zombifiedPiglinCache = new WeakReference<>(zombifiedPiglin);
        }

        PiglinRenderer piglinRenderer = piglinRendererCache.get();
        if (piglinRenderer == null) {
            piglinRenderer = new PiglinRenderer(
                    createEntityRenderer(),
                    ModelLayers.ZOMBIFIED_PIGLIN,
                    ModelLayers.ZOMBIFIED_PIGLIN_INNER_ARMOR,
                    ModelLayers.ZOMBIFIED_PIGLIN_OUTER_ARMOR,
                    true // noRightEar set to true for zombified piglin
            );
            piglinRendererCache = new WeakReference<>(piglinRenderer);
        }

        Direction direction = Direction.SOUTH;

        if (farm.getVillagerEntity() != null) {
            matrixStack.pushPose();
            matrixStack.translate(0.5D, 1D / 16D, 0.5D);
            matrixStack.mulPose(Axis.YP.rotationDegrees(-direction.toYRot()));
            matrixStack.translate(-5D / 16D, 0D, -5D / 16D);
            matrixStack.mulPose(Axis.YP.rotationDegrees(90));
            matrixStack.scale(0.3F, 0.3F, 0.3F);
            getVillagerRenderer().render(farm.getVillagerEntity(), 0F, 1F, matrixStack, buffer, combinedLight);
            matrixStack.popPose();
        }

        matrixStack.pushPose();
        matrixStack.translate(0.5D, 1D / 16D, 0.5D);
        matrixStack.mulPose(Axis.YP.rotationDegrees(-direction.toYRot()));
        matrixStack.translate(5D / 16D, 0D, -5D / 16D);
        matrixStack.mulPose(Axis.YP.rotationDegrees(-90));
        matrixStack.scale(0.3F, 0.3F, 0.3F);
        zombieRenderer.render(zombie, 0F, 1F, matrixStack, buffer, combinedLight);
        matrixStack.popPose();

        if (farm.getTimer() >= ZombifiedPiglinFarmTileentity.getZombifiedPiglinSpawnTime() && farm.getTimer() < ZombifiedPiglinFarmTileentity.getZombifiedPiglinKillTime()) {
            matrixStack.pushPose();
            matrixStack.translate(0.5D, 1D / 16D, 0.5D);
            matrixStack.mulPose(Axis.YP.rotationDegrees(-direction.toYRot()));
            matrixStack.translate(0D, 0D, 3D / 16D);
            matrixStack.scale(0.3F, 0.3F, 0.3F);
            if (farm.getTimer() % 20 < 10) {
                zombifiedPiglin.hurtTime = 20;
            } else {
                zombifiedPiglin.hurtTime = 0;
            }
            piglinRenderer.render(zombifiedPiglin, 0F, 1F, matrixStack, buffer, combinedLight);
            matrixStack.popPose();
        }

        matrixStack.popPose();
    }
}