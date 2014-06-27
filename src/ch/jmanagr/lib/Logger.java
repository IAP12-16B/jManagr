package ch.jmanagr.lib;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.bo.Department;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Logger class Far from perfect
 */
public class Logger
{
	private static void unifiedLog(PrintStream outputStream, String message)
	{
		outputStream.print(message);
	}

	private static String formatStackTrace(StackTraceElement[] stack)
	{
		String traceString = "";
		for (StackTraceElement stackTraceElement : stack) {
			traceString += String.format("\t%s\n", stackTraceElement.toString());
		}
		return traceString;
	}

	private static String formatMessage(Throwable e, String adidionalInfo)
	{

		String suppressed = "";
		for (Throwable throwable : e.getSuppressed()) {
			suppressed += formatMessage(throwable);
		}

		return String.format(
				"%s\nReason: \t%s \nMessage: \t%s \nLocalized:\t%s \nTrace: \n%s\n\nSuppressed: \n%s\n",
				adidionalInfo,
				e.getCause(),
				e.getMessage(),
				e.getLocalizedMessage(),
				formatStackTrace(e.getStackTrace()),
				suppressed
		);
	}

	private static String formatMessage(Throwable e)
	{
		return formatMessage(e, "");
	}

	public static void log(LOG_LEVEL level, String message)
	{
		message = String.format("%s: \t\t%s", level.toString(), message);
		switch (level) {
			case VERBOSE:
				logVerbose(message + "\n");
				break;
			case DEBUG:
				logDebug(message + "\n");
				break;
			case MESSAGE:
				logMessage(message + "\n");
				break;
			case WARNING:
				logWarning(message + "\n");
				break;
			case ERROR:
				logError(message + "\n");
				break;
			case FATAL_ERROR:
				logFatalError(message + "\n");
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

	public static void log(HashMap<?, ?> map)
	{
		Iterator<? extends Map.Entry<?, ?>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = it.next();
			log(pairs.getKey());
			log(": \n\t");
			logln(pairs.getValue());
			it.remove();
		}
	}

	public static void log(List list)
	{
		for (Object o : list) {
			log("\t");
			logln(o);
		}
	}

	public static void log(String[] array)
	{
		for (String s : array) {
			log("\t");
			logln(s);
		}

	}

	public static void log(Object[] obj)
	{
		for (Object o : obj) {
			log("\t");
			logln(o);
		}

	}

	public static void log(Integer[] array)
	{
		for (Integer integer : array) {
			logln(integer);
		}

	}

	public static void log(Class cls)
	{
		log(cls.getName());
	}

	public static void log(boolean b)
	{
		log(String.valueOf(b));
	}

	public static void log(Enum value)
	{
		log(value.toString());
	}

	public static void log(Integer i)
	{
		log(i.toString());
	}

	public static void log(Object o)
	{
		if (o == null) {
			log("[null]");
		} else {
			log(String.valueOf(o));
		}
	}

	public static void log(BusinessObject bo)
	{
		logln(
				String.format(
						"BusinessObject %s: \n\t ID: %d \n\t active: %b \n\t deleted: %b",
						bo.getClass(),
						bo.getId(),
						bo.getActive(),
						bo.getDeleted()
				)
		);
	}

	public static void log(Department department)
	{
		log((BusinessObject) department);
		logln(
				String.format(
						"\t Name: %s",
						department.getName()
				)
		);
		logln(department.getAgents());
	}

	public static void log()
	{
		log("\n");
	}

	public static void log(String string)
	{
		logMessage(string);
	}

	/**
	 * Logs to a new line
	 *
	 * @param m
	 * @param <T>
	 */
	public static <T> void logln(T m)
	{
		log(m);
		log();
	}
}
