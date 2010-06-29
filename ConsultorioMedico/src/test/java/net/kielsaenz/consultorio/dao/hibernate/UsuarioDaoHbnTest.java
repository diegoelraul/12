package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import junit.framework.Assert;
import net.kielsaenz.consultorio.dao.EmpresaDao;
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
    private List<Usuario> usuarios;
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
            Persona persona = new Persona();
            //persona.setPersonaId(new Integer());
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
            Assert.assertEquals(usuarios.size(), 1);
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
            Assert.assertEquals(usuarios.size(), 1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getUsuarioPorEmpresaUsername() {
        try {
            usuario = usuarioDao.getUsuarioPorEmpresaUsername(empresa,
                    "kiel.saenz");
            Assert.assertNotNull(usuario);
            Assert.assertEquals(usuario.getPassword(), "k13l.s43nz");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void getUsuarioPorId() {
        try {
            usuario = usuarioDao.getUsuarioPorEmpresaUsername(empresa,
                    "kiel.saenz");
            Assert.assertNotNull(usuario);
            Integer id = usuario.getUsuarioId();
            usuario = null;
            System.out.println("ID: " + id.intValue());
            usuario = usuarioDao.getUsuarioPorId(id);
            Assert.assertNotNull(usuario);
            Assert.assertEquals(usuario.getUsername(), "kiel.saenz");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
