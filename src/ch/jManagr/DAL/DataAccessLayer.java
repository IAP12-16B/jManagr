package ch.jManagr.DAL;

import ch.jManagr.BO.BusinessObject;

import java.util.ArrayList;

public interface DataAccessLayer {
    public void saveBusinessObject(BusinessObject bo);
    public <T extends Class<? extends BusinessObject>> ArrayList<T> fetchAllOfType(Class<T> cls); // WTF?
}
