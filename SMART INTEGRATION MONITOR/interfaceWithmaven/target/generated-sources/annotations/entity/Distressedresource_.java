package entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20140809-rNA", date="2014-10-18T21:54:09")
@StaticMetamodel(Distressedresource.class)
public class Distressedresource_ { 

    public static volatile SingularAttribute<Distressedresource, String> ip;
    public static volatile SingularAttribute<Distressedresource, Timestamp> lastUpdate;
    public static volatile SingularAttribute<Distressedresource, Double> responsetime;
    public static volatile SingularAttribute<Distressedresource, Integer> distressedresourcesId;
    public static volatile SingularAttribute<Distressedresource, String> uri;
    public static volatile SingularAttribute<Distressedresource, Integer> timeExpected;

}