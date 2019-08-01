package pe.com.belatrix.messabel.messabel;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pe.com.belatrix.messabel.exercise.JobLoggerReload;
import pe.com.belatrix.messabel.exercise.util.ValidatorMessage;

@SpringBootTest
public class MessabelApplicationTests {

	@Test
	public void contextLoads() {
		//Variable declaration		
		boolean logToFileParam = true, logToConsoleParam = true, logToDatabaseParam = true;
		boolean logMessageParam = true, logWarningParam = true,  logErrorParam = true;
		String path = "D://ADOLFO//PROYECTOS//TALLER_TESTING//WORKSPACE//BelatrixExercise";
		//Obtener desde properties
		Map dbParamsMap = new HashMap<>();
		dbParamsMap.put("userName","root");
		dbParamsMap.put("password","root");
		dbParamsMap.put("dbms","mysql");
		dbParamsMap.put("serverName","localhost");
		dbParamsMap.put("portNumber","3306");
		dbParamsMap.put("logFileFolder",path);
		JobLoggerReload demo = new JobLoggerReload(logToFileParam, logToConsoleParam, logToDatabaseParam, logMessageParam, logWarningParam, logErrorParam, dbParamsMap);
		
		String messageText = "Mensaje Demo Final";
		boolean message = false, warning = true, error = false;
		
		assertNotNull(dbParamsMap.get("userName"));
		assertNotNull(dbParamsMap.get("password"));
		assertNotNull(dbParamsMap.get("dbms"));
		assertNotNull(dbParamsMap.get("serverName"));
		assertNotNull(dbParamsMap.get("portNumber"));
		assertNotNull(dbParamsMap.get("logFileFolder"));
		
		assertFalse(ValidatorMessage.validKeyParametersBD(dbParamsMap));
		assertFalse(ValidatorMessage.validKeyFolder(dbParamsMap));
		assertTrue(ValidatorMessage.validFolder(path));
	}

	
}
