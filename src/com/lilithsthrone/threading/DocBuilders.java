package com.lilithsthrone.threading;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

/**
 * @since 0.4.10.7
 * @version 0.4.10.7
 * @author KeldonSlayer (DrZed)
 */
public class DocBuilders {
    private static final ArrayList<DocumentBuilderFactory> docFactories = new ArrayList<>();
    private static final ArrayList<DocumentBuilder> docBuilders = new ArrayList<>();
    private static int docBuilderIndex = 0;

    public static Document parseDoc(File xmlFile) {
        Document doc;
        do {
            doc = parseResetGet(xmlFile);
        } while (doc == null);
        return doc;
    }

    public static Document parseString(String str) {
        Document doc;
        do {
            doc = parseResetGet(str);
        } while (doc == null);
        return doc;
    }

    private static Document parseResetGet(File xmlFile) {
        if (!xmlFile.exists()) { System.out.println("File doesn't exist : " + xmlFile.getPath()); return null; }
        Document doc;
        try {
            DocumentBuilder db = getNextDocBuilder();
            doc = db.parse(xmlFile);
            db.reset();
        } catch (Exception ignored) { return null; }// Exception Thrown is a Concurrency Issue
        return doc;
    }

    private static Document parseResetGet(String str) {
        Document doc;
        try {
            DocumentBuilder db = getNextDocBuilder();
            doc = db.parse(new ByteArrayInputStream(str.getBytes()));
            db.reset();
        } catch (Exception ignored) { return null; }// Exception Thrown is a Concurrency Issue
        return doc;
    }

    private static void initBuilders() {
        for (int i = 0; i < 15; i++)
            docFactories.add(DocumentBuilderFactory.newInstance());
        try {
            for (DocumentBuilderFactory docFactory : docFactories)
                docBuilders.add(docFactory.newDocumentBuilder());
        } catch (Exception ignored) {}
    }

    public static DocumentBuilder getNextDocBuilder() {
        if (docFactories.isEmpty())
            initBuilders();
        docBuilderIndex++;
        if (docBuilderIndex >= docBuilders.size())
            docBuilderIndex = 0;
        return docBuilders.get(docBuilderIndex);
    }
}