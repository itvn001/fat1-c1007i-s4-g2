package dvd.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-10-11T01:05:44")
@StaticMetamodel(FeedBack.class)
public class FeedBack_ { 

    public static volatile SingularAttribute<FeedBack, String> feedBackComment;
    public static volatile SingularAttribute<FeedBack, Integer> userID;
    public static volatile SingularAttribute<FeedBack, Boolean> feedBackStatus;
    public static volatile SingularAttribute<FeedBack, Integer> albumID;
    public static volatile SingularAttribute<FeedBack, String> feedBackDateCreate;
    public static volatile SingularAttribute<FeedBack, Integer> feedBackID;

}