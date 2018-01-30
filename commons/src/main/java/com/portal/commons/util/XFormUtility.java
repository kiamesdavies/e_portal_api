/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.util;

import com.goebl.david.Webb;
import com.google.inject.Singleton;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import org.javatuples.Pair;

import org.json.JSONObject;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import play.Logger;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
@Singleton
public class XFormUtility {

    @Inject
    private Webb webb;

    public List<Pair<String, String>> combineXLSJsonConversion(File file, String formVersionId) throws IOException {
        Path xls = Files.createTempFile("sample-", ".xls");
        Files.move(Paths.get(file.getAbsolutePath()), xls, StandardCopyOption.REPLACE_EXISTING);
        xls.toFile().deleteOnExit();

        CompletableFuture<Pair<String, String>> xformFuture = CompletableFuture.supplyAsync(() -> {

            try {
                return this.convertXlsFormToXForm(xls.toFile(), formVersionId);
            } catch (Exception e) {
                Logger.error("Can not run command", e);
                return Pair.with("error", Optional.ofNullable(e.getMessage()).orElse(e.getClass().getName()));
            }

        });

        CompletableFuture<Pair<String, String>> jsonFuture = CompletableFuture.supplyAsync(() -> {

            try {
                return this.convertXlsFormToJson(xls.toFile(), formVersionId);
            } catch (Exception e) {
                Logger.error("Can not run command for json", e);
                return Pair.with("error", Optional.ofNullable(e.getMessage()).orElse(e.getClass().getName()));
            }
        });

        CompletableFuture<Pair<String, String>> previewFuture = CompletableFuture.supplyAsync(() -> this.formPreviewUrl(formVersionId));

        List<Pair<String, String>> join = xformFuture
                .thenCombine(jsonFuture, (a, b) -> Arrays.asList(a, b)).thenCombine(previewFuture, (c, d)
                -> Arrays.asList(c.get(0), c.get(1), d)
        ).join();

        return join;

    }

    public Pair<String, String> convertXlsFormToXForm(File xlsform, String formVersionId) throws IOException {
        final Path xFormOutput;

        xFormOutput = Files.createTempFile("output-", ".xml");

        final String[] xls2xform = new String[]{"python", "/usr/local/lib/python2.7/dist-packages/pyxform/xls2xform.py", xlsform.getAbsolutePath(), xFormOutput.toFile().getAbsolutePath(), "--json"};
        String s1 = "";
        try {
            s1 = Utility.runCommand(xls2xform);

            Logger.info("Xlsform conversion [{}]", s1);
            String s2 = String.join("", Files.readAllLines(xFormOutput).toArray(new String[]{}));
            return Pair.with(s1, XFormUtility.addFormVersionToXForm(s2, formVersionId));
        } catch (Exception e) {
            Logger.error("Can not run command", e);
            return Pair.with("error", s1);
        } finally {
            xFormOutput.toFile().delete();
        }
    }

    public Pair<String, String> convertXlsFormToJson(File xlsform, String formVersionId) throws IOException {

        final Path jsonOutput;

        jsonOutput = Files.createTempFile("output-", ".json");
        final String[] xls2json = new String[]{"python", "/usr/local/lib/python2.7/dist-packages/pyxform/xls2json.py", xlsform.getAbsolutePath(), jsonOutput.toFile().getAbsolutePath()};
        String s1 = "";
        try {
            s1 = Utility.runCommand(xls2json);

            String s2 = String.join("", Files.readAllLines(jsonOutput).toArray(new String[]{}));
            return Pair.with(s1, s2);
        } catch (Exception e) {
            Logger.error("Can not run command for json", e);
            return Pair.with("error", s1);
        } finally {
            jsonOutput.toFile().delete();
        }

    }

    public Pair<String, String> formPreviewUrl(String formVersionId) {
        try {
            String s1 = null;

            JSONObject result = webb.post(EnvironMentVariables.ENKETO_SERVER_URL + "/api/v2/survey/preview/iframe").header("Authorization", EnvironMentVariables.ENKETO_SERVER_TOKEN).param("server_url", EnvironMentVariables.API_SERVER_URL).param("form_id", formVersionId).ensureSuccess().asJsonObject().getBody();
            String s2 = result.get("preview_url").toString();
            return Pair.with(s1, s2);
        } catch (Exception e) {
            return Pair.with("error", e.getMessage());
        }
    }

    public static String addFormVersionToXForm(String xFormStructure, String formversionId) throws JDOMException, IOException, JDOMParseException {
        // the SAXBuilder is the easiest way to create the JDOM2 objects.
        SAXBuilder jdomBuilder = new SAXBuilder();

        // jdomDocument is the JDOM2 Object
        Document jdomDocument = jdomBuilder.build(new ByteArrayInputStream(xFormStructure.getBytes(StandardCharsets.UTF_8)));

        XPathFactory xFactory = XPathFactory.instance();

        // we create a dummy namespace prefix that points to the default
        // namespace and then access the element using that namespace
        Namespace defaultNs = Namespace.getNamespace("a", "http://www.w3.org/2002/xforms");
        XPathExpression<Element> expr2 = xFactory.compile("//a:instance", Filters.element(), null, defaultNs);
        Element child2 = expr2.evaluateFirst(jdomDocument);

        child2.getChildren().forEach(s -> {
            s.setAttribute("version", formversionId);
        });
        //Output as XML
        //create XMLOutputter
        XMLOutputter xml = new XMLOutputter();
        return xml.outputString(jdomDocument);
    }

}
