package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import dao.ReservasDao;
import model.Reserva;

@Service
public class ReservasServiceImpl implements ReservasService {
	@Autowired
	ReservasDao reservas;
	@Autowired
	RestTemplate template;
	String url = "http://servicio-vuelos"; // usamos identificador/nombre con que se registro en Eureka Server
	
	
	@Override
	public void realizarReserva(Reserva reserva, int totalPersonas) {
		reservas.generarReserva(reserva);
		/*Como no le pasamos nada en el cuerpo ponemos null*/
		//hacemos llamada al metodo PUT del servicio de VUELOS
		//2 parametros en url vuelos/{idvuelo}/{plazas} los valores de dichas variables se pasan como parametros
		template.put(url + "/vuelos/{p1}/{p2}",null,reserva.getVuelo(),totalPersonas);
		
	}

	@Override
	public List<Reserva> getReservas() {
		return this.reservas.getReservas();
	}

}
