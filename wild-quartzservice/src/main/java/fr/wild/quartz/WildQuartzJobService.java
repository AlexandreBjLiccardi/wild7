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
package fr.wild.quartz;

import fr.wild.orchestra.Wild4Test;
import fr.wild.real.WildQuartzJobList;
import fr.wild.real.WildSenderServiceLooper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.geotoolkit.nio.IOUtilities;

/**
 * Service pour controler le scheduler quartz.
 *
 * @author Johann Sorel (Geomatys)
 */
public class WildQuartzJobService extends AbstractHandler {

	public static void main(String[] args) throws Exception {
		if (args.length==0) {
			new WildQuartzJobService(9000, "./config.xml");
		} else if (args.length==1) {
			new WildQuartzJobService(Integer.parseInt(args[0]), "./config.xml");
		} else {
			new WildQuartzJobService(Integer.parseInt(args[0]), args[1]);
		}
	}

	private final int port;
	private final String workspace;
	private final WildQuartzJobList list;
	
	public WildQuartzJobService(int port, String workspace) throws Exception {
		this.port = port;
		this.workspace = workspace;

		final Wild4Test builder = new Wild4Test();
		final String id = UUID.randomUUID().toString();
		builder.addObject(id, "real", "WildQuartzJobList", new String[]{workspace});
		list = builder.getObject(id);

		Server server = new Server(port);
		server.setHandler(this);
        server.start();
       
        server.join();
	}

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if ("/addJob".equalsIgnoreCase(target)) {
			String path = (String) baseRequest.getParameter("path");
			if (path==null) {
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				baseRequest.setHandled(true);
				response.getWriter().println("Parametre 'path' manquant");
				return;
			} else if (!new File(path).exists()) {
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				baseRequest.setHandled(true);
				response.getWriter().println("Le fichier "+path+" est manquant.");
				return;
			}

			try {
				list.addJob(path);
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
				response.getWriter().println("Job added");
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage(),ex);
			}
		} else if ("/printList".equalsIgnoreCase(target)) {
			try {
				final String lst = list.printList();
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
				response.getWriter().println(lst);
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage(),ex);
			}
		} else if ("/showList".equalsIgnoreCase(target)) {
			try {
				final String lst = list.printList();
				response.setContentType("text/xml");
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
				try (InputStream in = new FileInputStream(lst)) {
					IOUtilities.copy(in, response.getOutputStream());
				}
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage(),ex);
			}
		} else {
			baseRequest.setHandled(false);
		}

	}

}
