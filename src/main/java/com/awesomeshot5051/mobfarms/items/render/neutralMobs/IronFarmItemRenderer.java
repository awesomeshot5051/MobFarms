package com.awesomeshot5051.mobfarms.items.render.neutralMobs;

import com.awesomeshot5051.mobfarms.blocks.ModBlocks;
import com.awesomeshot5051.mobfarms.blocks.tileentity.neutralMobs.IronFarmTileentity;
import com.awesomeshot5051.mobfarms.blocks.tileentity.render.neutralMoobs.IronFarmRenderer;
import com.awesomeshot5051.mobfarms.items.render.BlockItemRendererBase;
import net.minecraft.core.BlockPos;

public class IronFarmItemRenderer extends BlockItemRendererBase<IronFarmRenderer, IronFarmTileentity> {

    public IronFarmItemRenderer() {
        super(IronFarmRenderer::new, () -> new IronFarmTileentity(BlockPos.ZERO, ModBlocks.IRON_FARM.get().defaultBlockState()));
    }

}
