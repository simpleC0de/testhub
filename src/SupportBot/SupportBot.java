package SupportBot;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import SupportBot.Listener.SupportListener;



public class SupportBot extends JavaPlugin {

	public String ServerIp = null;
	public String versionLink;
	MySQLClan sqlclan;
	public void onEnable()
	{
		ServerIp = this.getServer().getIp().toString();
		try
		{
			loadConfig();
			sqlclan = new MySQLClan(this);
			this.getServer().getPluginManager().registerEvents(new SupportListener(this), this);

			}catch(Exception ex)
		{
			
		}
		broadCast();
		System.out.println("[SupportBot] Successfully started all processes.");
		if(getConfig().getString("SupportBot.Server.Type").equalsIgnoreCase("pvp")){
			System.out.println("[SupportBot] Configuring for PvP-Server.Type");
			System.out.println("[SupportBot] Loaded the Configuration.");
			System.out.println("[SupportBot] Ready for an Adventure!");return;
		}
		if(getConfig().getString("SupportBot.Server.Type").equalsIgnoreCase("citybuild")){
			System.out.println("[SupportBot] Configuring for CityBuild-Server.Type");
			System.out.println("[SupportBot] Loaded the Configuration.");
			System.out.println("[SupportBot] Ready for an Adventure!");return;
		}
		if(getConfig().getString("SupportBot.Server.Type").equalsIgnoreCase("minigames")){
			System.out.println("[SupportBot] Configuring for MiniGames-Server.Type");
			System.out.println("[SupportBot] Loaded the Configuration.");
			System.out.println("[SupportBot] Ready for an Adventure!");return;
		}
		if(getConfig().getString("SupportBot.Server.Type").equalsIgnoreCase("factions")){
			System.out.println("[SupportBot] Configuring for Factions-Server.Type");
			System.out.println("[SupportBot] Loaded the Configuration.");
			System.out.println("[SupportBot] Ready for an Adventure!");return;
		}
		else
		{
			System.out.println("[SupportBot] I dont recognize this Type of Configuration");
			System.out.println("[SupportBot] Loading Standardconfiguration");
			System.out.println("[SupportBot] Loaded, Ready for an Adventure!");
			return;
		}
	}
	public void onDisable(){
		
	}
	
	public MySQLClan getMySQL()
	{
		return sqlclan;
	}
	public void reconnect()
	{
		new BukkitRunnable() {
			
			@Override
			public void run() {
				sqlclan.closeConnection();
				
				try {
					sqlclan.openConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}.runTaskTimerAsynchronously(this, 1200, 20*60*10);
	}
	public void loadConfig()
	{
		if(getDataFolder().exists()){
			return;
		}else{
			getConfig().options().header("SupportBot");
			getConfig().set("SupportBot.Server.Type", "PvP");
			getConfig().set("SupportBot.Language.Locale", "EN");
			saveConfig();
		}
	}
	public void broadCast()
	{
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Bukkit.broadcastMessage(ChatColor.GRAY + "@SupportBot" + ChatColor.DARK_GRAY + " >> " + ChatColor.GOLD + "Hello! Im the " + ChatColor.GRAY + "@SupportBot!" + ChatColor.GOLD + " If you need help dont hesitate to contact me(@SupportBot)");				
			}
		}.runTaskTimerAsynchronously(this, 20*30, 20*60*5);
	}
	
}
