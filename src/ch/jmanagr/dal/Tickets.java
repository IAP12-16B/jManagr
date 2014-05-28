package ch.jmanagr.dal;

import ch.jmanagr.bo.Ticket;
import org.sql2o.Query;

import java.util.HashMap;

public class Tickets extends AbstractDAL<Ticket>
{
	private static Tickets instance;

	private Tickets()
	{
		super(Ticket.class);
	}

	public static Tickets getInstance()
	{
		if (instance == null) {
			synchronized (Tickets.class) {
				if (instance == null) {
					instance = new Tickets();
				}
			}
		}
		return instance;
	}

	@Override
	protected HashMap<String, String> getSaveFields()
	{
		HashMap<String, String> fields = new HashMap<>();
		fields.put("id", "id");
		fields.put("name", "name");
		fields.put("description", "description");
		fields.put("status", "status");
		fields.put("date", "date");
		fields.put("Resource", "resource_id");
		fields.put("Agent", "agent_id");
		fields.put("Department", "department_id");
		fields.put("User", "user_id");
		fields.put("active", "active");
		fields.put("deleted", "deleted");
		return fields;
	}

	@Override
	protected Query beforeSave(Query q, Ticket bo)
	{
		return super.beforeSave(q, bo)
		            .addParameter("resource_id", bo.getResource().getId())
		            .addParameter("agent_id", bo.getAgent().getId())
		            .addParameter("department_id", bo.getDepartment().getId())
		            .addParameter("user_id", bo.getUser().getId());
	}

	@Override
	protected HashMap<String, String> getFetchFields()
	{
		HashMap<String, String> fields = new HashMap<>();
		fields.put("id", "id");
		fields.put("name", "name");
		fields.put("description", "description");
		fields.put("status", "status");
		fields.put("date", "date");
		fields.put("active", "active");
		fields.put("deleted", "deleted");
		return fields;
	}

	@Override
	protected void afterFetch(Ticket ticket)
	{
		super.afterFetch(ticket);
		ticket.setResource(
				this.db.resolveRelation(ticket, Resources.getInstance(), "Resource")
		);

		ticket.setAgent(
				this.db.resolveRelation(ticket, Users.getInstance(), "Agent")
		);

		ticket.setUser(
				this.db.resolveRelation(ticket, Users.getInstance(), "User")
		);

		ticket.setDepartment(
				this.db.resolveRelation(ticket, Departments.getInstance(), "Department")
		);
	}
}
