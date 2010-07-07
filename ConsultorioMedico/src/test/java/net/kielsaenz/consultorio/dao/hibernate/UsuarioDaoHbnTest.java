package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import junit.framework.Assert;
import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.dao.PersonaDao;
import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Persona;
import net.kielsaenz.consultorio.model.Usuario;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsuarioDaoHbnTest {

    private UsuarioDaoHbn usuarioDao = new UsuarioDaoHbn();
    private Usuario usuario;
    private static Persona persona;
    private List<Usuario> usuarios;
    private static Empresa empresa;

    @BeforeClass
    public static void setUpClass() {
        try {
            EmpresaDao empresaDao = new EmpresaDaoHbn();
            empresa = empresaDao.getEmpresaPorRUC("20492218783");
            Assert.assertNotNull(empresa);
            PersonaDao personaDao = new PersonaDaoHbn();
            List<Persona> personas = personaDao.getPersonasPorEmpresa(empresa);
            Assert.assertNotNull(personas);
            Assert.assertTrue(personas.size() > 0);
            persona = personas.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Before
    public void setUp() {
        usuario = null;
        usuarios = null;
    }

    @Test
    public void insertarActualizarEliminar() {
        try {
            usuarios = usuarioDao.getUsuarios();
            Assert.assertNotNull(usuarios);
            int cantidadInicial = usuarios.size();
            //Se prueba el metodo insertar()
            usuario = new Usuario();
            usuario.setEmpresa(empresa);
            usuario.setUsername("yelina.huaroto");
            usuario.setPassword("1234567890");
            usuario.setPersona(persona);
            usuario.setActivo(Bean.NO_ACTIVO);
            Assert.assertTrue(usuarioDao.insertar(usuario));
            usuarios = null;
            usuarios = usuarioDao.getUsuarios();
            Assert.assertNotNull(usuarios);
            int cantidadFinal = usuarios.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial + 1);
            // Se prueba el método actualizar();
            usuario.setActivo(Bean.ACTIVO);
            Assert.assertTrue(usuarioDao.actualizar(usuario));
            usuario = null;
            usuario = usuarioDao.getUsuarioPorEmpresaUsername(empresa, "yelina.huaroto");
            Assert.assertEquals(usuario.getActivo(), Bean.ACTIVO);
            // Se prueba el método eliminar
            Assert.assertTrue(usuarioDao.eliminar(usuario));
            usuarios = null;
            usuarios = usuarioDao.getUsuarios();
            Assert.assertNotNull(usuarios);
            cantidadFinal = usuarios.size();
            Assert.assertEquals(cantidadFinal, cantidadInicial);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getUsuarios() {
        try {
            usuarios = usuarioDao.getUsuarios();
            Assert.assertNotNull(usuarios);
            Assert.assertTrue(usuarios.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getUsuariosPorEmpresa() {
        try {
            usuarios = usuarioDao.getUsuariosPorEmpresa(empresa);
            Assert.assertNotNull(usuarios);
            Assert.assertTrue(usuarios.size() > 0);

            usuarios = null;
            usuarios = usuarioDao.getUsuariosPorEmpresa(empresa.getEmpresaId());
            Assert.assertNotNull(usuarios);
            Assert.assertTrue(usuarios.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getUsuarioPorEmpresaUsername() {
        try {
            usuario = usuarioDao.getUsuarioPorEmpresaUsername(empresa, "kiel.saenz");
            Assert.assertNotNull(usuario);
            Assert.assertEquals(usuario.getPassword(), "k13l.s43nz");
            Assert.assertEquals(usuario.getPersona().getNroDocumento(), "41296691");

            usuario = null;
            usuario = usuarioDao.getUsuarioPorEmpresaUsername(empresa.getEmpresaId(), "kiel.saenz");
            Assert.assertNotNull(usuario);
            Assert.assertEquals(usuario.getPassword(), "k13l.s43nz");
            Assert.assertEquals(usuario.getPersona().getNroDocumento(), "41296691");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getUsuarioPorId() {
        try {
            usuario = usuarioDao.getUsuarioPorEmpresaUsername(empresa, "kiel.saenz");
            Assert.assertNotNull(usuario);
            Integer id = usuario.getUsuarioId();
            usuario = null;
            usuario = usuarioDao.getUsuarioPorId(id);
            Assert.assertNotNull(usuario);
            Assert.assertEquals(usuario.getUsername(), "kiel.saenz");
            Assert.assertEquals(usuario.getPersona().getNroDocumento(), "41296691");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
