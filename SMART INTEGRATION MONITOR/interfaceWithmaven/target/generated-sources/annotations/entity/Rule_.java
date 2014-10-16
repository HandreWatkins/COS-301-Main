package entity;

import entity.Users;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.0.v20140809-rNA", date="2014-10-16T13:45:21")
@StaticMetamodel(Rule.class)
public class Rule_ { 

    public static volatile SingularAttribute<Rule, Timestamp> dateTime;
    public static volatile SingularAttribute<Rule, Double> expectedTime;
    public static volatile SingularAttribute<Rule, Integer> rulesId;
    public static volatile SingularAttribute<Rule, String> uri;
    public static volatile SingularAttribute<Rule, Users> user;

}