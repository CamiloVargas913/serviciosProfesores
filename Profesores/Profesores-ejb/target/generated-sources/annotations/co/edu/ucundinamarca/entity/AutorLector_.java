package co.edu.ucundinamarca.entity;

import co.edu.ucundinamarca.entity.Autor;
import co.edu.ucundinamarca.entity.AutorLectorPK;
import co.edu.ucundinamarca.entity.Lector;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-24T18:37:27")
@StaticMetamodel(AutorLector.class)
public class AutorLector_ { 

    public static volatile SingularAttribute<AutorLector, String> infoAdicional;
    public static volatile SingularAttribute<AutorLector, Lector> lector;
    public static volatile SingularAttribute<AutorLector, AutorLectorPK> autorLectorId;
    public static volatile SingularAttribute<AutorLector, Autor> autor;

}