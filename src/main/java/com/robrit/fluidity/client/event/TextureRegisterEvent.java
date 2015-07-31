/*
 *  TextureRegisterEvent.java
 *
 *  Copyright (c) 2015 TheRoBrit
 *
 *  Fluidity is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Fluidity is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.robrit.fluidity.client.event;

import com.robrit.fluidity.common.ref.ModInformation;
import com.robrit.fluidity.common.util.LogHelper;
import com.robrit.fluidity.common.util.TextureHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

@SideOnly(Side.CLIENT)
public class TextureRegisterEvent {

  private static final byte TEXTURE_MAP_ID_BLOCK = 0;
  private static final String SEPARATOR = "/";
  private static final String TEMPLATE_BUCKET_LOCATION = "items/TemplateBucket.png";

  private void registerTexture(final Fluid fluid) {

  }

  @SubscribeEvent
  public void onTextureStitch(TextureStitchEvent.Post event) {
    if (event.map.getGlTextureId() == TEXTURE_MAP_ID_BLOCK) {
      final Minecraft mc = Minecraft.getMinecraft();
      final Color maskColor = new Color(0, 255, 0); // Chroma-key green
      final ResourceLocation templateBucket =
          new ResourceLocation(ModInformation.MOD_ID.toLowerCase(),
                               TEMPLATE_BUCKET_LOCATION);

      for (final Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
        try {

          final BufferedImage fluidImage =
              ImageIO.read(mc.getResourceManager().getResource(fluid.getStill()).getInputStream());
          final File outputFile = new File(mc.mcDataDir + SEPARATOR + "assets" + SEPARATOR,
                                           ModInformation.MOD_ID.toLowerCase() + SEPARATOR +
                                           fluid.getName() + ".png");

          TextureHelper.addTextureLayer(maskColor, templateBucket, fluidImage, outputFile);

        } catch (final Exception e) {
          LogHelper.error("Encountered an issue when attempting to manipulate texture");
          e.printStackTrace();
        }
      }
    }
  }
}
