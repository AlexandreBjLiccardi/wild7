package fr.wild.real;

import static fr.wild.common.IoWilds.config_getTab;

import java.net.URI;
import java.util.HashMap;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;


public class WildQuartzService_client {

	public static void main(String [ ] args) throws Exception{
		args = new String[2];
		args[0] = "addJob";
		args[1] = "Test1.xml";
		if(args==null||args.length==0||args[0]==null){
			System.out.println("NOTHING TO DO.");
			return;
		}
		final HashMap<String, Object> diceConfig = config_getTab() ; 
		Integer port = (Integer)diceConfig.get("qConfig_port");
		String method = ((Boolean)diceConfig.get("qConfig_https"))?"https":"http";
		HttpClient httpClient = new HttpClient();
	  	httpClient.setFollowRedirects(false);
	  	URI uri ;
	  	ContentResponse contents ;
	  	String msg ;
		switch(args[0].toLowerCase()){
		case "addjob":
			if(args.length<2||args[1]==null)break;
		  	httpClient.start();
		  	uri = new URI(method, "localhost:"+port, "/addJob", "path="+args[1], null);	
		  	contents = httpClient.GET(uri.toASCIIString());
		  	msg = contents.getContentAsString().trim();
		  	if(contents.getStatus()==400)System.out.println("ERROR : " + msg);
		  	else if(msg!=null&&!msg.isEmpty()&&!msg.startsWith("999 "))System.out.println("ADDED FROM JAVA CLIENT : " + msg);  	
			break;
		case "killjob":
			if(args.length<2||args[1]==null)break;
		  	httpClient.start();
		  	uri = new URI(method, "localhost:"+port, "/killJob", "path="+args[1], null);	
		  	contents = httpClient.GET(uri.toASCIIString());
		  	msg = contents.getContentAsString().trim();
		  	if(contents.getStatus()==400)System.out.println("ERROR : " + msg);
		  	else if(msg!=null&&!msg.isEmpty()&&!msg.startsWith("999 "))System.out.println("KILLED FROM JAVA CLIENT : " + msg);  	
			break;
		case "printlist":
		  	httpClient.start();
		  	uri = new URI(method, "localhost:"+port, "/printList",null,null);	
		  	contents = httpClient.GET(uri.toASCIIString());
		  	msg = contents.getContentAsString().trim();
		  	if(contents.getStatus()==400)System.out.println("ERROR : " + msg);
		  	else if(msg!=null&&!msg.isEmpty()&&!msg.startsWith("999 "))System.out.println("EDITED FROM JAVA CLIENT : " + msg);  	
			break;
		case "showlist":
			httpClient.start();
		  	uri = new URI(method, "localhost:"+port, "/showList",null,null);	
		  	contents = httpClient.GET(uri.toASCIIString());
		  	msg = contents.getContentAsString().trim();
		  	if(contents.getStatus()==400)System.out.println("ERROR : " + msg);
		  	else if(msg!=null&&!msg.isEmpty()&&!msg.startsWith("999 "))System.out.println("GOT FROM JAVA CLIENT : " + msg);  	
			break;	
		case "killloop":
			httpClient.start();
		  	uri = new URI(method, "localhost:"+port, "/killLoop",null,null);	
		  	contents = httpClient.GET(uri.toASCIIString());
		  	msg = contents.getContentAsString().trim();
		  	if(contents.getStatus()==400)System.out.println("ERROR : " + msg);
		  	else if(msg!=null&&!msg.isEmpty()&&!msg.startsWith("999 "))System.out.println("LOOP KILLED FROM JAVA CLIENT : " + msg);  	
			break;
		case "startloop":
			httpClient.start();
		  	uri = new URI(method, "localhost:"+port, "/startLoop",null,null);	
		  	contents = httpClient.GET(uri.toASCIIString());
		  	msg = contents.getContentAsString().trim();
		  	if(contents.getStatus()==400)System.out.println("ERROR : " + msg);
		  	else if(msg!=null&&!msg.isEmpty()&&!msg.startsWith("999 "))System.out.println("LOOP STARTED FROM JAVA CLIENT : " + msg);  	
			break;
		case "deletejob":
			if(args.length<2||args[1]==null)break;
		  	httpClient.start();
		  	uri = new URI(method, "localhost:"+port, "/deleteJob", "path="+args[1], null);	
		  	contents = httpClient.GET(uri.toASCIIString());
		  	msg = contents.getContentAsString().trim();
		  	if(contents.getStatus()==400)System.out.println("ERROR : " + msg);
		  	else if(msg!=null&&!msg.isEmpty()&&!msg.startsWith("999 "))System.out.println("DELETED FROM JAVA CLIENT : " + msg);  	
			break;	
		case "infojob":
			if(args.length<2||args[1]==null)break;
		  	httpClient.start();
		  	uri = new URI(method, "localhost:"+port, "/infoJob", "path="+args[1], null);	
		  	contents = httpClient.GET(uri.toASCIIString());
		  	msg = contents.getContentAsString().trim();
		  	if(contents.getStatus()==400)System.out.println("ERROR : " + msg);
		  	else if(msg!=null&&!msg.isEmpty()&&!msg.startsWith("999 "))System.out.println("GOT FROM JAVA CLIENT : " + msg);  	
			break;	
		default : System.out.println("UNKNOWN ORDER.");
		}
		httpClient.stop();
		
		
	}
}
