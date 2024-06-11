package org.example;

import java.io.File;
import java.io.FileOutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class PDFGenerator {

    public static void main(String[] args) {
        try {
            // Setup FOP factory
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

            // Setup output file
            File pdfFile = new File("output.pdf");
            FileOutputStream out = new FileOutputStream(pdfFile);

            // Setup FOP with the desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            // Setup the XSLT transformer factory
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new File("template/template.fo")));

            // Setup the input for the transformation (XML file)
            StreamSource src = new StreamSource(new File("template/data.xml"));

            // Setup the transformation result
            SAXResult res = new SAXResult(fop.getDefaultHandler());

            // Start the transformation and rendering process
            transformer.transform(src, res);

            // Clean up
            out.close();

            System.out.println("PDF created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
