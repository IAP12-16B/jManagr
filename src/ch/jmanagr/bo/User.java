package ch.jmanagr.bo;

import ch.jmanagr.lib.USER_ROLE;

public class User implements BusinessObject
{
	protected int id;
	protected String firstname;
	protected String lastname;
	protected String username;
	protected String password;
	protected USER_ROLE role;
}
