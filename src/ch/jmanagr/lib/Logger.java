package ch.jmanagr.lib;


import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Logger
{
	private static void unifiedLog(PrintStream outputStream, String message)
	{
		outputStream.println(message);
	}

	private static String formatStackTrace(StackTraceElement[] stack)
	{
		String traceString = "";
		for (StackTraceElement stackTraceElement : stack) {
			traceString += String.format("\t%s\n", stackTraceElement.toString());
		}
		return traceString;
	}

	private static String formatMessage(Exception e, String adidionalInfo)
	{
		return String.format(
				"%s\nReason: %s \nMessage: %s \nTrace: \n%s\n", adidionalInfo, e.getCause(),
				e.getMessage(), formatStackTrace(e.getStackTrace())
		);
	}

	private static String formatMessage(Exception e)
	{
		return formatMessage(e, "");
	}

	public static void log(LOG_LEVEL level, String message)
	{
		message = String.format("%s: %s", level.toString(), message);
		switch (level) {
			case VERBOSE:
				logVerbose(message);
				break;
			case DEBUG:
				logDebug(message);
				break;
			case MESSAGE:
				logMessage(message);
				break;
			case WARNING:
				logWarning(message);
				break;
			case ERROR:
				logError(message);
				break;
			case FATAL_ERROR:
				logFatalError(message);
				break;
		}
	}

	public static void log(LOG_LEVEL level, Exception e)
	{
		log(level, formatMessage(e));
	}

	public static void log(LOG_LEVEL level, String adidionalInfo, Exception e)
	{
		log(level, formatMessage(e, adidionalInfo));
	}

	public static void logError(String message)
	{
		unifiedLog(System.err, message);
	}

	public static void logFatalError(String message)
	{
		unifiedLog(System.err, message);
	}

	public static void logWarning(String message)
	{
		unifiedLog(System.err, message);
	}

	public static void logMessage(String message)
	{
		unifiedLog(System.out, message);
	}

	public static void logDebug(String message)
	{
		unifiedLog(System.out, message);
	}

	public static void logVerbose(String message)
	{
		unifiedLog(System.out, message);
	}

	public static void log(HashMap<String, ?> map)
	{
		Iterator<? extends Map.Entry<String, ?>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = it.next();
			log(String.format("%s:\t %s\n", pairs.getKey(), pairs.getValue()));
			it.remove();
		}
	}

	public static void log(List list)
	{
		for (Object o : list) {
			log(o.toString() + "\n");
		}
	}

	public static void log(String[] array)
	{
		for (String s : array) {
			log(s + "\n");
		}

	}

	public static void log(Integer[] array)
	{
		for (Integer integer : array) {
			log(integer.toString() + "\n");
		}

	}

	public static void log(String string)
	{
		logMessage(string);
	}


}
