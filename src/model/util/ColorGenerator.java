package model.util;

public class ColorGenerator {

	public static java.awt.Color[] createGradient(int numColors) {
		java.awt.Color[] colorPalette = new java.awt.Color[numColors];

		for (int i = 0; i < numColors; i++)
			colorPalette[i] = java.awt.Color.getHSBColor((float) i / numColors, 1, 1);

		return colorPalette;
	}
}
