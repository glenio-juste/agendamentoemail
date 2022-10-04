package br.com.alura.timer;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import br.com.alura.business.AgendamentoEmailBusiness;
import br.com.alura.entity.AgendamentoEmail;

@Singleton // configuramos o EJB Timer para não ter duas execuções em paralelo
public class AgendamentoEmailTimer {
	
	//private static Logger logger =  Logger.getLogger(AgendamentoEmailTimer.class.getName());
	
	@Inject
	private AgendamentoEmailBusiness ageanAgendamentoEmailBusiness;
	
	
	@Schedule(hour = "*", minute = "*")
	public void enviarEmailsAgendados() {
		
		//logger.info("Rodou!");
		
		List<AgendamentoEmail> agendamentoEmails = ageanAgendamentoEmailBusiness.listarAgendamentoEmailsNaoEnviados();
		agendamentoEmails
		.stream()
		.forEach(agendamentoEmail -> ageanAgendamentoEmailBusiness.enviarEmail(agendamentoEmail));
		
	}

}
