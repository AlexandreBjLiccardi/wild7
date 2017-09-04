
package fr.wild.real;
import fr.wild.common.IoCommons;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;

import static fr.wild.common.IoWilds.config_getTab;

/**
 * Test de manipulation de la liste de job quartz.
 *
 */
public class WildSenderServiceLooper implements Callable {
	private final HashMap<String, Object> diceConfig = config_getTab() ; 
	private final File testDir = new File((String)diceConfig.get("qWorkspace_path")+"/"+(String)diceConfig.get("qLoop_path"));
	private final String killerPath = (String)diceConfig.get("qWorkspace_path")+"/"+(String)diceConfig.get("qConfig_path")+"/"+(String)diceConfig.get("qConfig_killer");
	private final Integer port = (Integer)diceConfig.get("qConfig_port");

	@Override
	public ConcurrentHashMap<String,Object> call() throws Exception {
		HashMap<String,Object> toRet = new HashMap() ;  
		
		while(true){
		  	   final File testKiller = new File(killerPath);
		  	   if(testKiller.exists()){
		  		   System.out.println("Met the KILLER FLAG in config directory.");
		  		   testKiller.delete();
		  		   break;
		  	   }
		  	   File[] listTests = testDir.listFiles();
			     for(File f:listTests){
					  	HttpClient httpClient = new HttpClient();
					  	httpClient.setFollowRedirects(false);
					  	httpClient.start();
					  	URI uri = new URI("http", "localhost:"+port, "/addJobFromLoop", "path="+f.getName(), null);	
					  	ContentResponse contents = httpClient.GET(uri.toASCIIString());
					  	String msg = contents.getContentAsString().trim();
					  	if(contents.getStatus()==400)System.out.println("ERROR : " + msg);
					  	else if(msg!=null&&!msg.isEmpty()&&!msg.startsWith("999 "))System.out.println("ADDED FROM DIRECTORY : " + msg);
					  	httpClient.stop();
			     }
			       Thread.sleep((Integer) diceConfig.get("system_jloop_delay"));
		     }
		return IoCommons.cast_Map2ConcurrentHashMap(toRet);
	}
	public static String get(String url) throws IOException{
			String source ="";
			URL ur1 = new URL(url);
			URLConnection yc = ur1.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
			source +=inputLine;
			in.close();
			return source;
		}
}
