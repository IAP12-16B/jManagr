package ch.jManagr.DAL;

import ch.jManagr.BO.BusinessObject;

import java.util.ArrayList;

public interface DataAccessLayer {
    public void saveBusinessObject(BusinessObject);
    public <T extends BusinessObject> ArrayList<T> fetchAllOfType(T bo);
}
