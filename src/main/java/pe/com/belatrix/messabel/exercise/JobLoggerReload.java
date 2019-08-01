package pe.com.belatrix.messabel.exercise;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.com.belatrix.messabel.exercise.service.MessageService;
import pe.com.belatrix.messabel.exercise.util.ValidatorMessage;

public class JobLoggerReload {

	// Tipos de mensaje
	private static boolean B_TO_FILE;
	private static boolean B_TO_CONSOLE;
	private static boolean B_TO_DATABASE;

	// Nivel de mensajes
	private static boolean B_LOG_MESSAGE;
	private static boolean B_LOG_WARNING;
	private static boolean B_LOG_ERROR;

	private static Map PARAMETERS_DB;

	private static Logger LOG;
	private static DateFormat FORMAT_DATE = DateFormat.getDateInstance(DateFormat.LONG);

	public JobLoggerReload(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
			boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, Map dbParamsMap) {
		LOG = Logger.getLogger("MyLog");

		B_LOG_MESSAGE = logMessageParam;
		B_LOG_ERROR = logErrorParam;
		B_LOG_WARNING = logWarningParam;

		B_TO_DATABASE = logToDatabaseParam;
		B_TO_FILE = logToFileParam;
		B_TO_CONSOLE = logToConsoleParam;

		PARAMETERS_DB = dbParamsMap;
	}

	public static void LogMessage(String messageText, boolean message, boolean warning, boolean error)
			throws Exception {
		if (messageText == null || messageText.length() == 0) {
			return;
		} else {
			messageText.trim();
		}
		if (!B_TO_CONSOLE && !B_TO_FILE && !B_TO_DATABASE) {
			throw new Exception("Invalid configuration");
		}
		if ((!B_LOG_ERROR && !B_LOG_MESSAGE && !B_LOG_WARNING) || (!message && !warning && !error)) {
			throw new Exception("Error or Warning or Message must be specified");
		}
		if (ValidatorMessage.validKeyParametersBD(PARAMETERS_DB) && B_TO_DATABASE) {
			throw new Exception("Invalid configuration for database mode");
		}
		if (ValidatorMessage.validKeyFolder(PARAMETERS_DB)) {
			throw new Exception("Invalid configuration for file folder");
		}
		String path = PARAMETERS_DB.get("logFileFolder").toString();
		if (!ValidatorMessage.validFolder(path)) {
			throw new Exception("Invalid path or does not exist");
		}
		int t = 0;
		if (message && B_LOG_MESSAGE) {
			t = 1;
		}
		if (error && B_LOG_ERROR) {
			t = 2;
		}
		if (warning && B_LOG_WARNING) {
			t = 3;
		}
		String l = null;
		File logFile = new File(path + "/logFile.txt");
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
		FileHandler fh = new FileHandler(path + "/logFile.txt");
		ConsoleHandler ch = new ConsoleHandler();
		Date today = new Date();
		if (error && B_LOG_ERROR) {
			l = l + "error " + FORMAT_DATE.format(today) + messageText;
		}
		if (warning && B_LOG_WARNING) {
			l = l + "warning " + FORMAT_DATE.format(today) + messageText;
		}
		if (message && B_LOG_MESSAGE) {
			l = l + "message " + FORMAT_DATE.format(today) + messageText;
		}
		if (B_TO_FILE) {
			LOG.addHandler(fh);
			LOG.log(Level.INFO, messageText);
		}
		if (B_TO_CONSOLE) {
			LOG.addHandler(ch);
			LOG.log(Level.INFO, messageText);
		}
		if (B_TO_DATABASE) {
			MessageService serviceMessage = new MessageService();
			serviceMessage.insertMessage(messageText, t);
		}
	}
}