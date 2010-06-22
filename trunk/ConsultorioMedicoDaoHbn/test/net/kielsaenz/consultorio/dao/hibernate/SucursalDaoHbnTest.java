package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import net.kielsaenz.consultorio.dao.InterfaceDao;
import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Sucursal;
import net.kielsaenz.consultorio.model.UbigeoId;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SucursalDaoHbnTest {

	private SucursalDaoHbn sucursalDao = new SucursalDaoHbn();
	private Sucursal sucursal;
	private List<Sucursal> sucursales;
	private static Empresa empresa;

	@BeforeClass
	public static void setUpClass() {
		try {
			EmpresaDaoHbn empresaDao = new EmpresaDaoHbn();
			empresa = empresaDao.getEmpresaPorRUC("20492218783");
			Assert.assertNotNull(empresa);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Before
	public void setUp() {
		sucursal = null;
		sucursales = null;
	}

	//@Test
	public void sucursalTest() {
		eliminarTest(); 
		setUp(); 
		insertarTest(); 
		setUp(); 
		actualizarTest();
		/*
		setUp();
		getSucursalesTest();
		setUp();
		getSucursalesPorEmpresaTest();
		setUp();
		getSucursalesPorEmpresaNombre();
		setUp();
		getSucursalesPorEmpresaNombre();
		setUp();
		getSucursalPorIdTest();*/
	}

	// @Test
	public void eliminarTest() {
		try {
			sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa,
					"sUrCo", true, InterfaceDao.TO_LOWER_CASE);
			Assert.assertNotNull(sucursales);
			Assert.assertEquals(sucursales.size(), 1);
			sucursal = sucursalDao.getSucursalPorId(new Integer(sucursales.get(
					0).getSucursalId()));
			Assert.assertNotNull(sucursal);
			Assert.assertTrue(sucursalDao.eliminar(sucursal));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	// @Test
	public void insertarTest() {
		try {
			sucursal = new Sucursal();
			sucursal.setEmpresa(empresa);
			sucursal.setNombre("innova surco");
			sucursal.setDireccion("calle loma bella 282");
			sucursal.setUrbanizacion("prolongación benavides");
			sucursal.setTelefonos("2790623");
			sucursal.setPrincipal(Bean.NO);
			sucursal.setActivo(Bean.ACTIVO);
			sucursal.setUbigeoId(new UbigeoId("15", "01", "40"));
			Assert.assertTrue(sucursalDao.insertar(sucursal));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	// @Test
	public void actualizarTest() {
		try {
			String principal = null;
			sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa,
					"sUrCo", true, InterfaceDao.TO_LOWER_CASE);
			Assert.assertNotNull(sucursales);
			Assert.assertEquals(sucursales.size(), 1);
			sucursal = sucursalDao.getSucursalPorId(new Integer(sucursales.get(
					0).getSucursalId()));
			Assert.assertNotNull(sucursal);
			if (sucursal.getPrincipal().equals(Bean.SI)) {
				principal = Bean.NO;
			} else {
				principal = Bean.SI;
			}
			sucursal.setPrincipal(Bean.SI);
			Assert.assertTrue(sucursalDao.actualizar(sucursal));
			sucursal = null;
			sucursal = sucursalDao.getSucursalPorId(new Integer(sucursales.get(
					0).getSucursalId()));
			Assert.assertNotNull(sucursal);
			Assert.assertEquals(sucursal.getPrincipal(), principal);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void getSucursalesTest() {
		try {
			sucursales = sucursalDao.getSucursales();
			Assert.assertNotNull(sucursales);
			Assert.assertEquals(sucursales.size(), 1);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void getSucursalesPorEmpresaTest() {
		try {
			sucursales = sucursalDao.getSucursalesPorEmpresa(empresa);
			Assert.assertNotNull(sucursales);
			Assert.assertEquals(sucursales.size(), 1);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void getSucursalesPorEmpresaNombre() {
		try {
			sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa,
					"sUrCo", true, InterfaceDao.TO_LOWER_CASE);
			Assert.assertNotNull(sucursales);
			Assert.assertEquals(sucursales.size(), 1);

			sucursales = null;
			sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa,
					"sUrCo", true, InterfaceDao.TO_LOWER_CASE);
			Assert.assertNotNull(sucursales);
			Assert.assertEquals(sucursales.size(), 1);

			sucursales = null;
			sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa,
					"sUrCo", true, InterfaceDao.TO_UPPER_CASE);
			Assert.assertNotNull(sucursales);
			Assert.assertEquals(sucursales.size(), 1);

			/*
			 * sucursales = null; sucursales =
			 * sucursalDao.getSucursalesPorEmpresaNombre(empresa, "sUrCo", true,
			 * InterfaceDao.NORMAL_CASE Assert.assertNotNull(sucursales);
			 * Assert.assertEquals(sucursales.size(), 0);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public void getSucursalPorIdTest() {
		try {
			sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa,
					"sUrCo", true, InterfaceDao.TO_LOWER_CASE);
			Assert.assertNotNull(sucursales);
			Assert.assertEquals(sucursales.size(), 1);
			sucursal = sucursalDao.getSucursalPorId(new Integer(sucursales.get(
					0).getSucursalId()));
			Assert.assertNotNull(sucursal);
			Assert.assertEquals(sucursal.getTelefonos(), "2790623");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void getSucursalEmpresaTest() {
		try {
			sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa,
					"sUrCo", true, InterfaceDao.TO_LOWER_CASE);
			Assert.assertNotNull(sucursales);
			Assert.assertEquals(sucursales.size(), 1);
			sucursal = sucursalDao.getSucursalPorId(new Integer(sucursales.get(
					0).getSucursalId()));
			Assert.assertNotNull(sucursal);
			Assert.assertEquals(sucursal.getEmpresa().getRuc(), "20492218783");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
