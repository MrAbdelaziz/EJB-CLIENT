import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Compte;
import metier.*;

public class Test {
	
	public static BanqueRemote lookUpBanqueRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();
		
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
		final Context context = new InitialContext(jndiProperties);
		return (BanqueRemote) context.lookup("ejb:/Server_EJB/ok!metier.BanqueRemote");
	
	}

	public static void main(String[] args) {
		try {
			BanqueRemote br = lookUpBanqueRemote();
			
			br.createCompte(new Compte(5, 2000, new Date()));
			
			/*for(Compte c : br.findListCompte()) {
				System.out.println(c.getCode());
			}*/
			System.out.println(br.findCompteByCode(1).getSolde());
			System.out.println(br.findCompteByCode(2).getSolde());
			
			br.verement(br.findCompteByCode(2), br.findCompteByCode(1), 1000);
			System.out.println("----------------------");
			
			System.out.println(br.findCompteByCode(1).getSolde());
			System.out.println(br.findCompteByCode(2).getSolde());
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
