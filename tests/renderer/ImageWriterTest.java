package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void writeToImage() {
        int nX = 800;
        int nY = 500;
        int gapX=nX/16;
        int gapY=nY/10;
        ImageWriter imageWriter = new ImageWriter("Our scene", nX, nY);
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % gapY == 0 || j % gapX == 0) {
                    //create the grid
                    imageWriter.writePixel(j, i, new Color(255, 255, 0));
                }
                else {
                    //coloring the pixels
                    imageWriter.writePixel(j, i, new Color(200, 0, 200));
                }
            }
        }
        imageWriter.writeToImage();
    }
}