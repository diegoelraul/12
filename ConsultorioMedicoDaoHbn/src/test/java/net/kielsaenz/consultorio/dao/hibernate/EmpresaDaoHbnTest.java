package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.dao.InterfaceDao;
import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.Empresa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmpresaDaoHbnTest {

	private EmpresaDaoHbn empresaDao = new EmpresaDaoHbn();
	private Empresa empresa;
	private List<Empresa> empresas;

	@Before
	public void setUp() {
		empresa = null;
		empresas = null;
	}

	//@Test
	public void empresaTest() {
		eliminarTest();
		setUp();
		insertarTest();
		setUp();
		actualizarTest();
		/*
		 * setUp(); getEmpresaPorIdTest(); setUp();
		 * getEmpresaPorRazonSocialTest(); setUp(); getEmpresaPorRucTest();
		 * setUp(); getEmpresas();
		 */
	}

	// @Test
	public void eliminarTest() {
		try {
			empresa = empresaDao.getEmpresaPorRUC("20492218783");
			Assert.assertNotNull(empresa);
			Assert.assertTrue(empresaDao.eliminar(empresa));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	// @Test
	public void insertarTest() {
		try {
			empresa = new Empresa();
			empresa.setRazonSocial("InnovaSys Perú S.A.C.");
			empresa.setRuc("20492218783");
			empresa.setActivo(Bean.NO_ACTIVO);
			Assert.assertTrue(empresaDao.insertar(empresa));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	// @Test
	public void actualizarTest() {
		try {
			String estado = null;
			empresa = empresaDao.getEmpresaPorRUC("20492218783");
			Assert.assertNotNull(empresa);
			if (empresa.getActivo().equals(Bean.NO_ACTIVO)) {
				estado = Bean.ACTIVO;
			} else {
				estado = Bean.NO_ACTIVO;
			}
			empresa.setActivo(estado);
			Assert.assertTrue(empresaDao.actualizar(empresa));
			empresa = null;
			empresa = empresaDao.getEmpresaPorRUC("20492218783");
			Assert.assertNotNull(empresa);
			Assert.assertEquals(empresa.getActivo(), estado);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void getEmpresaPorIdTest() {
		try {
			empresa = empresaDao.getEmpresaPorRUC("20492218783");
			Assert.assertNotNull(empresa);
			Integer empresaId = empresa.getEmpresaId();
			empresa = null;
			empresa = empresaDao.getEmpresaPorId(empresaId);
			Assert.assertNotNull(empresa);
			Assert.assertEquals(empresa.getRuc(), "20492218783");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void getEmpresaPorRazonSocialTest() {
		try {
			empresas = empresaDao.getEmpresaPorRazonSocial("iNnOvAsYs", true,
					InterfaceDao.TO_UPPER_CASE);
			Assert.assertNotNull(empresas);
			Assert.assertEquals(empresas.size(), 1);

			empresas = null;
			empresas = empresaDao.getEmpresaPorRazonSocial("iNnOvAsYs", true,
					InterfaceDao.TO_LOWER_CASE);
			Assert.assertNotNull(empresas);
			Assert.assertEquals(empresas.size(), 1);

			// empresas = empresaDao.getEmpresaPorRazonSocial("iNnOvAsYs", true,
			// InterfaceDao.NORMAL_CASE);
			// Assert.assertNotNull(empresas);
			// Assert.assertEquals(empresas.size(), 0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void getEmpresaPorRucTest() {
		try {
			empresa = empresaDao.getEmpresaPorRUC("20492218783");
			Assert.assertNotNull(empresa);
			Assert.assertEquals(empresa.getRazonSocial(),
					"InnovaSys Perú S.A.C.");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void getEmpresasTest() {
		try {
			empresas = empresaDao.getEmpresas();
			Assert.assertNotNull(empresas);
			Assert.assertEquals(empresas.size(), 1);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void getEmpresaSucursalesTest() {
		try {
			empresa = empresaDao.getEmpresaPorRUC("20492218783");
			Assert.assertNotNull(empresa);
			Assert.assertEquals(empresa.getSucursales().size(),
					1);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
