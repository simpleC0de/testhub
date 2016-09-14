package SupportBot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

import org.bukkit.scheduler.BukkitRunnable;

public class MySQLClan {

	private Connection conn;
	private SupportBot plugin;
	private String hostname;
	private String user;
	private String password;
	private String database;
	private int port;
	public MySQLClan(SupportBot plugin) throws Exception
	{
		
		this.plugin = plugin;
		
		this.hostname = "kry0-productions.lima-db.de";
		this.port = 3306;
		this.user = "USER348308";
		this.password = "XTeEL39Wm";
		this.database = "db_348308_2";
		this.openConnection();
		
	}
	public Connection openConnection() throws Exception
	{
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+hostname + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&useUnicode=true&characterEncoding=UTF-8");
			this.conn = conn;
			return conn;
	
	
	}
	public Connection getConnection()
	{
		return this.conn;
	}
	public boolean hasConnection()
	{
		try {
			return this.conn != null || this.conn.isValid(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public int receivedPayment(Player p)
	{
		try {
			if(getConnection().isValid(2000))
			{
				PreparedStatement st = null;
				Connection conn = getConnection();
				ResultSet rs = null;
				st = conn.prepareStatement("SELECT * from payment WHERE UUID = '" + p.getUniqueId().toString() + "';");
				rs = st.executeQuery();
				rs.next();
				int i = rs.getInt("GOT");
				return i;
			}
			else
			{
				try {
					openConnection();
					PreparedStatement st = null;
					Connection conn = getConnection();
					ResultSet rs = null;
					st = conn.prepareStatement("SELECT * from payment WHERE UUID = '" + p.getUniqueId().toString() + "';");
					rs = st.executeQuery();
					rs.next();
					int i = rs.getInt("GOT");
					return i;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public void closeRessources(ResultSet rs, PreparedStatement st)
	{
		if(rs != null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				
			}
		}
		if(st != null)
		{
			try {
				st.close();
			} catch (SQLException e) {
				
			}
		}
	}
	
	
	public void closeConnection()
	{
		try {
			this.conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally
		{
			this.conn = null;
		}
		
	}
	public void queryUpdate(String query)
	{
		new BukkitRunnable() {
			
			@Override
			public void run() {
				try {
					if(getConnection().isValid(2000))
					{
						PreparedStatement st = null;
						
						Connection conn = getConnection();
						try {
							 st = conn.prepareStatement(query);
							 st.executeUpdate();
						} catch (SQLException e) {
							System.err.println("Failed to send update '" + query + "'.");
							e.printStackTrace();
						}finally
						{
							closeRessources(null, st);
						}
					}
					else
					{
						try {
							openConnection();
							PreparedStatement st = null;
							
							Connection conn = getConnection();
							try {
								 st = conn.prepareStatement(query);
								 st.executeUpdate();
							} catch (SQLException e) {
								System.err.println("Failed to send update '" + query + "'.");
								e.printStackTrace();
							}finally
							{
								closeRessources(null, st);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		}.runTaskAsynchronously(plugin);
		
	}
	
	public void updateInformation(Player player, String message, String ServerIp, String Botanswer)
	{
		message = message.replace("'", "");
		message = message.replace("`", "");
		message = message.replace("@SupportBot", "");
		message = message.replace("(", "");
		message = message.replace(")", "");
		message = message.replace(";", "");
		message = message.replace(",", "");
		message = message.replace("`", "");
		
		Botanswer = Botanswer.replace("'", "");
		Botanswer = Botanswer.replace("`", "");
		Botanswer = Botanswer.replace("(", "");
		Botanswer = Botanswer.replace(")", "");
		Botanswer = Botanswer.replace(";", "");
		Botanswer = Botanswer.replace(",", "");
		Botanswer = Botanswer.replace(".", "");
		try {
			if(getConnection().isValid(2000))
			{
				
				plugin.getMySQL().queryUpdate("INSERT INTO SupportBot (`MESSAGE`, `PLAYER`, `SERVERIP`, `BOTANSWER`) VALUES('"+ message + "', '" + player.getName() + "', '" + plugin.ServerIp +"', '"  + Botanswer + "'" + ");");
			}
			else
			{
				try {
					openConnection();
					plugin.getMySQL().queryUpdate("INSERT INTO SupportBot (`MESSAGE`, `PLAYER`, `SERVERIP`, `BOTANSWER`) VALUES('"+ message + "', '" + player.getName() + "', '" + plugin.ServerIp +"', '"  + Botanswer + "'" + ");");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getKnowledge()
	{
		try
		{
			int numberRow = 0;
			if(getConnection().isValid(2000))
			{
				Connection conn = getConnection();
				ResultSet rs = null;
				String query = "select count(*) from SupportBot";
			    PreparedStatement st = conn.prepareStatement(query);
			    rs = st.executeQuery();
			    while(rs.next()){
			        numberRow = rs.getInt("count(*)");
			    }
			    return numberRow;
			}
			else
			{
				openConnection();
				Connection conn = getConnection();
				ResultSet rs = null;
				String query = "select count(*) from dataTable";
			    PreparedStatement st = conn.prepareStatement(query);
			    rs = st.executeQuery();
			    while(rs.next()){
			        numberRow = rs.getInt("count(*)");
			    }
			    return numberRow;
			}
		}
		catch(Exception ex)
		{
			return -1;
		}
		
		
		
	}
			
		
		
		
}
	
	
	
	

