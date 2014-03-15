package ch.jManagr.DAL;

import ch.jManagr.BO.BusinessObject;

import java.util.ArrayList;

/**
 * Created by kije on 3/15/14.
 */
public class dalTest implements DataAccessLayer {
    @Override
    public void saveBusinessObject(BusinessObject bo) {

    }

    @Override
    public <T extends Class<? extends BusinessObject>> ArrayList<T> fetchAllOfType(Class<T> cls) {
        ArrayList<T> result = new ArrayList<T>();
        for(int i = 0; i < 5; i++) {
            try {
               result.add(cls.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
