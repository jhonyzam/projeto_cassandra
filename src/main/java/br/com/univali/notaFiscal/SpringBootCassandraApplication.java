package br.com.univali.notaFiscal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.univali.notaFiscal.services.NotaFiscalService;

@SpringBootApplication
public class SpringBootCassandraApplication implements CommandLineRunner {
	
	@Autowired
	NotaFiscalService notaFiscalService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCassandraApplication.class, args);
	}
	
    @Override
    public void run(String... arg0) throws Exception {
    	notaFiscalService.getNotasFiscais();
        System.out.println("Creating tables");       
    }
}
