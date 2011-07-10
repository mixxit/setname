package net.gamerservices.setname;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

/**
 * SetName plugin for Bukkit
 *
 * @author Mixxit
 */
public class setname extends JavaPlugin {
    private final setnamePListener playerListener = new setnamePListener(this);
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    static String mainDirectory = "plugins/setname"; //sets the main directory for easy reference
    static File PlayerNames = new File(mainDirectory + File.separator + "PlayerNames.dat");
    static Properties prop = new Properties(); //creates a new properties file
    
    // NOTE: There should be no need to define a constructor any more for more info on moving from
    // the old constructor see:
    // http://forums.bukkit.org/threads/too-long-constructor.5032/

    public void onDisable() {
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        System.out.println("[SetName] Disabled!");
    }

    public void loadProcedure() { 
    	try
    	{
    		FileInputStream in = new FileInputStream(PlayerNames); //Creates the input stream
    		prop.load(in); //loads the file contents
    	} catch (Exception e)
    	{
    		 System.out.println("[SetName] FAILED TO LOAD PLAYER NAMES!");
    		 e.printStackTrace(); //explained below.

    	}
    	
    }
    
    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events
    	new File(mainDirectory).mkdir();
    	if(!PlayerNames.exists()){ 
    		try {
    			PlayerNames.createNewFile(); 
				FileOutputStream out = new FileOutputStream(PlayerNames); 
				prop.put("PlayerCount", "0"); //put the property ZoneCount with a value of 0 into the properties file, this will show up as ZoneCount=0 in the properties file.
				prop.store(out, "Do NOT edit this config!"); //You need this line! It stores what you just put into the file and adds a comment.
				out.flush();  
				out.close();
			} catch (IOException ex) { 
				ex.printStackTrace(); //explained below.
			}
    		
    	} else { 
			loadProcedure();
		} 
        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Normal, this);


        // Register our commands
        getCommand("setname").setExecutor(new SetNameCommand(this));
        getCommand("checkname").setExecutor(new CheckNameCommand(this));
        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
}
