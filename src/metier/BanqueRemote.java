package metier;

import java.util.List;

import javax.ejb.Remote;

import entities.Compte;

@Remote
public interface BanqueRemote {
	void createCompte(Compte c);
	Compte findCompteByCode(int code);
	List<Compte> findListCompte();
	void verser(int code, double montant);
	void retirer(int code, double montant);
	void verement(Compte c1 ,Compte c2, double montant);
}
