package fr.hugman.promenade.client.color;

public final class ColorUtil {
    /**
     * Gets a color that varies between two colors depending on a cursor position.
     *
     * @param start The start color
     * @param end The end color
     * @param delta The cursor position (from 0 to 1)
     */
    public static int lerp(float delta, int start, int end) {
        if(delta <= 0.0F) {
            return start;
        }
        if(delta >= 1.0F) {
            return end;
        }

        int r1 = (start >> 16) & 0xFF;
        int g1 = (start >> 8) & 0xFF;
        int b1 = start & 0xFF;

        int r2 = (end >> 16) & 0xFF;
        int g2 = (end >> 8) & 0xFF;
        int b2 = end & 0xFF;

        int r = (int) (r1 + (r2 - r1) * delta);
        int g = (int) (g1 + (g2 - g1) * delta);
        int b = (int) (b1 + (b2 - b1) * delta);

        return (r << 16) | (g << 8) | b;
    }

    /**
     * Gets a color that varies between colors depending on a cursor position.
     * @param delta The cursor position (from 0 to 1)
     * @param colors The colors
     */
    public static int lerp(float delta, int... colors) {
        if(delta <= 0.0F) {
            return colors[0];
        }
        if(delta >= 1.0F) {
            return colors[colors.length - 1];
        }

        if(colors.length == 0) {
            return 0;
        }
        if(colors.length == 1) {
            return colors[0];
        }
        if(colors.length == 2) {
            return lerp(delta, colors[0], colors[1]);
        }

        float step = 1F / (colors.length - 1);
        int index = (int) (delta / step);
        float subCursor = (delta - index * step) / step;
        return lerp(subCursor, colors[index], colors[index + 1]);
    }
}