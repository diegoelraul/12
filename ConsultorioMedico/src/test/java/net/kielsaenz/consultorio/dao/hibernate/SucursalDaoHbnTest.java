package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import junit.framework.Assert;
import net.kielsaenz.consultorio.dao.EmpresaDao;

import net.kielsaenz.consultorio.dao.SucursalDao;
import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.DepProvDist;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Sucursal;
import net.kielsaenz.consultorio.model.UbigeoPK;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SucursalDaoHbnTest {

    private SucursalDao sucursalDao = new SucursalDaoHbn();
    private Sucursal sucursal;
    private List<Sucursal> sucursales;
    private static Empresa empresa;

    @BeforeClass
    public static void setUpClass() {
        try {
            EmpresaDao empresaDao = new EmpresaDaoHbn();
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

    @Test
    public void insertarActualizarEliminar() {
        try {
            sucursales = sucursalDao.getSucursales();
            Assert.assertNotNull(sucursales);
            int cantidadInicial = sucursales.size();
            //Se prueba el metodo insertar()
            sucursal = new Sucursal();
            sucursal.setEmpresa(empresa);
            sucursal.setNombre("SEDE SURCO");
            sucursal.setDireccion("CALLE LOMA BELLA 282");
            sucursal.setUrbanizacion("PROLONGACION BENAVIDES");
            sucursal.setTelefonos("2790623");
            sucursal.setPrincipal(Bean.NO);
            sucursal.setActivo(Bean.ACTIVO);
            sucursal.setDepProvDist(new DepProvDist(new UbigeoPK("15", "01", "40"), null, null, null));
            Assert.assertTrue(sucursalDao.insertar(sucursal));
            sucursales = null;
            sucursales = sucursalDao.getSucursales();
            Assert.assertNotNull(sucursales);
            int cantidadFinal = sucursales.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial + 1);
            // Se prueba el método actualizar();
            sucursal.setPrincipal(Bean.SI);
            Assert.assertTrue(sucursalDao.actualizar(sucursal));
            Integer sucursalId = sucursal.getSucursalId();
            sucursal = null;
            sucursal = sucursalDao.getSucursalPorId(sucursalId);
            Assert.assertNotNull(sucursal);
            Assert.assertEquals(sucursal.getPrincipal(), Bean.SI);
            // Se prueba el método eliminar
            Assert.assertTrue(sucursalDao.eliminar(sucursal));
            sucursales = null;
            sucursales = sucursalDao.getSucursales();
            Assert.assertNotNull(sucursales);
            cantidadFinal = sucursales.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getSucursales() {
        try {
            sucursales = sucursalDao.getSucursales();
            Assert.assertNotNull(sucursales);
            Assert.assertTrue(sucursales.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getSucursalesPorEmpresa() {
        try {
            sucursales = sucursalDao.getSucursalesPorEmpresa(empresa);
            Assert.assertNotNull(sucursales);
            Assert.assertTrue(sucursales.size() > 0);

            sucursales = sucursalDao.getSucursalesPorEmpresa(empresa.getEmpresaId());
            Assert.assertNotNull(sucursales);
            Assert.assertTrue(sucursales.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getSucursalesPorEmpresaNombre() {
        try {
            sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa, "sUrCo", true);
            Assert.assertNotNull(sucursales);
            Assert.assertTrue(sucursales.size() > 0);

            sucursales = null;
            sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa.getEmpresaId(), "sUrCo", true);
            Assert.assertNotNull(sucursales);
            Assert.assertTrue(sucursales.size() > 0);

            sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa, "sUrCo", false);
            Assert.assertNotNull(sucursales);
            Assert.assertTrue(sucursales.isEmpty());

            sucursales = null;
            sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa.getEmpresaId(), "sUrCo", false);
            Assert.assertNotNull(sucursales);
            Assert.assertTrue(sucursales.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getSucursalPorId() {
        try {
            sucursales = sucursalDao.getSucursalesPorEmpresaNombre(empresa, "sUrCo", true);
            Assert.assertNotNull(sucursales);
            Assert.assertTrue(sucursales.size() > 0);
            sucursal = sucursalDao.getSucursalPorId(new Integer(sucursales.get(0).getSucursalId()));
            Assert.assertNotNull(sucursal);
            Assert.assertEquals(sucursal.getTelefonos(), "2790623");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
