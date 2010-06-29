package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;
import junit.framework.Assert;
import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.dao.PersonaDao;
import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Persona;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kiel
 */
public class PersonaDaoTest {

    private PersonaDao personaDao = new PersonaDaoHbn();
    private Persona persona;
    private List<Persona> personas;
    private Empresa empresa;

    @BeforeClass
    public void setUpClass(){
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
    public void insertarEliminarActualizar(){
        try {
            personas = personaDao.getPersonas();
            Assert.assertNotNull(personas);
            int cantidadInicial = personas.size();
            //Se prueba el metodo insertar()
            persona = new Persona();
            persona.setNombres("Yelina Isabel");
            persona.setApellidoPaterno("Huaroto");
            persona.setApellidoMaterno("De La Cruz");
            persona.setDireccion("Av. Bolivar 337 - Int E");
            persona.setEmpresa(empresa);
            persona.setTelefonoFijo("2790623");
            persona.setTelefonoCelular("99846835");
            persona.setActivo(Bean.NO_ACTIVO);
            Assert.assertTrue(personaDao.insertar(persona));
            personas = null;
            personas = personaDao.getPersonas();
            Assert.assertNotNull(personas);
            int cantidadFinal = personas.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial + 1);
            // Se prueba el método actualizar();
            persona.setActivo(Bean.ACTIVO);
            Assert.assertTrue(personaDao.actualizar(persona));
            Integer personaId = persona.getPersonaId();
            persona = null;
            persona = personaDao.getPersonaPorId(personaId);
            Assert.assertNotNull(persona);
            Assert.assertEquals(persona.getActivo(), Bean.ACTIVO);
            // Se prueba el método eliminar
            Assert.assertTrue(personaDao.eliminar(persona));
            personas = null;
            personas = personaDao.getPersonas();
            Assert.assertNotNull(personas);
            cantidadFinal = personas.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
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
}
