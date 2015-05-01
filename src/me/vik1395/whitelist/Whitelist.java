package me.vik1395.whitelist;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Whitelist extends JavaPlugin implements Listener, CommandExecutor
{
	
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("WhitelistPerm has successfully started!");
		getLogger().info("Created by Vik1395");
		saveDefaultConfig();
	}
	
	String allowed = getConfig().getString("Allowed");
	String kicked = getConfig().getString("Kick");
	
	@EventHandler
	public void onPlayerJoin(PlayerLoginEvent event)
	{
		Player p = event.getPlayer(); 
		if(!p.hasPermission("whitelist.allowed")) 
		{ 
			event.disallow(PlayerLoginEvent.Result.KICK_OTHER, format(kicked));
			p.kickPlayer(format(kicked));
		}
		else if(p.hasPermission("whitelist.allowed"))
		{
			p.sendMessage(format(allowed));
		}
	}
	
	private String format(String input){
		return ChatColor.translateAlternateColorCodes('&', input);
	}
}