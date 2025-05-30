package com.sarkhan.dtssystem.config;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class PdfScriptChecker {

    public List<String> findJavaScriptCodes(InputStream pdfInputStream) {
        List<String> foundScripts = new ArrayList<>();
        try (PDDocument document = PDDocument.load(pdfInputStream)) {

            // 📄 Check document-level JavaScript (Catalog → Names → JavaScript)
            COSDictionary catalog = document.getDocumentCatalog().getCOSObject();
            COSDictionary names = (COSDictionary) catalog.getDictionaryObject(COSName.NAMES);
            if (names != null) {
                COSDictionary jsDict = (COSDictionary) names.getDictionaryObject(COSName.JAVA_SCRIPT);
                if (jsDict != null) {
                    extractJavaScripts(jsDict, foundScripts);
                }
            }

            // 🖋 Check page annotations
            for (PDPage page : document.getPages()) {
                for (PDAnnotation annotation : page.getAnnotations()) {
                    COSDictionary annotDict = annotation.getCOSObject();
                    COSBase actionBase = annotDict.getDictionaryObject(COSName.A);
                    if (actionBase instanceof COSDictionary actionDict) {
                        String sType = actionDict.getNameAsString(COSName.S);
                        if ("JavaScript".equals(sType)) {
                            String js = actionDict.getString(COSName.JS);
                            if (js != null) {
                                foundScripts.add(js);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundScripts;
    }

    private void extractJavaScripts(COSDictionary jsDict, List<String> foundScripts) {
        COSBase namesBase = jsDict.getDictionaryObject(COSName.NAMES);
        if (namesBase instanceof COSDictionary) {
            COSDictionary names = (COSDictionary) namesBase;
            for (COSName key : names.keySet()) {
                COSBase jsBase = names.getDictionaryObject(key);
                if (jsBase instanceof COSDictionary jsEntry) {
                    String js = jsEntry.getString(COSName.JS);
                    if (js != null) {
                        foundScripts.add(js);
                    }
                }
            }
        }
    }
}
