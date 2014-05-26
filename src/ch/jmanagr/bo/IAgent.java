package ch.jmanagr.bo;


public interface IAgent extends IUser
{
	public Department getDepartment();

	public void setDepartment(Department department);
}
