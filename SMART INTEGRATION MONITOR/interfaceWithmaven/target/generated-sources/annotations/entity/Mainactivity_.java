package entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20140809-rNA", date="2014-10-18T21:54:09")
@StaticMetamodel(Mainactivity.class)
public class Mainactivity_ { 

    public static volatile SingularAttribute<Mainactivity, Integer> mainactivityId;
    public static volatile SingularAttribute<Mainactivity, Double> responseTime;
    public static volatile SingularAttribute<Mainactivity, String> ip;
    public static volatile SingularAttribute<Mainactivity, String> uri;
    public static volatile SingularAttribute<Mainactivity, Timestamp> createDate;

}