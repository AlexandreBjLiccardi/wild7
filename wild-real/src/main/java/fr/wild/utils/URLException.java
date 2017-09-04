/*
 *  ONEMA Dice project.
 *  Copyright (C) 2016 ONEMA
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project. If not, see http://www.gnu.org/licenses.
 */
package fr.wild.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.sis.io.TableAppender;
import org.geotoolkit.nio.IOUtilities;
import org.geotoolkit.util.DomUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import fr.wild.common.IoCommons;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class URLException extends IOException {

	private final URLConnection cnx;
	private String message;
	private String content;
	private Node contentNode;

	public URLException(URLConnection cnx, Throwable cause) {
		super("",cause);
		this.cnx = cnx;
		toMessage(cnx, cause);
	}

	@Override
	public String getLocalizedMessage() {
		return getMessage();
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getContent() {
		return content;
	}
	
	public Node getContentNode() {
		return contentNode;
	}

	public URLConnection getURLConnection() {
		return cnx;
	}

	private void toMessage(URLConnection cnx, Throwable ex) {
		content = "";
		if (cnx instanceof HttpURLConnection) {
			final HttpURLConnection httpUrl = (HttpURLConnection) cnx;
			try (InputStream in = httpUrl.getErrorStream()) {
				final ByteArrayOutputStream out = new ByteArrayOutputStream();
				IOUtilities.copy(in, out);
				content = new String(out.toByteArray());
				try{
					contentNode = IoCommons.cast_String2Node(new String(out.toByteArray()));
				}catch(Exception e){
					contentNode = IoCommons.cast_String2Node("<Errors status = \"Unable to parse\">"+IoCommons.cast_xmlEscape(new String(out.toByteArray()))+"</Errors>");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		//on essai de mettre en forme le text
		if (!content.isEmpty()) {
			try {
				final Document dom = DomUtilities.read(content);
				content = fr.wild.utils.StringUtils.toString(dom);
			} catch (ParserConfigurationException | SAXException | IOException ex1) {
			}
		}

		final StringWriter writer = new StringWriter();
		writer.append(ex.getMessage()).append('\n');
		final TableAppender tablewriter = new TableAppender(writer);
		tablewriter.setMultiLinesCells(true);

		tablewriter.appendHorizontalSeparator();

		for(Map.Entry<String,List<String>> entry : cnx.getHeaderFields().entrySet()){
			tablewriter.append((entry.getKey()!= null)? entry.getKey() : "null");
			tablewriter.nextColumn();
			tablewriter.append(org.apache.commons.lang.StringUtils.join(entry.getValue(), ";"));
			tablewriter.nextLine();
		}
		tablewriter.appendHorizontalSeparator();
		tablewriter.append("Message");
		tablewriter.nextColumn();
		tablewriter.append(content);
		tablewriter.nextLine();
		tablewriter.appendHorizontalSeparator();

		try {
			tablewriter.flush();
		} catch (IOException e) {
			//will never happen is this case
			e.printStackTrace();
		}

		message = writer.toString();
	}


}
