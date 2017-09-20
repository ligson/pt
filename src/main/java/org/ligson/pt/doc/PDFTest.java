package org.ligson.pt.doc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDFTest {
	public static void main(String[] args) throws Exception {
		PDDocument document = PDDocument.load(new File("F:/1.pdf"));
		PDPage page = document.getPage(0);
		PDResources resources = page.getResources();
		Iterable xobjects = resources.getXObjectNames();
		if (xobjects != null) {
			Iterator imageIter = xobjects.iterator();
			while (imageIter.hasNext()) {
				COSName key = (COSName) imageIter.next();
				if (resources.isImageXObject(key)) {
					try {
						PDImageXObject image = (PDImageXObject) resources.getXObject(key);
						BufferedImage bufferedImage = image.getImage();
						ImageIO.write(bufferedImage, "png", new File("2.png"));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}

		}
	}
}
