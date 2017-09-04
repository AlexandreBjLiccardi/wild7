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

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Decorateur : permet de voir une NodeList DOM comme une List java standard.
 *
 * @author Johann Sorel (Geomatys)
 */
public class DomNodeList extends AbstractList<Node> {

	private final NodeList lst;

	public DomNodeList(NodeList lst) {
		this.lst = lst;
	}

	@Override
	public Node get(int index) {
		if (lst==null) {
			throw new IndexOutOfBoundsException();
		} else {
			return lst.item(index);
		}
	}

	@Override
	public int size() {
		return lst==null ? 0 : lst.getLength();
	}

	/**
	 * Collection des noeuds enfant uniquement de la classe Element.
	 * @return
	 */
	public Collection<Element> elements(){
		return new ElementList();
	}

	private class ElementList extends AbstractCollection<Element> {

		@Override
		public Iterator<Element> iterator() {
			return new FilteringIterator(DomNodeList.this.iterator()) {
				@Override
				protected boolean accept(Object input) {
					return input instanceof Element;
				}
			};
		}

		@Override
		public int size() {
			int size = 0;
			for(Iterator i=iterator();i.hasNext();i.next(),size++);
			return size;
		}
	}

}
