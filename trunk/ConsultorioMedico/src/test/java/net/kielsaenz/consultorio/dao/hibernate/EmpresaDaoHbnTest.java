package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.Empresa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmpresaDaoHbnTest {

    private EmpresaDao empresaDao = new EmpresaDaoHbn();
    private Empresa empresa;
    private List<Empresa> empresas;

    @Before
    public void setUp() {
        empresa = null;
        empresas = null;
    }

    @Test
    public void insertarActualizarEliminar(){
        try {
            empresas = empresaDao.getEmpresas();
            Assert.assertNotNull(empresas);
            int cantidadInicial = empresas.size();
            //Se prueba el metodo insertar()
            empresa = new Empresa();
            empresa.setRazonSocial("YeKi Perú S.A.C.");
            empresa.setRuc("98765432109");
            empresa.setActivo(Bean.NO_ACTIVO);
            Assert.assertTrue(empresaDao.insertar(empresa));
            empresas = null;
            empresas = empresaDao.getEmpresas();
            Assert.assertNotNull(empresas);
            int cantidadFinal = empresas.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial + 1);
            // Se prueba el método actualizar();
            empresa.setActivo(Bean.ACTIVO);
            Assert.assertTrue(empresaDao.actualizar(empresa));
            Integer empresaId = empresa.getEmpresaId();
            empresa = null;
            empresa = empresaDao.getEmpresaPorId(empresaId);
            Assert.assertNotNull(empresa);
            Assert.assertEquals(empresa.getActivo(), Bean.ACTIVO);
            // Se prueba el método eliminar
            Assert.assertTrue(empresaDao.eliminar(empresa));
            empresas = null;
            empresas = empresaDao.getEmpresas();
            Assert.assertNotNull(empresas);
            cantidadFinal = empresas.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getEmpresaPorId() {
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
    public void getEmpresaPorRazonSocial() {
        try {
            empresas = empresaDao.getEmpresaPorRazonSocial("iNnOvAsYs", true);
            Assert.assertNotNull(empresas);
            Assert.assertTrue(empresas.size() == 1);

            empresas = empresaDao.getEmpresaPorRazonSocial("iNnOvAsYs", false);
            Assert.assertNotNull(empresas);
            Assert.assertTrue(empresas.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getEmpresaPorRuc() {
        try {
            empresa = empresaDao.getEmpresaPorRUC("20492218783");
            Assert.assertNotNull(empresa);
            Assert.assertEquals(empresa.getRazonSocial(),
                    "InnovaSys Perú S.A.C.");
            Assert.assertTrue(empresa.getSucursales().size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getEmpresas() {
        try {
            empresas = empresaDao.getEmpresas();
            Assert.assertNotNull(empresas);
            Assert.assertTrue(empresas.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
