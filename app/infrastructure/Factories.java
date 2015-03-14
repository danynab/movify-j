package infrastructure;

import business.BusinessFactory;
import business.impl.BusinessFactoryImpl;

/**
 * Created by Dani on 13/3/15.
 */
public class Factories {

    public static BusinessFactory businessFactory = new BusinessFactoryImpl();

}
