package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.model.Empresa;

import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Test;

public class EmpresaDaoHbnTest {

	@Test
	public void empresaTest(){
        try {
        	System.out.println("Obteniendo EmpresaDaoHbn");
            EmpresaDao empresaDao = new EmpresaDaoHbn();
            System.out.println("Obteniendo empresas");
            List<Empresa> empresas = empresaDao.getEmpresas();
            System.out.println("Comparando resultrados...");
            Assert.assertEquals(empresas.size(), 1);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
	}
}
