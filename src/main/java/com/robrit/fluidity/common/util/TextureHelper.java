/*
 *  TextureHelper.java
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

package com.robrit.fluidity.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class TextureHelper {

  private static final String FILE_TYPE = "png";
  private static final int COLOR_COMPONENT_MAX_VALUE = 255;

  public static void addTextureLayer(Color maskColor, ResourceLocation resourceLocation,
                                     BufferedImage textureLayer, File outputFile) {
    try {
      final IResourceManager resourceManager = Minecraft.getMinecraft().getResourceManager();
      final IResource resource = resourceManager.getResource(resourceLocation);
      final BufferedImage textureImage = ImageIO.read(resource.getInputStream());

      int imageWidth = textureImage.getWidth();
      int imageHeight = textureImage.getHeight();

      Graphics2D graphics2d = textureImage.createGraphics();
      graphics2d.drawImage(textureImage, imageWidth, imageHeight, null);
      graphics2d.drawImage(textureLayer, imageWidth, imageHeight, null);

      if (!outputFile.getParentFile().exists() && !outputFile.getParentFile().mkdirs()) {
        return;
      }

      ImageIO.write(textureImage, FILE_TYPE, outputFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Color getMeanColor(final int[] dataArray) {
    short[] allRedStored = new short[dataArray.length];
    short[] allGreenStored = new short[dataArray.length];
    short[] allBlueStored = new short[dataArray.length];

    for (int pixelIndex = 0; pixelIndex < dataArray.length; pixelIndex++) {
      allRedStored[pixelIndex] = (short) (dataArray[pixelIndex] >> 16 & 255);
      allGreenStored[pixelIndex] = (short) (dataArray[pixelIndex] >> 8 & 255);
      allBlueStored[pixelIndex] = (short) (dataArray[pixelIndex] & 255);
    }

    int aggregateRed = 0;
    int aggregateGreen = 0;
    int aggregateBlue = 0;

    for (int colourIndex = 0; colourIndex < dataArray.length; colourIndex++) {
      aggregateRed += allRedStored[colourIndex];
      aggregateGreen += allGreenStored[colourIndex];
      aggregateBlue += allBlueStored[colourIndex];
    }

    short meanRed = COLOR_COMPONENT_MAX_VALUE;
    short meanGreen = COLOR_COMPONENT_MAX_VALUE;
    short meanBlue = COLOR_COMPONENT_MAX_VALUE;

    meanRed = (short) (aggregateRed / (allRedStored.length > 0 ? allRedStored.length : 1));
    meanGreen = (short) (aggregateGreen / (allGreenStored.length > 0 ? allGreenStored.length : 1));
    meanBlue = (short) (aggregateBlue / (allBlueStored.length > 0 ? allBlueStored.length : 1));

    return new Color(meanRed, meanGreen, meanBlue, 128); // TODO: RE-THINK!
  }
}
