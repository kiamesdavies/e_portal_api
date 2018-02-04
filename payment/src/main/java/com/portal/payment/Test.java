/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.payment;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.portal.commons.util.EnvironMentVariables;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author poseidon
 */
public class Test {

    private static final SimpleDateFormat MONTH_DATEFORMAT = new SimpleDateFormat("yyyy-MM");

    public static void main(String[] args) throws FileNotFoundException, Exception {
        String html = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
              
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n"
                + "  <head>\n"
                + "    <title>Flying Saucer: CSS List Support</title>\n"
                + "    <link rel=\"stylesheet\" type=\"text/css\" href=\"general.css\" title=\"Style\" media=\"screen\" />\n"
                + "    <style>\n"
                + "      html {\n"
                + "        background-color: transparent;\n"
                + "      }\n"
                + "\n"
                + "      body {\n"
                + "        background-color: white;\n"
                + "        border: 0px;\n"
                + "        margin: 0;\n"
                + "        padding: 15;\n"
                + "        text-align: center;\n"
                + "      }\n"
                + "      p {\n"
                + "        font-size: 10pt;\n"
                + "      }\n"
                + "    </style>\n"
                + "  </head>\n"
                + "  <body>\n"
                + "    <div style=\"border: 1px dotted blue; padding: 10px\">\n"
                + "    <p id=\"fslogo\">Flying Saucer</p>\n"
                + "    <img src=\"images/flyingsaucer.png\" />\n"
                + "    <p id=\"pagebyline\">Browser Application Demo</p>\n"
                + "\n"
                + "    <p>An example of integrating Flying Saucer in a real application.</p>\n"
                + "    <p><em>(This is not a real web browser)</em></p>\n"
                + "\n"
                + "    <p>Licensed under the GNU Lesser General Public License.</p>\n"
                + "    </div>\n"
                + "\n"
                + "  </body>\n"
                + "</html>";

        String certificatePath = MONTH_DATEFORMAT.format(new Date()) + "/" + java.util.UUID.randomUUID().toString() + ".pdf";
        String fullPath = EnvironMentVariables.STORAGE_PATH + certificatePath;
        new File(fullPath).getParentFile().mkdirs();
        PdfRendererBuilder builder = new PdfRendererBuilder();
       
        builder.withHtmlContent(html, "file://"+EnvironMentVariables.STORAGE_PATH);

        try (FileOutputStream outputStream = new FileOutputStream(fullPath)) {
            builder.toStream(outputStream);
            builder.run();
        }
        System.out.println(fullPath);
    }
}
