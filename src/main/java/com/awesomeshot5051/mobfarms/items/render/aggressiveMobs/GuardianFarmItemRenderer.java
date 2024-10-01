package com.awesomeshot5051.mobfarms.items.render.aggressiveMobs;

import com.awesomeshot5051.mobfarms.blocks.ModBlocks;
import com.awesomeshot5051.mobfarms.blocks.tileentity.aggressiveMobs.guardianFarmTileentity;
import com.awesomeshot5051.mobfarms.blocks.tileentity.render.aggressiveMobs.GuardianFarmRenderer;
import com.awesomeshot5051.mobfarms.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class GuardianFarmItemRenderer extends BlockItemRendererBase<GuardianFarmRenderer, guardianFarmTileentity> {

    public GuardianFarmItemRenderer() {
        super(GuardianFarmRenderer::new, () -> new guardianFarmTileentity(BlockPos.ZERO, ModBlocks.guardian_FARM.get().defaultBlockState()));
    }

}
