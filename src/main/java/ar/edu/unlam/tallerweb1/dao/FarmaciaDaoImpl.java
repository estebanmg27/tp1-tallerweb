package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Farmacia;

@SuppressWarnings("unchecked")
@Repository("farmaciaDao")
public class FarmaciaDaoImpl implements FarmaciaDao {
	
	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public List<Farmacia> obtenerTodasLasFarmacias() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Farmacia.class)
				.list();
	}
	
}
