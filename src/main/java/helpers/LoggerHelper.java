package helpers;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import utility.ResourceHelper;

/**
 * @author Bhanu Pratap
 * https://www.youtube.com/user/MrBhanupratap29/playlists
 */
@SuppressWarnings("rawtypes")
public class LoggerHelper {

	private static boolean root = false;

	public static Logger getLogger(Class clas){
		if (root) {
			return Logger.getLogger(clas);
		}
		//String log4jLOcation = System.getProperty("user.dir")+"/src/main/resources/log4j.properties";
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("/log4j.properties"));
		root = true;
		return Logger.getLogger(clas);
	}
}
