package net.kielsaenz.consultorio.dao.hibernate;

import junit.framework.Assert;

import org.junit.Test;

public class SucursalDaoHbnTest {

	private SucursalDaoHbn sucursalDao;

	@Test
	public void testSucursal() {
		try {
			sucursalDao = new SucursalDaoHbn();
			Assert.assertEquals(sucursalDao.getSucursales().size(), 1);
			
			Assert.assertEquals(sucursalDao.getSucursalesPorEmpresa(new Integer(1000000)), 1);
			
			//sucursalDao.getSucursalesPorEmpresaNombre(new Integer(1000000), "surco", true, )			
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
