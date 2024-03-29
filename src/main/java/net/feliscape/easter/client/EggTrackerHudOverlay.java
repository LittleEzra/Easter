package net.feliscape.easter.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.feliscape.easter.Easter;
import net.feliscape.easter.util.ColorUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class EggTrackerHudOverlay {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Easter.MOD_ID, "textures/gui/icons.png");

    public static final IGuiOverlay HUD_SIPHON = ((gui, guiGraphics, partialTick, width, height) -> {
        int easterEggs = ClientEggData.getEasterEggs();
        int goldenEggs = ClientEggData.getGoldenEggs();
        RenderSystem.enableBlend();

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        RenderSystem.defaultBlendFunc();

        guiGraphics.blit(TEXTURE, 12, 12, 0, 0, 8, 8);
        guiGraphics.drawString(gui.getFont(), Integer.toString(easterEggs), 16, 4, ColorUtil.getIntColor("#ffffff"));
        guiGraphics.blit(TEXTURE, 12, 22, 8, 0, 8, 8);
        guiGraphics.drawString(gui.getFont(), Integer.toString(goldenEggs), 16, 4, ColorUtil.getIntColor("#ffffff"));
    });
}
