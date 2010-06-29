package net.kielsaenz.consultorio.service.implementacion;

import java.util.List;

import net.kielsaenz.consultorio.dao.InterfaceDao;
import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.service.EmpresaService;
import net.kielsaenz.consultorio.service.exception.ServiceException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmpresaServiceImplTest {

    private static EmpresaService empresaService;
    private Empresa empresa;
    private List<Empresa> empresas;

    @BeforeClass
    public static void setUpClass() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext(
                    "applicationContext.xml");
            empresaService = (EmpresaService) context.getBean("empresaService");
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.assertFalse(true);
        }
    }

    @Before
    public void setUp() {
        empresa = null;
        empresas = null;
    }

    // @Test
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
            empresa = empresaService.getEmpresaPorRUC("20492218783");
            Assert.assertNotNull(empresa);
            Assert.assertTrue(empresaService.eliminar(empresa));
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
            Assert.assertTrue(empresaService.insertar(empresa));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    // @Test
    public void actualizarTest() {
        try {
            String estado = null;
            empresa = empresaService.getEmpresaPorRUC("20492218783");
            Assert.assertNotNull(empresa);
            if (empresa.getActivo().equals(Bean.NO_ACTIVO)) {
                estado = Bean.ACTIVO;
            } else {
                estado = Bean.NO_ACTIVO;
            }
            empresa.setActivo(estado);
            Assert.assertTrue(empresaService.actualizar(empresa));
            empresa = null;
            empresa = empresaService.getEmpresaPorRUC("20492218783");
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
            empresa = empresaService.getEmpresaPorRUC("20492218783");
            Assert.assertNotNull(empresa);
            Integer empresaId = empresa.getEmpresaId();
            empresa = null;
            empresa = empresaService.getEmpresaPorId(empresaId);
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
            empresas = empresaService.getEmpresaPorRazonSocial("iNnOvAsYs", true,
                    InterfaceDao.TO_UPPER_CASE);
            Assert.assertNotNull(empresas);
            Assert.assertEquals(empresas.size(), 1);

            empresas = null;
            empresas = empresaService.getEmpresaPorRazonSocial("iNnOvAsYs", true,
                    InterfaceDao.TO_LOWER_CASE);
            Assert.assertNotNull(empresas);
            Assert.assertEquals(empresas.size(), 1);

            // empresas = empresaDao.getEmpresaPorRazonSocial("iNnOvAsYs", true,
            // InterfaceDao.NORMAL_CASE);
            // Assert.assertNotNull(empresas);
            // Assert.assertEquals(empresas.size(), 0);
            try {
                empresas = empresaService.getEmpresaPorRazonSocial("", true,
                        InterfaceDao.TO_LOWER_CASE);
            } catch (ServiceException ex) {
                Assert.assertEquals(ex.getKey(), "consultorio.service.error.1109");
            }

            try {
                empresas = empresaService.getEmpresaPorRazonSocial(null, true,
                        InterfaceDao.TO_LOWER_CASE);
            } catch (ServiceException ex) {
                Assert.assertEquals(ex.getKey(), "consultorio.service.error.1109");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getEmpresaPorRucTest() {
        try {
            empresa = empresaService.getEmpresaPorRUC("20492218783");
            Assert.assertNotNull(empresa);
            Assert.assertEquals(empresa.getRazonSocial(),
                    "InnovaSys Perú S.A.C.");

            try {
                empresa = empresaService.getEmpresaPorRUC("");
            } catch (ServiceException ex) {
                Assert.assertEquals(ex.getKey(), "consultorio.service.error.1107");
            }

            try {
                empresa = empresaService.getEmpresaPorRUC(null);
            } catch (ServiceException ex) {
                Assert.assertEquals(ex.getKey(), "consultorio.service.error.1107");
            }

            try {
                empresa = empresaService.getEmpresaPorRUC("12345");
            } catch (ServiceException ex) {
                Assert.assertEquals(ex.getKey(), "consultorio.service.error.1101");
            }

            try {
                empresa = empresaService.getEmpresaPorRUC("abcde");
            } catch (ServiceException ex) {
                Assert.assertEquals(ex.getKey(), "consultorio.service.error.1102");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getEmpresasTest() {
        try {
            empresas = empresaService.getEmpresas();
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
            empresa = empresaService.getEmpresaPorRUC("20492218783");
            Assert.assertNotNull(empresa);
            Assert.assertEquals(empresa.getSucursales().size(), 2);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
