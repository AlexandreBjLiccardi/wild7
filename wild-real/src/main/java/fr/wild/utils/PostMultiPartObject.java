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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.IOUtils;

/**
 * Object descriptif d'un element dans une requete POST en mode multi-part.
 *
 * @author Johann Sorel (Geomatys)
 */
public class PostMultiPartObject {

	private static final String CRLF = "\r\n";

	private final Map<String,String> properties;
	private final String contentType;
	private final Object data;

	public PostMultiPartObject(String name, String contentType, Object data) {
		this(Collections.singletonMap("name", name),contentType,data);
	}

	public PostMultiPartObject(Map<String,String> properties, String contentType, Object data) {
		this.properties = properties==null ? Collections.EMPTY_MAP : properties;
		this.contentType = contentType;
		this.data = data;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public String getContentType() {
		return contentType;
	}

	public Object getData() {
		return data;
	}

	/**
	 * Ecriture du multi-part dans le flux de sortie.
	 *
	 * @param out
	 */
	public void writePart(PrintWriter writer, OutputStream out) throws IOException {


		//ecriture des valeurs
		Object cdt = data;
		if (cdt instanceof String && new File((String)cdt).exists()) {
			cdt = new File((String)cdt);
		}

		//ecriture de l'entete
		writer.append("Content-Disposition: form-data");
		String name = null;
		String filename = null;
		for (Entry<String,String> entry : properties.entrySet()) {
			writer.append("; "+entry.getKey()+"=\""+entry.getValue()+"\"");
			if("name".equals(entry.getKey())) name = entry.getValue();
			if("filename".equals(entry.getKey())) filename = entry.getValue();
		}

		//on ajoute le nom
		if (name==null) {
			name = "data";
			if (cdt instanceof File) {
				name = ((File)cdt).getName();
			}
			writer.append("; name=\""+name+"\"");
		}
		//on ajoute le nom du fichier si c'est un File et que filename est null
		if (filename==null && cdt instanceof File) {
			writer.append("; filename=\""+((File)cdt).getName()+"\"");
		}

		writer.append(CRLF);
		if (contentType!=null) {
			writer.append("Content-Type: " + contentType).append(CRLF);
		}

		if (cdt instanceof File) {
			writer.append("Content-Transfer-Encoding: binary").append(CRLF);
			writer.append(CRLF).flush();
			final File file = (File) cdt;
			Files.copy(file.toPath(), out);
			out.flush();
		} else if (cdt instanceof byte[]) {
			writer.append("Content-Transfer-Encoding: binary").append(CRLF);
			writer.append(CRLF).flush();
			final ByteArrayInputStream array = new ByteArrayInputStream((byte[]) cdt);
			array.reset();
			IOUtils.copy(array, out);
			out.flush();
		} else if (cdt instanceof String) {
			writer.append(CRLF).flush();
			writer.append((String)cdt);
		} else {
			throw new IOException("Element invalide pour requete POST en multipart : "+cdt);
		}
		writer.append(CRLF).flush();

	}

}
