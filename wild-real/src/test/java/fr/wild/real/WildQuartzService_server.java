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
package fr.wild.real;

import fr.wild.common.IoCommons;
import fr.wild.common.IoFileSystem;
import fr.wild.orchestra.Wild4Test;
import fr.wild.real.WildQuartzJobList;

import static fr.wild.common.IoWilds.config_getTab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
public class WildQuartzService_server extends AbstractHandler {

	public static void main(String[] args) throws Exception {
		final HashMap<String, Object> diceConfigSub = config_getTab() ; 
		if (args.length==0) {
			new WildQuartzService_server((Integer)diceConfigSub.get("qConfig_port"), (String)diceConfigSub.get("qWorkspace_path"));
		} else if (args.length==1) {
			new WildQuartzService_server(Integer.parseInt(args[0]), (String)diceConfigSub.get("qWorkspace_path"));
		} else {
			new WildQuartzService_server(Integer.parseInt(args[0]), args[1]);
		}
	}

	private final int port;
	private final String workspace;
	private final WildQuartzJobList list;
	private final HashMap<String, Object> diceConfig = config_getTab() ; 
	private String execPath ;
	private String execPathFromLoop ;
	private CompletionService<ConcurrentHashMap<String,Object>> taskCompletionService = new ExecutorCompletionService<ConcurrentHashMap<String,Object>>(Executors.newCachedThreadPool()); 
	Future<ConcurrentHashMap<String, Object>> loop ;
	
	public WildQuartzService_server(int port, String workspace) throws Exception {

		this.port = port;
		this.workspace = workspace;
		execPath = workspace + "/" +  (String)diceConfig.get("qTests_path")+ "/";
		execPathFromLoop = workspace + "/" +  (String)diceConfig.get("qLoop_path")+ "/";
		final String execPath_temp = workspace + "/" +  (String)diceConfig.get("qExec_path")+ "/";
		IoFileSystem.file_delete(execPath_temp);
		new File(execPath).mkdirs();
		new File(execPath_temp).mkdirs();
		final Wild4Test builder = new Wild4Test();
		final String id = UUID.randomUUID().toString();
		builder.addObject(id, "real", "WildQuartzJobList", new String[]{workspace+"/"+ (String)diceConfig.get("qConfig_path")+"/config.xml"});
		list = builder.getObject(id);
		//demarrage du service
		Server server = new Server(port);
		server.setHandler(this);
        server.start();
        loop = taskCompletionService.submit(new WildSenderServiceLooper());
        server.join();
	}

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		final String killerPath = workspace+"/"+(String)diceConfig.get("qConfig_path")+"/"+(String)diceConfig.get("qConfig_killer");

		if ("/addJob".equalsIgnoreCase(target)) {
			String path = baseRequest.getParameter("path");
			path = (path ==null)? null:execPath+path ;
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
				String nId = list.addJob(path);
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
				if(nId!=null)response.getWriter().println("Job added : "+nId);
				else response.getWriter().println("999 : Job déjà attribué.");
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage(),ex);
			}
		} else if ("/addJobFromLoop".equalsIgnoreCase(target)) {
			String path = baseRequest.getParameter("path");
			path = (path ==null)? null:execPathFromLoop+path ;
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
				String nId = list.addJob(path);
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
				if(nId!=null)response.getWriter().println("Job added : "+nId);
				else response.getWriter().println("999 : Job déjà attribué.");
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
		} else if ("/killLoop".equalsIgnoreCase(target)) {
			try {
				(new File(killerPath)).createNewFile();
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
				response.getWriter().println("Loop flag submitted.");
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage(),ex);
			}
		} else if ("/startLoop".equalsIgnoreCase(target)) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.setStatus(HttpServletResponse.SC_OK);
				baseRequest.setHandled(true);
				if(loop==null||loop.isCancelled()||loop.isDone()){
					if(new File(killerPath).exists())new File(killerPath).delete();
					loop=taskCompletionService.submit(new WildSenderServiceLooper());
					response.getWriter().println("Loop started.");
				}else{
					response.getWriter().println("Loop already launched.");
				}				
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage(),ex);
			}
		} else if ("/deleteJob".equalsIgnoreCase(target)) {
			try {
				String path0 = baseRequest.getParameter("path");
				String path = (path0 ==null)? null:execPath+path0 ;
				if (path==null) {
					response.setContentType("text/html;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					baseRequest.setHandled(true);
					response.getWriter().println("Parametre 'path' manquant");
					return;
				} else if (!new File(path).exists()) {
					path = path0;
				}
					Boolean nId = list.deleteJob(path);
					response.setContentType("text/html;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_OK);
					baseRequest.setHandled(true);
					if(nId!=null)response.getWriter().println("Deleting job attempt : "+nId);
					else response.getWriter().println("999 : Unable to delete job.");	
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage(),ex);
			}
		}  else if ("/killJob".equalsIgnoreCase(target)) {
			try {
				String path0 = baseRequest.getParameter("path");
				String path = (path0 ==null)? null:execPath+path0 ;
				if (path==null) {
					response.setContentType("text/html;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					baseRequest.setHandled(true);
					response.getWriter().println("Parametre 'path' manquant");
					return;
				} else if (!new File(path).exists()) {
					path = path0;
				}
					Boolean nId = list.DVP_killJob(path);
					response.setContentType("text/html;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_OK);
					baseRequest.setHandled(true);
					if(nId!=null)response.getWriter().println("Killing job attempt : "+nId);
					else response.getWriter().println("999 : Unable to stop job.");	
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage(),ex);
			}
		} else if ("/infoJob".equalsIgnoreCase(target)) {
			try {
				String path0 = baseRequest.getParameter("path");
				String path = (path0 ==null)? null:execPath+path0 ;
				if (path==null) {
					response.setContentType("text/html;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					baseRequest.setHandled(true);
					response.getWriter().println("Parametre 'path' manquant");
					return;
				} else if (!new File(path).exists()) {
					path = path0;
				}
					final String lst = IoCommons.cast_2String(list.infoJob(path));				
					response.setContentType("text/html;charset=utf-8");
					response.setStatus(HttpServletResponse.SC_OK);
					baseRequest.setHandled(true);
					response.getWriter().println(lst);

					
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ServletException(ex.getMessage(),ex);
			}
		} else {
			baseRequest.setHandled(false);
		}
	}

}
