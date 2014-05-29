package ch.jmanagr.bo;


public interface IAgent<T extends BusinessObject> extends IUser<T>
{
	public Department getDepartment();

	public void setDepartment(Department department);
}
