package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;
import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.dao.PersonaDao;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Persona;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kiel
 */
public class PersonaDaoHbnTest {

    private PersonaDao personaDao = new PersonaDaoHbn();
    private Persona persona;
    private List<Persona> personas;
    private static Empresa empresa;

    @BeforeClass
    public static void setUpClass(){
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
    public void setUp(){
        persona = null;
        personas = null;
    }

    @Test
    public void getPersonas(){
        try {
            personas = personaDao.getPersonas();
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPersonaPorId(){
        try {
            personas = personaDao.getPersonas();
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
            persona = personaDao.getPersonaPorId(personas.get(0).getPersonaId());
            Assert.assertNotNull(persona);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPersonasPorEmpresa(){
        try {
            personas = personaDao.getPersonasPorEmpresa(empresa);
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
            personas = null;
            personas = personaDao.getPersonasPorEmpresa(empresa.getEmpresaId());
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPersonasPorEmpresaNombre(){
        try {
            personas = personaDao.getPersonasPorEmpresaNombre(empresa, "KiEl", true);
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
            personas = null;
            personas = personaDao.getPersonasPorEmpresaNombre(empresa.getEmpresaId(), "KiEl", false);
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPersonasPorEmpresaApPaterno(){
        try {
            personas = personaDao.getPersonasPorEmpresaApPaterno(empresa, "SaEnZ", true);
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
            personas = null;
            personas = personaDao.getPersonasPorEmpresaApPaterno(empresa.getEmpresaId(), "SaEnZ", false);
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPersonasPorEmpresaApMaterno(){
        try {
            personas = personaDao.getPersonasPorEmpresaApMaterno(empresa, "ZuNiGa", true);
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
            personas = null;
            personas = personaDao.getPersonasPorEmpresaApMaterno(empresa.getEmpresaId(), "ZuNiGa", false);
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPersonasPorEmpresaNroDocumento(){
        try {
            personas = personaDao.getPersonasPorEmpresaNroDocumento(empresa, "4129", true);
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
            personas = null;
            personas = personaDao.getPersonasPorEmpresaNroDocumento(empresa.getEmpresaId(), "4129", false);
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
