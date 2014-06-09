package ch.jmanagr.lib;


import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.DateType;
import javafx.beans.property.SimpleIntegerProperty;

public class SimpleIntegerPropertyPersister extends DateType
{
	private static final SimpleIntegerPropertyPersister singleTon = new SimpleIntegerPropertyPersister();

	private SimpleIntegerPropertyPersister()
	{
		super(SqlType.INTEGER, new Class<?>[]{SimpleIntegerProperty.class});
	}

	public static SimpleIntegerPropertyPersister getSingleton()
	{
		return singleTon;
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object javaObject)
	{
		SimpleIntegerProperty integr = (SimpleIntegerProperty) javaObject;
		return integr.getValue();
	}

	@Override
	public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos)
	{
		return new SimpleIntegerProperty((Integer) sqlArg);
	}
}
