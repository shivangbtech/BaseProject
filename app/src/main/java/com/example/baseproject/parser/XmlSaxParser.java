package com.example.baseproject.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class XmlSaxParser {

	public static void saxParser(InputStream stream, ContentHandler xmlHandler){
		InputStream in = stream;  //getResources().openRawResource(R.raw.myxml);
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			//			MyXMLHandler myXMLHandler = new MyXMLHandler();
			xr.setContentHandler(xmlHandler);
			xr.parse(new InputSource(in));
		} catch (Exception e) {
			System.out.println("XML Pasing Excpetion = " + e);
		}
	}

	public static void saxParserByUrl(String url, ContentHandler xmlHandler){
		try {
			URL sourceUrl = new URL(url);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			//			MyXMLHandler myXMLHandler = new MyXMLHandler();
			xr.setContentHandler(xmlHandler);
			xr.parse(new InputSource(sourceUrl.openStream()));
		} catch (Exception e) {
			System.out.println("XML Pasing Excpetion = " + e);
		}

	}

	public boolean saxParserByString(String data, ContentHandler xmlHandler){
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			//			MyXMLHandler myXMLHandler = new MyXMLHandler();
			xr.setContentHandler(xmlHandler);
			// convert String into InputStream
			InputStream is = new ByteArrayInputStream(data.getBytes());
		 
			xr.parse(new InputSource(is));
			return true;
		} catch (Exception e) {
			System.out.println("XML Pasing Excpetion = " + e);
			return false;
		}
	}
	
}
