package SupportBot.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import SupportBot.SupportBot;

public class SupportListener implements Listener{

	private SupportBot plugin;
	public SupportListener(SupportBot plugin)
	{
		this.plugin = plugin;
	}
	
	
	
	//MESSAGE, PLAYER, SERVERIP
	
	//INSERT INTO SupportBot (`MESSAGE`, `PLAYER`, `SERVERIP`) VALUES('" message + "', '" + player.getName() "'"+ ", '"+ PASSWORD +"', '" + player.getName() +"');");
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
	
		Player p = e.getPlayer();
		if(e.getMessage().toLowerCase().startsWith("@supportbot"))
		{
			e.setCancelled(true);
			String message = e.getMessage();
			message = ChatColor.translateAlternateColorCodes('&', message);
			String spielernachricht = ChatColor.GRAY + p.getName() + ChatColor.DARK_GRAY + " >> " + ChatColor.GRAY + message;
			spielernachricht = spielernachricht.replace("@SupportBot", "");
			p.sendMessage(spielernachricht);
			message = message.toLowerCase();
			if(e.getMessage().toLowerCase().contains("report") || e.getMessage().toLowerCase().contains("hacker"))
			{
				
				plugin.getMySQL().updateInformation(e.getPlayer(), e.getMessage(), plugin.ServerIp, "To report a Player use /report.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "To report a Player use /report.");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(e.getMessage().toLowerCase().contains("admin"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Im sorry, i havent got a answer for that.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im sorry, i havent got a answer for that.");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(e.getMessage().toLowerCase().contains("plugins"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Im sorry I cant give you Details on that.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im sorry I cant give you Details on that.");
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("no problem"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Yay :)!");
				new BukkitRunnable() {
	
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Yay :)!");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(e.getMessage().toLowerCase().contains("bukkit") || message.contains("spigot") || message.contains("version"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "As far as my System goes this Server is using Spigot {version}");
				new BukkitRunnable() {
	
					@Override
					public void run() {
						String version = Bukkit.getServer().getVersion();
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "As far as my System goes this Server is using Spigot " + version);
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.equalsIgnoreCase("how are you doing"))
			{
				if(e.getMessage().toLowerCase().contains("hello") || message.contains("hey") || message.contains("hallo") || message.contains("ola"))
				{
					plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Im fine, thank you for asking!");
					new BukkitRunnable() {
						
						@Override
						public void run() {
							p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im fine, thank you for asking!" );
							
						}
					}.runTaskLaterAsynchronously(plugin, 30);
					
					return;
				}
			}
			if(message.contains("%%configuration%%")){
				if(p.hasPermission("supportbot.showconfiguration"))
				{
					String config = plugin.getConfig().getString("SupportBot.Server.Type");
					String locale = plugin.getConfig().getString("SupportBot.Language.Locale");
					String version = plugin.getDescription().getVersion();
					if(locale.equalsIgnoreCase("en"))
					{
						locale = "English";
					}
					else
					{
						
					}
					p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im currently using the '" + ChatColor.GRAY +config  + ChatColor.GOLD + "' config"+ " with the Locale: " + ChatColor.GRAY + locale + ChatColor.DARK_GRAY + " Bot_Version_" + version);
				}
				else
				{
					p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im sorry, I cant show you my Configuration." );
				}
				return;
			}
			if(message.contains("dos"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "A quick Google research gave out that DoS-Attacks arent nice.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "A quick Google research gave out that DoS-Attacks arent nice." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("shutdown") || message.contains("kill"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "If you dont like me, leave me alone please.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "If you dont like me, leave me alone please." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.equalsIgnoreCase("?"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Cant help you out there, Im sorry.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Cant help you out there, Im sorry." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("op"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "I cant give you any permissions, ask the Owner.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "I cant give you any permissions, ask the Owner." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("staff")){
				
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "The Staffteam can be found on our Website.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "The Staffteam can be found on our Website." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("spawn"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "You can get to the Spawn with /spawn!");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "You can get to the Spawn with /spawn!" );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("commands"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "@SupportBot isnt using Commands.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "@SupportBot isnt using Commands." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("type") && message.contains("server"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "The Owner tells me that the Server is an {serverType} Server!");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "The Owner tells me that the Server is an " +plugin.getConfig().getString("SupportBot.Server.Type") + " Server!" );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("stop") && message.contains("server"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "I could stop it, but im a nice person.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "I could stop it, but im a nice person." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("what") && message.contains("do"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "You could do a Jump n' Run or other Minigames.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "You could do a Jump n' Run or other Minigames." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("%%feedback%%"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Thank you for the feedback.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Thank you for the feedback.");
					}
				}.runTaskLaterAsynchronously(plugin, 30);
			}
			if(message.contains("feedback"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "If you want to leave Feedback just Type: @SupportBot %%feedback%% {YourMcVersion}, {YourMcName}, {FeedBack}\n note, that is not sensitive and you can do less.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "If you want to leave Feedback just Type: @SupportBot %%feedback%% {YourMcVersion}, {YourMcName}, {FeedBack}\n note, that is not sensitive and you can do less.");
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			
			if(message.contains("owner"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "I dont know :(");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "I dont know :(");
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("creator") || message.contains("created"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "A guy called 'GoRoK' created me");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "A guy called 'GoRoK' created me");
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("kit"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "If you want to get a Kit use /kit");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "If you want to get a Kit use /kit");
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("my") && message.contains("name"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Let me guess... Your name is {playerName} am I right?");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Let me guess... Your name is " + p.getName()  + " am I right?");
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("fuck"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Im not gonna answer that, please use nicer words.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im not gonna answer that, please use nicer words." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("leave"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "If you want to leave the Server then press the ESC Button and click Disconnect.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "If you want to leave the Server then press the ESC Button and click Disconnect." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("apply"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "If you want to apply please use our Forums.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "If you want to apply please use our Forums." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("scam"))
			{
				
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Im sorry, I cant help you with Scamming... You should probably contact the police.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im sorry, I cant help you with Scamming... You should probably contact the police." );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("help"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Hey {player}, how are you?");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im here to help, give me more Information!" );
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(e.getMessage().toLowerCase().contains("hello") || message.contains("hey") || message.contains("hallo") || message.contains("ola"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Hey {player}, how are you?");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Hey " + ChatColor.GRAY + e.getPlayer().getName() + ChatColor.GOLD + ", how are you doing?");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(e.getMessage().toLowerCase().contains("cool"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Yup.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Yup.");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("learning") || message.contains("learn"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Im learning out of your answers! I've already studied x asnwers");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						int i = plugin.getMySQL().getKnowledge();
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im learning out of your answers! I've already studied " +ChatColor.GRAY +  i + ChatColor.GOLD + " answers!");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("i dont understand"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Sorry for my bad answer!");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Sorry for my bad answer!");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(e.getMessage().toLowerCase().contains("good") || message.contains("im fine")){
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Great to hear!");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Great to hear!");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(e.getMessage().toLowerCase().contains("thank you") || e.getMessage().toLowerCase().contains("thanks"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "No problem!");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "No problem!");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(e.getMessage().toLowerCase().contains("bad"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Tell me more about it");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Tell me more about it.");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("asshole") || message.contains("whore") || message.contains("ass") || message.contains("bastard"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Im sorry that I couldn't fit your needs!");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im sorry that I couldn't fit your needs!");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("not fine"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Im sorry to hear that, whats going on?");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im sorry to hear that, whats going on?");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			if(message.contains("network"))
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Im not too sure about this.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im not too sure about this.");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			else
			{
				plugin.getMySQL().updateInformation(p, e.getMessage(), plugin.ServerIp, "Im sorry! I dont understand.");
				new BukkitRunnable() {
					
					@Override
					public void run() {
						p.sendMessage(ChatColor.GRAY + "@SupportBot | " + ChatColor.GOLD + "Im sorry! I dont understand.");
						
					}
				}.runTaskLaterAsynchronously(plugin, 30);
				
				return;
			}
			
		}
		else
		{
			e.setCancelled(false);
			return;
		}
	}
}
