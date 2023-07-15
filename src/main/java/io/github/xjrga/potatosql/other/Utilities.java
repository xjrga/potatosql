package io.github.xjrga.potatosql.other;

import io.github.xjrga.potatosql.gui.Message;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Utilities {
    public Utilities() {
    }
    public static String sha256_hash_to_hex( String s ) {
        StringBuilder sb = null;
        try {
            MessageDigest digest = MessageDigest.getInstance( "SHA-256" );
            byte[] encodedhash = digest.digest( s.getBytes( StandardCharsets.UTF_8 ) );
            sb = new StringBuilder( 2 * encodedhash.length );
            for ( byte b : encodedhash ) {
                sb.append( String.format( "%02x", b ) );
            }
        } catch ( NoSuchAlgorithmException ex ) {
        }
        return sb.toString();
    }
    public static String random() {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < 8; i++ ) {
            sb.append( Math.random() );
        }
        String replace = sb.toString().replace( ".", "" );
        return replace.substring( 0, 128 );
    }
    private String convert_to_hex_02( byte[] hash ) {
        StringBuilder sb = new StringBuilder( 2 * hash.length );
        for ( byte b : hash ) {
            sb.append( String.format( "%02x", b ) );
        }
        return sb.toString();
    }
    private String convert_to_hex_03( byte[] hash ) {
        StringBuilder sb = new StringBuilder( 2 * hash.length );
        for ( byte b : hash ) {
            sb.append( Integer.toString( (b & 0xff) + 0x100, 16 ).substring( 1 ) );
        }
        return sb.toString();
    }
    public static String format_xml_doc( String xml ) {
        String doc = "";
        try {
            final InputSource src = new InputSource( new StringReader( xml ) );
            final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse( src ).getDocumentElement();
            final Boolean keepDeclaration = xml.startsWith( "<?xml" );
            final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            final DOMImplementationLS impl = ( DOMImplementationLS ) registry.getDOMImplementation( "LS" );
            final LSSerializer writer = impl.createLSSerializer();
            writer.getDomConfig().setParameter( "format-pretty-print", Boolean.TRUE );
            writer.getDomConfig().setParameter( "xml-declaration", keepDeclaration );
            doc = writer.writeToString( document );
        } catch ( IOException | ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException | ParserConfigurationException | DOMException | LSException | SAXException e ) {
            e.printStackTrace();
        }
        return doc;
    }
    public static boolean validate_xml_doc( String schema_path, String xmldoc_path ) {
        boolean result = false;
        Source xmlDoc = new StreamSource( new File( xmldoc_path ) );
        SchemaFactory schemaFactory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
        try {
            Schema xmlSchema = schemaFactory.newSchema( new File( schema_path ) );
            Validator validator = xmlSchema.newValidator();
            validator.validate( xmlDoc );
            result = true;
        } catch ( IOException | SAXException e ) {
            Message.showMessage( "Xml document validation error ocurred.\nMessage is " + e.getMessage() );
        }
        return result;
    }
    public static String capitalize( String word ) {
        StringBuilder sb = new StringBuilder();
        sb.append( word.substring( 0, 1 ).toUpperCase() );
        sb.append( word.substring( 1 ).toLowerCase() );
        return sb.toString();
    }
    private Date createDate( String dateastxt )
            throws ParseException {
        //"2019/09/06 19:00:00"
        long millis = getMillis( dateastxt );
        Date date = new Date( millis );
        return date;
    }
    private Time createTime( String dateastxt )
            throws ParseException {
        //"2019/09/06 19:00:00"
        long millis = getMillis( dateastxt );
        Time time = new Time( millis );
        return time;
    }
    private Timestamp createTimestamp( String dateastxt )
            throws ParseException {
        //"2019/09/06 19:00:00"
        long millis = getMillis( dateastxt );
        Timestamp timestamp = new Timestamp( millis );
        return timestamp;
    }
    private long getMillis( String timestamp )
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
        return sdf.parse( timestamp ).getTime();
    }
    private Blob createBlob( Connection connection, String filepath )
            throws SQLException, IOException {
        Blob blob = connection.createBlob();
        byte[] bytes = Files.readAllBytes( Paths.get( filepath ) );
        blob.setBytes( 1, bytes );
        return blob;
    }
    private Clob createClob( Connection connection, String filepath )
            throws SQLException, IOException {
        Clob clob = connection.createClob();
        byte[] bytes = Files.readAllBytes( Paths.get( filepath ) );
        clob.setString( 1, new String( bytes ) );
        return clob;
    }
    public static boolean contains_text( String s ) {
        String_check check = new String_check();
        check.add( s );
        return check.pass();
    }
    public static void openUrl( String url ) {
        try {
            Desktop.getDesktop().browse( new URL( url ).toURI() );
        } catch ( IOException | URISyntaxException e ) {
        }
    }
    public static String convert_file_to_string( String path ) {
        String str = "";
        try {
            str = new String( Files.readAllBytes( Path.of( path ) ) );
        } catch ( IOException ex ) {
        }
        return str;
    }
}
