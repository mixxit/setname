package net.gamerservices.setname;


import java.io.FileOutputStream;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetNameCommand implements CommandExecutor {
	private final setname plugin;
	
	public SetNameCommand(setname plugin) {
        this.plugin = plugin;
    }
	
	 @Override
	    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
	        if (!(sender instanceof Player)) {
	            return false;
	        }
	        
	        if (!sender.isOp())
	        {
	        	// only ops can use
	        	return false;
	        }
	        Player player = (Player) sender;
	        
	        

	        if (split.length == 0 || split.length == 1) {
	        	player.sendMessage("Incorrect syntax: /setname accountname newname");
	        	return false;
	        }

	        
	        Player targetPlayer = this.plugin.getServer().getPlayer(split[0]);
	        
	        try {
	        	//player.sendMessage("Found player: " +  targetPlayer.getName());
	        	targetPlayer.setDisplayName(split[1]);
	        	this.plugin.getServer().broadcastMessage(split[0] + " shall be hence forth known as " + split[1]);
	        	FileOutputStream out = new FileOutputStream(plugin.PlayerNames); 
	        	plugin.prop.setProperty(player.getName(),split[1]);
	        	plugin.prop.store(out,"Do NOT edit this config!");
				out.flush();  
				out.close();
	        	
	        	return true;
	        } catch (Exception e)
	        {
	        	player.sendMessage("Could not find player account name: " + split[0]);
	        }

	        
	        // else return false
	        return false;
	    }
	
}
