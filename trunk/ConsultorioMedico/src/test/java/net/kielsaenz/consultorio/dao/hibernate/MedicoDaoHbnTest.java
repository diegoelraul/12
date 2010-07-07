package net.kielsaenz.consultorio.dao.hibernate;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.dao.MedicoDao;
import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.DepProvDist;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Medico;
import net.kielsaenz.consultorio.model.UbigeoPK;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Kiel
 */
public class MedicoDaoHbnTest {

    private MedicoDao medicoDao = new MedicoDaoHbn();
    private Medico medico;
    private List<Medico> medicos;
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
        medico = null;
        medicos = null;
    }

    @Test
    public void insertarEliminarActualizar(){
        try {
            medicos = medicoDao.getMedicos();
            Assert.assertNotNull(medicos);
            int cantidadInicial = medicos.size();
            //Se prueba el metodo insertar()
            medico = new Medico();
            medico.setNombres("Yelina Isabel");
            medico.setApellidoPaterno("Huaroto");
            medico.setApellidoMaterno("De La Cruz");
            medico.setDireccion("Av. Bolivar 337");
            medico.setEmpresa(empresa);
            medico.setTelefonoFijo("2790623");
            medico.setTelefonoCelular("99846835");
            medico.setNroColegiatura("54875");
            medico.setFechaNacimiento(new Date(new GregorianCalendar(1981, 1, 20, 0, 0, 0).getTimeInMillis()));
            medico.setDepProvDist(new DepProvDist(new UbigeoPK("15", "01", "10"), null, null, null));
            medico.setActivo(Bean.NO_ACTIVO);
            Assert.assertTrue(medicoDao.insertar(medico));
            medicos = null;
            medicos = medicoDao.getMedicos();
            Assert.assertNotNull(medicos);
            int cantidadFinal = medicos.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial + 1);
            // Se prueba el método actualizar();
            medico.setActivo(Bean.ACTIVO);
            Assert.assertTrue(medicoDao.actualizar(medico));
            Integer personaId = medico.getPersonaId();
            medico = null;
            medico = medicoDao.getMedicoPorId(personaId);
            Assert.assertNotNull(medico);
            Assert.assertEquals(medico.getActivo(), Bean.ACTIVO);
            // Se prueba el método eliminar
            Assert.assertTrue(medicoDao.eliminar(medico));
            medicos = null;
            medicos = medicoDao.getMedicos();
            Assert.assertNotNull(medicos);
            cantidadFinal = medicos.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getMedicos(){
        try {
            medicos = medicoDao.getMedicos();
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getMedicoPorId(){
        try {
            medicos = medicoDao.getMedicos();
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
            medico = medicoDao.getMedicoPorId(medicos.get(0).getPersonaId());
            Assert.assertNotNull(medico);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getMedicosPorEmpresa(){
        try {
            medicos = medicoDao.getMedicosPorEmpresa(empresa);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
            medicos = null;
            medicos = medicoDao.getMedicosPorEmpresa(empresa.getEmpresaId());
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getMedicosPorEmpresaNombre(){
        try {
            medicos = medicoDao.getMedicosPorEmpresaNombre(empresa, "CaMiLo", true);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
            medicos = null;
            medicos = medicoDao.getMedicosPorEmpresaNombre(empresa.getEmpresaId(), "CaMiLo", false);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getMedicosPorEmpresaApPaterno(){
        try {
            medicos = medicoDao.getMedicosPorEmpresaApPaterno(empresa, "CoNtReRaS", true);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
            medicos = null;
            medicos = medicoDao.getMedicosPorEmpresaApPaterno(empresa.getEmpresaId(), "CoNtReRaS", false);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getMedicosPorEmpresaApMaterno(){
        try {
            medicos = medicoDao.getMedicosPorEmpresaApMaterno(empresa, "CaMpAnA", true);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
            medicos = null;
            medicos = medicoDao.getMedicosPorEmpresaApMaterno(empresa.getEmpresaId(), "CaMpAnA", false);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getMedicosPorEmpresaNroColegiatura(){
        try {
            medicos = medicoDao.getMedicosPorEmpresaNroColegiatura(empresa, "123", true);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
            medicos = null;
            medicos = medicoDao.getMedicosPorEmpresaNroColegiatura(empresa.getEmpresaId(), "123", false);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getMedicosPorEmpresaNroDocumento(){
        try {
            medicos = medicoDao.getMedicosPorEmpresaNroDocumento(empresa, "987", true);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.size() > 0);
            medicos = null;
            medicos = medicoDao.getMedicosPorEmpresaNroDocumento(empresa.getEmpresaId(), "987", false);
            Assert.assertNotNull(medicos);
            Assert.assertTrue(medicos.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
