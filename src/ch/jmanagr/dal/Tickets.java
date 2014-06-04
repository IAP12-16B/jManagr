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
		Integer resource_id = null;
		Integer agent_id = null;
		Integer department_id = null;
		Integer user_id = null;

		// save referenced objs -> order is important
		if (bo.getDepartment() != null) {
			Departments.getInstance().save(bo.getDepartment());
			department_id = bo.getDepartment().getId();
		}
		if (bo.getUser() != null) {
			Users.getInstance().save(bo.getUser());
			user_id = bo.getUser().getId();
		}
		if (bo.getAgent() != null) {
			Users.getInstance().save(bo.getAgent());
			agent_id = bo.getAgent().getId();
		}
		if (bo.getResource() != null) {
			Resources.getInstance().save(bo.getResource());
			resource_id = bo.getResource().getId();
		}

		q = super.beforeSave(q, bo);

		q.addParameter("resource_id", resource_id);
		q.addParameter("agent_id", agent_id);
		q.addParameter("department_id", department_id);
		q.addParameter("user_id", user_id);

		return q;
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
		if (ticket.getResource() == null) {
			ticket.setResource(
					this.db.resolveRelation(ticket, Resources.getInstance(), "Resource")
			);
		}

		if (ticket.getAgent() == null) {
			ticket.setAgent(
					this.db.resolveRelation(ticket, Users.getInstance(), "Agent")
			);
		}

		if (ticket.getUser() == null) {
			ticket.setUser(
					this.db.resolveRelation(ticket, Users.getInstance(), "User")
			);
		}

		if (ticket.getDepartment() == null) {
			ticket.setDepartment(
					this.db.resolveRelation(ticket, Departments.getInstance(), "Department")
			);
		}
	}
}
