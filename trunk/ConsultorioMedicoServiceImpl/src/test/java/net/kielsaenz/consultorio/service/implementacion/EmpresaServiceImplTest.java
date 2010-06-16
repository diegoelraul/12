package net.kielsaenz.consultorio.service.implementacion;

import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.service.EmpresaService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmpresaServiceImplTest {

	@Test
	public void empresaTest() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			EmpresaService empresaService = (EmpresaService) context
					.getBean("empresaService");

			Empresa empresa = new Empresa();
			empresa.setRuc("20492218783");
			empresa.setRazonSocial("YeKi Solutions S.A.C.");
			empresa.setActivo(Bean.ACTIVO);
			Assert.assertFalse(empresaService.insertar(null));
			Assert.assertFalse(empresaService.insertar(empresa));
		} catch (Exception ex) {
			System.out.println(ex.toString());
			System.out.println(ex.hashCode());
			Assert.assertFalse(true);
		}
	}
}
