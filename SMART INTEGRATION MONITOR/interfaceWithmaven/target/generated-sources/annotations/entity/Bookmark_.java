package entity;

import entity.Users;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20140809-rNA", date="2014-10-16T13:45:21")
@StaticMetamodel(Bookmark.class)
public class Bookmark_ { 

    public static volatile SingularAttribute<Bookmark, Timestamp> dateTime;
    public static volatile SingularAttribute<Bookmark, Integer> bookmarkId;
    public static volatile SingularAttribute<Bookmark, String> discription;
    public static volatile SingularAttribute<Bookmark, Users> user;

}