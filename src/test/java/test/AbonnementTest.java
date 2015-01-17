package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sid.entities.Abonnement;
import org.sid.entities.AbonnementGSM;
import org.sid.entities.AbonnementInternet;
import org.sid.metier.IAbonnementMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbonnementTest {
	private IAbonnementMetier metier;
	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext
				(new String[]{"applicationContext.xml"});
		metier=(IAbonnementMetier)context.getBean("metier");
	}

	@Test
	public void test() {
		List<Abonnement> abs1=metier.listAbonnements(true);
		metier.addAbonnement(new AbonnementGSM(new Date(), 7000, true, 400));
		metier.addAbonnement(new AbonnementInternet(new Date(),500,true,4));
		List<Abonnement> abs2=metier.listAbonnements(true);
		assertTrue(abs1.size()+2==abs2.size());
	}

}
