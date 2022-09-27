package br.com.alura.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class AgendamentoEmailBusiness {
	
	public List<String> listarAgendamentosEmail(){
		
		List<String> emails = new ArrayList<>();
		
		emails.add("email1@teste.com");
		emails.add("email2@teste.com");
		
		return emails;
	}

}
