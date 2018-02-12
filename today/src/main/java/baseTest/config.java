package baseTest;

import java.util.Properties;

public class config extends baseTestClass {
	
private Properties OR;
	
	public config(Properties OR){
		this.OR = OR;
	}

	public String getBrowser()
	{
		return OR.getProperty("Browser");
	}
	
	public String getUserName()
	{
		return OR.getProperty("Username");
	}
	
	public String getPassword()
	{
		return OR.getProperty("Password");
	}
	
	public String getWebsite()
	{
		return OR.getProperty("Website");
	}
	
	public int getPageLoadtimeOut()
	{
		
		return Integer.parseInt(OR.getProperty("PageLoadTimeOut"));
	}
	
	public int getImplicitWait()
	{
		return Integer.parseInt(OR.getProperty("ImplicitWait"));
	}
	
	public int getExplicitWait()
	{
		return Integer.parseInt(OR.getProperty("ExplicitWait"));
	}
	
	public String getDbType()
	{
		return OR.getProperty("DataBase.Type");
	}
	
	public String getDbConnStr()
	{
		return OR.getProperty("DataBase.ConnectionStr");
	}

	
}
