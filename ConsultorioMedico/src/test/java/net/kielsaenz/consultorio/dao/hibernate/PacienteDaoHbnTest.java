package net.kielsaenz.consultorio.dao.hibernate;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.dao.PacienteDao;
import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.DepProvDist;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Paciente;
import net.kielsaenz.consultorio.model.UbigeoPK;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kiel
 */
public class PacienteDaoHbnTest {

    private PacienteDao pacienteDao = new PacienteDaoHbn();
    private Paciente paciente;
    private List<Paciente> pacientes;
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
        paciente = null;
        pacientes = null;
    }

    @Test
    public void insertarEliminarActualizar(){
        try {
            pacientes = pacienteDao.getPacientes();
            Assert.assertNotNull(pacientes);
            int cantidadInicial = pacientes.size();
            //Se prueba el metodo insertar()
            paciente = new Paciente();
            paciente.setNombres("Yelina Isabel");
            paciente.setApellidoPaterno("Huaroto");
            paciente.setApellidoMaterno("De La Cruz");
            paciente.setDireccion("Av. Bolivar 337");
            paciente.setEmpresa(empresa);
            paciente.setTelefonoFijo("2790623");
            paciente.setTelefonoCelular("99846835");
            paciente.setFechaNacimiento(new Date(new GregorianCalendar(1981, 1, 20, 0, 0, 0).getTimeInMillis()));
            paciente.setDepProvDist(new DepProvDist(new UbigeoPK("15", "01", "10"), null, null, null));
            paciente.setActivo(Bean.NO_ACTIVO);
            Assert.assertTrue(pacienteDao.insertar(paciente));
            pacientes = null;
            pacientes = pacienteDao.getPacientes();
            Assert.assertNotNull(pacientes);
            int cantidadFinal = pacientes.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial + 1);
            // Se prueba el método actualizar();
            paciente.setActivo(Bean.ACTIVO);
            Assert.assertTrue(pacienteDao.actualizar(paciente));
            Integer personaId = paciente.getPersonaId();
            paciente = null;
            paciente = pacienteDao.getPacientePorId(personaId);
            Assert.assertNotNull(paciente);
            Assert.assertEquals(paciente.getActivo(), Bean.ACTIVO);
            // Se prueba el método eliminar
            Assert.assertTrue(pacienteDao.eliminar(paciente));
            pacientes = null;
            pacientes = pacienteDao.getPacientes();
            Assert.assertNotNull(pacientes);
            cantidadFinal = pacientes.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPacientes(){
        try {
            pacientes = pacienteDao.getPacientes();
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPacientePorId(){
        try {
            pacientes = pacienteDao.getPacientes();
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.size() > 0);
            paciente = pacienteDao.getPacientePorId(pacientes.get(0).getPersonaId());
            Assert.assertNotNull(paciente);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPacientesPorEmpresa(){
        try {
            pacientes = pacienteDao.getPacientesPorEmpresa(empresa);
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.size() > 0);
            pacientes = null;
            pacientes = pacienteDao.getPacientesPorEmpresa(empresa.getEmpresaId());
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPacientesPorEmpresaNombre(){
        try {
            pacientes = pacienteDao.getPacientesPorEmpresaNombre(empresa, "KiEl", true);
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.size() > 0);
            pacientes = null;
            pacientes = pacienteDao.getPacientesPorEmpresaNombre(empresa.getEmpresaId(), "KiEl", false);
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPacientesPorEmpresaApPaterno(){
        try {
            pacientes = pacienteDao.getPacientesPorEmpresaApPaterno(empresa, "SaEnZ", true);
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.size() > 0);
            pacientes = null;
            pacientes = pacienteDao.getPacientesPorEmpresaApPaterno(empresa.getEmpresaId(), "SaEnZ", false);
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPacientesPorEmpresaApMaterno(){
        try {
            pacientes = pacienteDao.getPacientesPorEmpresaApMaterno(empresa, "ZuÑiGa", true);
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.size() > 0);
            pacientes = null;
            pacientes = pacienteDao.getPacientesPorEmpresaApMaterno(empresa.getEmpresaId(), "ZuÑiGa", false);
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getPacientesPorEmpresaNroDocumento(){
        try {
            pacientes = pacienteDao.getPacientesPorEmpresaNroDocumento(empresa, "4129", true);
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.size() > 0);
            pacientes = null;
            pacientes = pacienteDao.getPacientesPorEmpresaNroDocumento(empresa.getEmpresaId(), "4129", false);
            Assert.assertNotNull(pacientes);
            Assert.assertTrue(pacientes.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
