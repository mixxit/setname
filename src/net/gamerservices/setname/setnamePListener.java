package net.gamerservices.setname;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Handle events for all Player related events
 * @author Mixxit
 */
public class setnamePListener extends PlayerListener {
    private final setname plugin;

    public setnamePListener(setname instance) {
        plugin = instance;
    }
    
    public void onPlayerJoin(PlayerEvent event){
    	Player player = event.getPlayer();
    	try
    	{
    		String savedname = plugin.prop.getProperty(player.getName());
    		player.setDisplayName(savedname);
    	} catch (Exception e)
    	{
    		e.printStackTrace(); //explained below.
    	}
    }

    //Insert Player related code here
}
