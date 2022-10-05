package br.com.alura.timer;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import br.com.alura.business.AgendamentoEmailBusiness;
import br.com.alura.entity.AgendamentoEmail;

@Singleton // configuramos o EJB Timer para não ter duas execuções em paralelo
public class AgendamentoEmailTimer {

	// private static Logger logger =
	// Logger.getLogger(AgendamentoEmailTimer.class.getName());

	@Inject
	private AgendamentoEmailBusiness ageanAgendamentoEmailBusiness;

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;

	@Schedule(hour = "*", minute = "*")
	public void enviarEmailsAgendados() {
		
		//logger.info("Rodou!");
		
		List<AgendamentoEmail> agendamentoEmails = ageanAgendamentoEmailBusiness.listarAgendamentoEmailsNaoEnviados();
		agendamentoEmails
		.stream()
		.forEach(agendamentoEmail ->{			
		context.createProducer().send(queue, agendamentoEmail);
		ageanAgendamentoEmailBusiness.marcarEnviadas(agendamentoEmail);
	});

}

}
