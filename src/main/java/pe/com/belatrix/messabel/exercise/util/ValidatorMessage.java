package pe.com.belatrix.messabel.exercise.util;

import java.io.File;
import java.util.Map;

public class ValidatorMessage {

	public static boolean validKeyParametersBD(Map PARAMETERS_DB) {
		if (PARAMETERS_DB.containsKey("userName") && PARAMETERS_DB.containsKey("password")
				&& PARAMETERS_DB.containsKey("dbms") && PARAMETERS_DB.containsKey("serverName")
				&& PARAMETERS_DB.containsKey("portNumber")) {
			return false;
		}
		return true;
	}

	public static boolean validKeyFolder(Map PARAMETERS_DB) {
		if (PARAMETERS_DB.containsKey("logFileFolder")) {
			return false;
		}
		return true;
	}

	public static boolean validFolder(String path) {
		File f = new File(path);
		if (f.exists() && f.isDirectory()) {
			return true;
		}
		return false;
	}
}
