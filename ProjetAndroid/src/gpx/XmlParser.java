package gpx;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class XmlParser {
	
	private static final String ns = null;
	   
    public GPX parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }
    
    private GPX readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        GPX entries = new GPX();

        parser.require(XmlPullParser.START_TAG, ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("entry")) {
                entries.addTrack(readEntry(parser));
            } else {
                skip(parser);
            }
        }  
        return entries;
    }
    
	 // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
	 // to their respective "read" methods for processing. Otherwise, skips the tag.
	 private Track readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
	     parser.require(XmlPullParser.START_TAG, ns, "entry");
	     String title = null;
	     String summary = null;
	     String link = null;
	     while (parser.next() != XmlPullParser.END_TAG) {
	         if (parser.getEventType() != XmlPullParser.START_TAG) {
	             continue;
	         }
	         String name = parser.getName();
	         if (name.equals("title")) {
	             title = readTitle(parser);
	         } else if (name.equals("summary")) {
	             //summary = readSummary(parser);
	         } else if (name.equals("link")) {
	             //link = readLink(parser);
	         } else {
	             skip(parser);
	         }
	     }
	     Track tck = new Track();
	     
	     return tck;//Entry(title, summary, link);
	 }
	
	 // Processes title tags in the feed.
	 private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
	     parser.require(XmlPullParser.START_TAG, ns, "title");
	     String title = readText(parser);
	     parser.require(XmlPullParser.END_TAG, ns, "title");
	     return title;
	 }
	 
	// For the tags title and summary, extracts their text values.
	 private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	     String result = "";
	     if (parser.next() == XmlPullParser.TEXT) {
	         result = parser.getText();
	         parser.nextTag();
	     }
	     return result;
	 }
	 
	 private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
		    if (parser.getEventType() != XmlPullParser.START_TAG) {
		        throw new IllegalStateException();
		    }
		    int depth = 1;
		    while (depth != 0) {
		        switch (parser.next()) {
		        case XmlPullParser.END_TAG:
		            depth--;
		            break;
		        case XmlPullParser.START_TAG:
		            depth++;
		            break;
		        }
		    }
		 }



}
