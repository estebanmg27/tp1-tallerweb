package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.FarmaciaDao;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;

@Service("servicioFarmacia")
@Transactional
public class ServicioFarmaciaImpl implements ServicioFarmacia {
	
	@Inject
	private FarmaciaDao servicioFarmaciaDao;

	@Override
	public List<Farmacia> obtenerFarmacias() {
		return servicioFarmaciaDao.obtenerTodasLasFarmacias();
	}

}
