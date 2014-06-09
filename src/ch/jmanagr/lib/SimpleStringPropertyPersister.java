package ch.jmanagr.lib;


import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.DateType;
import javafx.beans.property.SimpleStringProperty;

public class SimpleStringPropertyPersister extends DateType
{
	private static final SimpleStringPropertyPersister singleTon = new SimpleStringPropertyPersister();

	private SimpleStringPropertyPersister()
	{
		super(SqlType.STRING, new Class<?>[]{SimpleStringProperty.class});
	}

	public static SimpleStringPropertyPersister getSingleton()
	{
		return singleTon;
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object javaObject)
	{
		SimpleStringProperty string = (SimpleStringProperty) javaObject;
		return string.getValue();
	}

	@Override
	public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos)
	{
		return new SimpleStringProperty((String) sqlArg);
	}
}
