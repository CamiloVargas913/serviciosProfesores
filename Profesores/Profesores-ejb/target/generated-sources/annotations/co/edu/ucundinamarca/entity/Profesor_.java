package co.edu.ucundinamarca.entity;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-18T17:41:16")
@StaticMetamodel(Profesor.class)
public class Profesor_ { 

    public static volatile SingularAttribute<Profesor, Date> fechaNacimiento;
    public static volatile SingularAttribute<Profesor, String> cedula;
    public static volatile SingularAttribute<Profesor, String> apellido;
    public static volatile SingularAttribute<Profesor, String> correo;
    public static volatile SingularAttribute<Profesor, Integer> id;
    public static volatile SingularAttribute<Profesor, String> nombre;
    public static volatile SingularAttribute<Profesor, Integer> edad;

}