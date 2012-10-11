package dvd.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-10-11T01:05:44")
@StaticMetamodel(DataStore.class)
public class DataStore_ { 

    public static volatile SingularAttribute<DataStore, Integer> supID;
    public static volatile SingularAttribute<DataStore, String> dataPath;
    public static volatile SingularAttribute<DataStore, Integer> dataID;
    public static volatile SingularAttribute<DataStore, Boolean> dataPublic;
    public static volatile SingularAttribute<DataStore, Integer> albumID;
    public static volatile SingularAttribute<DataStore, Boolean> dataStatus;
    public static volatile SingularAttribute<DataStore, String> dataDescription;
    public static volatile SingularAttribute<DataStore, String> dataName;

}