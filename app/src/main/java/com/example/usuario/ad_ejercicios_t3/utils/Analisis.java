package com.example.usuario.ad_ejercicios_t3.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.util.Xml;

import com.example.usuario.ad_ejercicios_t3.Noticia;
import com.example.usuario.ad_ejercicios_t3.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;


public class Analisis {

    public static String analizar(String texto) throws XmlPullParserException, IOException {
        StringBuilder cadena = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new StringReader(texto));
        int eventType = xpp.getEventType();
        cadena.append("Leyendo XML:\n ");
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_DOCUMENT)
                cadena.append("START DOCUMENT \n");
            if (eventType == XmlPullParser.START_TAG)
                cadena.append("START TAG: " + xpp.getName() + "\n");
            if (eventType == XmlPullParser.TEXT)
                cadena.append("CONTENT: " + xpp.getText() + "\n");
            if (eventType == XmlPullParser.END_TAG)
                cadena.append("END TAG: " + xpp.getName() + "\n");
            eventType = xpp.next();
        }
        cadena.append("END DOCUMENT");
        return cadena.toString();
    }

    public static String analizarXmlNextText(Context c) throws XmlPullParserException, IOException {

        StringBuilder stringResultante = new StringBuilder();

        double suma = 0.0;
        double getNota = 0;
        int contador = 0;


        XmlResourceParser xrp = c.getResources().getXml(R.xml.alumnos);
        int eventType = xrp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (xrp.getName().equals("nombre")) {
                        stringResultante.append("Nombre: " + xrp.nextText() + "\n");
                    }

                    if (xrp.getName().equals("nota")) {

                        for (int i = 0; i < xrp.getAttributeCount(); i++) {
                            stringResultante.append(xrp.getAttributeName(i) + ": " + xrp.getAttributeValue(i) + "\n");
                            contador++;
                        }

                        getNota = Double.parseDouble(xrp.nextText());
                        stringResultante.append("Nota: " + Double.toString(getNota) + "\n");
                        suma += getNota;
                    }
                    if (xrp.getName().equals("observaciones")) {
                        stringResultante.append("Observaciones: " + xrp.nextText() + "\n\n");
                    }
                    break;
            }
            eventType = xrp.next();
        }

        stringResultante.append("Media de todas las notas : " + String.format("%.2f", suma / contador));
        xrp.close();
        return stringResultante.toString();
    }

    public static String analizarXmlGet(Context c) throws XmlPullParserException, IOException {
        boolean esNombre = false;
        boolean esNota = false;
        StringBuilder stringResultante = new StringBuilder();
        Double suma = 0.0;
        int contador = 0;

        XmlResourceParser xrp = c.getResources().getXml(R.xml.alumnos);
        int eventType = xrp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (xrp.getName().equals("nombre")) {
                        esNombre = true;
                    }

                    if (xrp.getName().equals("nota")) {
                        esNota = true;

                        for (int i = 0; i < xrp.getAttributeCount(); i++) {
                            stringResultante.append(xrp.getAttributeName(i) + ": " + xrp.getAttributeValue(i) + "\n");
                            contador++;
                        }
                    }

                    if (xrp.getName().equals("observaciones")) {

                    }
                    break;
                case XmlPullParser.TEXT:

                    if (esNombre) {
                        stringResultante.append("Nombre: " + xrp.getText() + "\n");
                    }
                    else if (esNota) {
                        suma += Double.parseDouble(xrp.getText());
                        stringResultante.append("Nota: " + xrp.getText() + "\n");
                    }
                    else {
                        stringResultante.append("Observaciones: " + xrp.getText() + "\n");
                    }

                    break;
                case XmlPullParser.END_TAG:
                    if (xrp.getName().equals("nombre"))
                        esNombre = false;
                    if (xrp.getName().equals("nota"))
                        esNota = false;
                    if (xrp.getName().equals("alumno"))
                        stringResultante.append("\n");
                    break;
            }
            eventType = xrp.next();
        }

        stringResultante.append("Media de todas las notas : " + String.format("%.2f", suma / contador));
        xrp.close();
        return stringResultante.toString();
    }


    public static String analizarNombres(Context c) throws XmlPullParserException,
            IOException {
        boolean esNombre = false;
        boolean esNota = false;
        StringBuilder cadena = new StringBuilder();
        Double suma = 0.0;
        int contador = 0;
        XmlResourceParser xrp = c.getResources().getXml(R.xml.alumnos);
        int eventType = xrp.getEventType();
        while (eventType != XmlPullParser. END_DOCUMENT ) {
            switch (eventType) {
                case XmlPullParser.START_TAG :
                    break;
                case XmlPullParser.TEXT :
                    break;
                case XmlPullParser.END_TAG :
                    break;
            }
            eventType = xrp.next();
        }
        return cadena.toString();
    }

    public static String analizarRSS(File file) throws NullPointerException, XmlPullParserException, IOException {

        boolean dentroItem = false;

        StringBuilder builder = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new FileReader(file));

        int eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("item"))
                        dentroItem = true;

                    if (dentroItem && xpp.getName().equals("title"))
                    {
                        builder.append("Post: " + xpp.next() + "\n");
                        dentroItem = false;
                    }
                    break;
            }
            eventType = xpp.next();
        }
        return builder.toString();
    }

    public static ArrayList<Noticia> analizarNoticias(File file) throws XmlPullParserException, IOException {
        int eventType;
        ArrayList<Noticia> noticias = new ArrayList<>();
        Noticia actual = null;
        boolean dentroItem = false;
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new FileReader(file));
        eventType=xpp.getEventType();
        while (eventType!=XmlPullParser.END_DOCUMENT){
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    switch (xpp.getName()) {
                        case "item":
                            actual = new Noticia();
                            dentroItem = true;
                            break;
                        case "title":
                            if(dentroItem && actual != null)
                                actual.setTitular(xpp.nextText());
                            break;
                        case "link":
                            if(dentroItem && actual != null)
                                actual.setUrl(xpp.nextText());
                            break;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    switch (xpp.getName()) {
                        case "item":
                            if(dentroItem && actual != null)
                                noticias.add(actual);
                            actual = null;
                            break;
                    }
                    break;
            }
            eventType = xpp.next();
        }
        //devolver el array de noticias
        return noticias;
    }

    public static void crearXML(ArrayList<Noticia> noticias, String fichero) throws IOException {
        FileOutputStream output;
        output = new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fichero));
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(output, "UTF-8");
        serializer.startDocument(null, true);
        serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true); //poner tabulación
        serializer.startTag(null, "titulares");

        for (Noticia noticia : noticias) {
            serializer.startTag(null, "item");
            serializer.attribute(null, "fecha", noticia.getPubDate());
            serializer.startTag(null, "titular");
            serializer.text(noticia.getTitular());
            serializer.endTag(null, "titular");
            serializer.startTag(null, "url");
            serializer.text(noticia.getUrl());
            serializer.endTag(null, "url");
            serializer.startTag(null, "descripcion");
            serializer.text(noticia.getDescripcion());
            serializer.endTag(null, "descripcion");
            serializer.endTag(null, "item");
        }

        serializer.endTag(null, "titulares");
        serializer.endDocument();
        serializer.flush();
        output.close();
    }

    public static ArrayList<Noticia> analizarDescargaRSS(File file) throws XmlPullParserException, IOException {
        int eventType;
        ArrayList<Noticia> noticias = new ArrayList<>();
        Noticia actual = null;
        boolean dentroItem = false;
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new FileReader(file));
        eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    switch (xpp.getName()) {
                        case "item":
                            actual = new Noticia();
                            dentroItem = true;
                            break;
                        case "title":
                            if (dentroItem && actual != null)
                                actual.setTitular(xpp.nextText());
                            break;
                        case "link":
                            if (dentroItem && actual != null)
                                actual.setUrl(xpp.nextText());
                            break;
                        case "pubDate":
                            if (dentroItem && actual != null)
                                actual.setPubDate(xpp.nextText());
                            break;
                        case "description":
                            if (dentroItem && actual != null)
                                actual.setDescripcion(xpp.nextText());
                            break;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    switch (xpp.getName()) {
                        case "item":
                            if (dentroItem && actual != null)
                                noticias.add(actual);
                            actual = null;
                            break;
                    }
                    break;
            }
            eventType = xpp.next();
        }
        //devolver el array de noticias
        return noticias;
    }
}




