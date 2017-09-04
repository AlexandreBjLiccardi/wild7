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

import java.util.List;
import java.util.Map;
import org.apache.sis.internal.util.X364;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Utilitaire sur les String java.
 *
 * @author Johann Sorel (Geomatys)
 */
public class StringUtils {

	private static final String BOLD = X364.BOLD.sequence();
	private static final String PLAIN = X364.RESET.sequence();

	/**
	 * Mise en forme lisible du contenu d'un Map java.
	 *
	 * @param name
	 * @param map
	 * @return
	 */
	public static String toString(String name, Map<?,?> map) {
		final StringBuilder sb = new StringBuilder();
		if (name!=null) sb.append(name);
		if (map == null) return sb.toString();
		final int size = map.size();
		sb.append(" (size=").append(size).append(")");
		if (map.isEmpty()) return sb.toString();

		int i = 0;
		for (Map.Entry entry : map.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Map) {
				value = toString(null, (Map<?, ?>) value);
			} else if (value instanceof List) {
				value = toString((List) value);
			} else if(value instanceof Node) {
				value = toString((Node) value);
			}
			String str = String.valueOf(value);
			str = str.replaceAll("\n", (i==size-1) ? "\n     " : "\n  |  ");
			sb.append("\n  +--").append(BOLD).append(key).append(PLAIN).append(" : ").append(str);
			i++;
		}
		return sb.toString();
	}

	/**
	 * Mise en forme lisible du contenu d'un noeud DOM.
	 *
	 * @param node
	 * @return
	 */
	public static String toString(Node node) {
		final StringBuilder sb = new StringBuilder();
		sb.append(BOLD).append(node.getNodeName()).append(PLAIN);
		NamedNodeMap attributes = node.getAttributes();
		if (attributes!=null && attributes.getLength()>0) {
			sb.append("(");
			for (int i=0;i<attributes.getLength();i++) {
				final Attr att = (Attr) attributes.item(i);
				if(i!=0) sb.append(", ");
				sb.append(att.getName()).append("=").append(att.getValue());
			}
			sb.append(")");
		}


		final NodeList children = node.getChildNodes();
		if (children!=null) {
			if (children.getLength()==1 && children.item(0) instanceof Text) {
				String text = children.item(0).getTextContent();
				if(text!=null && !text.trim().isEmpty()){
					sb.append(" = ").append(text.trim());
				}
			} else {

				for (int i=0,n=children.getLength();i<n;i++) {
					Node item = children.item(i);
					if (item instanceof Text) {
						String str = ((Text)item).getTextContent().trim();
						if (!str.isEmpty()) {
							str = str.replaceAll("\n", (i==n-1) ? "\n     " : "\n  |  ");
							sb.append("\n  +-- text = ").append(str);
						}
					} else {
						String str = toString(item);
						str = str.replaceAll("\n", (i==n-1) ? "\n     " : "\n  |  ");
						sb.append("\n  +--").append(str);
					}
				}
			}
		}

		return sb.toString();
	}

	public static String toString(List lst) {
		final StringBuilder sb = new StringBuilder();
		sb.append("Liste (size=").append(lst.size()).append(")");
		for (int i=0,n=lst.size();i<n;i++) {
			Object value = lst.get(i);
			if (value instanceof Map) {
				value = toString(null, (Map<?, ?>) value);
			} else if (value instanceof List) {
				value = toString((List) value);
			} else if(value instanceof Node) {
				value = toString((Node) value);
			}
			String str = String.valueOf(value);
			str = str.replaceAll("\n", (i==n-1) ? "\n     " : "\n  |  ");
			sb.append("\n  +--").append(BOLD).append("[").append(i).append("]").append(PLAIN).append(" : ").append(str);
		}
		return sb.toString();
	}

}
