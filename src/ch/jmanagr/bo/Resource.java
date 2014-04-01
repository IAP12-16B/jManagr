package ch.jmanagr.bo;


import java.util.ArrayList;
import java.util.HashMap;

public class Resource implements BusinessObject
{
	private int id;
	private String name;
	private HashMap<String, String> data;
	private ArrayList<Ticket> tickets;
	private Resource parent;
	private ArrayList<Resource> children;
}
