package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.SpringTest;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("unchecked")
public class FarmaciaTest extends SpringTest {
    private Session session;

    @Before
    public void setUp(){
        session = getSession();

        Comuna comuna1 = new Comuna("Comuna 1");
        Comuna comuna5 = new Comuna("Comuna 5");

        session.save(comuna1);
        session.save(comuna5);

        Barrio retiro = new Barrio("Retiro", comuna1);
        Barrio sanTelmo = new Barrio("San Telmo", comuna1);
        Barrio boedo = new Barrio("Boedo", comuna5);

        session.save(retiro);
        session.save(sanTelmo);
        session.save(boedo);

        Direccion avSanJuan620 = new Direccion("Av. San Juan", "620", sanTelmo);
        Direccion juncal954 = new Direccion("juncal954", "954", retiro);
        Direccion inclan4185 = new Direccion("Inclan", "4185", boedo);
        Direccion inclan4325 = new Direccion("Inclan", "4325", boedo);

        session.save(avSanJuan620);
        session.save(juncal954);
        session.save(inclan4185);
        session.save(inclan4325);

        Punto punto1 = new Punto("-34.58665", "-58.33556");
        Punto punto2 = new Punto("-34.7789", "-58.526998");
        Punto punto3 = new Punto("-34.12548", "-58.9588");
        Punto punto4 = new Punto("-34.12548", "-58.9629");

        session.save(punto1);
        session.save(punto2);
        session.save(punto3);
        session.save(punto4);

        Farmacia farmaciaDrAhorro = new Farmacia("Farmacia del Dr. Ahorro", "46589795", "Martes", punto1, avSanJuan620);
        Farmacia farmaciaBelen = new Farmacia("Farmacia Belen", "44589498", "Jueves", punto2, juncal954);
        Farmacia farmaciaLucas = new Farmacia("Farmacia Lucas", "53585245", "Martes", punto3, inclan4185);
        Farmacia farmaciaJuan = new Farmacia("Farmacia Juan", "53581284", "Sabado", punto4, inclan4325);

        session.save(farmaciaDrAhorro);
        session.save(farmaciaBelen);
        session.save(farmaciaLucas);
        session.save(farmaciaJuan);
    }

    @Test
    @Transactional @Rollback()
    public void obtenerFarmaciasDeTurnoLosMartes(){
        List<Farmacia> busqueda = session.createCriteria(Farmacia.class)
                .add(Restrictions.eq("diaDeTurno", "Martes"))
                .list();

        assertThat(busqueda.size()).isEqualTo(2);
        for(Farmacia farmacia : busqueda){
            assertThat(farmacia.getDiaDeTurno()).isEqualTo("Martes");
        }
    }

    @Test
    @Transactional @Rollback()
    public void obtenerFarmaciasDeLaCalleInclan(){
        List<Farmacia> busqueda = session.createCriteria(Farmacia.class)
                .createAlias("direccion", "tablaDireccion")
                .add(Restrictions.eq("tablaDireccion.calle", "Inclan"))
                .list();

        assertThat(busqueda.size()).isEqualTo(2);
        for(Farmacia farmacia : busqueda){
            assertThat(farmacia.getDireccion().getCalle()).isEqualTo("Inclan");
        }
    }

    @Test
    @Transactional @Rollback()
    public void obtenerFarmaciasDeBoedo(){
        List<Farmacia> busqueda = session.createCriteria(Farmacia.class)
                .createAlias("direccion", "tablaDireccion")
                .createAlias("tablaDireccion.barrio", "tablaBarrio")
                .add(Restrictions.eq("tablaBarrio.nombre", "Boedo"))
                .list();

        assertThat(busqueda.size()).isEqualTo(2);
        for(Farmacia farmacia : busqueda){
            assertThat(farmacia.getDireccion().getBarrio().getNombre()).isEqualTo("Boedo");
        }
    }
}
