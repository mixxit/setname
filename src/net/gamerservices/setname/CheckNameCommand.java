package net.gamerservices.setname;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CheckNameCommand implements CommandExecutor {
	private final setname plugin;
	
	public CheckNameCommand(setname plugin) {
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
	        
	        

	        if (split.length == 0) {
	        	player.sendMessage("Incorrect syntax: /checkname accountname");
	        	return false;
	        }

	        
	        Player targetPlayer = this.plugin.getServer().getPlayer(split[0]);
	        
	        try {
	        	//player.sendMessage("Found player: " +  targetPlayer.getName());
	        	String name = targetPlayer.getDisplayName();
	        	player.sendMessage(name);
	        	return true;
	        } catch (Exception e)
	        {
	        	player.sendMessage("Could not find player: " + split[0]);
	        	
	        }

	        
	        // else return false
	        return false;
	    }
	
}
