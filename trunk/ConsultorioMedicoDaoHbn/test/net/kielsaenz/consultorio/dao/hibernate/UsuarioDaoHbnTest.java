package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import junit.framework.Assert;
import net.kielsaenz.consultorio.dao.InterfaceDao;
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
			EmpresaDaoHbn empresaDao = new EmpresaDaoHbn();
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

	//@Test
	public void usuarioTest() {
		eliminarTest(); 
		setUp();
		insertarTest(); 
		setUp(); 
		actualizarTest();
		/*
		setUp();
		getSucursalesTest();
		setUp();
		getSucursalesPorEmpresaTest();
		setUp();
		getSucursalesPorEmpresaNombre();
		setUp();
		getSucursalesPorEmpresaNombre();
		setUp();
		getSucursalPorIdTest();*/
	}

	// @Test
	public void eliminarTest() {
		try {
			usuario = usuarioDao.getUsuarioPorEmpresaUsername(empresa,
					"kiel.saenz");
			Assert.assertNotNull(usuario);
			Assert.assertTrue(usuarioDao.eliminar(usuario));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	// @Test
	public void insertarTest() {
		try {
			usuario = new Usuario();
			usuario.setEmpresa(empresa);
			usuario.setUsername("kiel.saenz");
			usuario.setPassword("k13l.s43nz");
			usuario.setActivo(Bean.NO_ACTIVO);
			Assert.assertTrue(usuarioDao.insertar(usuario));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	// @Test
	public void actualizarTest() {
		try {
			String activo = null;
			usuario = usuarioDao.getUsuarioPorEmpresaUsername(empresa,
			"kiel.saenz");
			Assert.assertNotNull(usuario);
			if (usuario.getPassword().equals(Bean.ACTIVO)) {
				activo = Bean.NO_ACTIVO;
			} else {
				activo = Bean.ACTIVO;
			}
			usuario.setActivo(activo);
			Assert.assertTrue(usuarioDao.actualizar(usuario));
			usuario = null;
			usuario = usuarioDao.getUsuarioPorEmpresaUsername(empresa,
			"kiel.saenz");
			Assert.assertNotNull(usuario);
			Assert.assertEquals(usuario.getActivo(), activo);
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
	public void getUsuariosPorEmpresaTest() {
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
	public void getUsuarioPorEmpresaUsernameTest() {
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
	public void getUsuarioPorIdTest() {
		try {
			usuario = usuarioDao.getUsuarioPorEmpresaUsername(empresa,
			"kiel.saenz");
			Assert.assertNotNull(usuario);
			Integer id = usuario.getUsuarioId();
			usuario = null;
			usuario = usuarioDao.getUsuarioPorId(id);
			Assert.assertNotNull(usuario);
			Assert.assertEquals(usuario.getUsername(), "kiel.saenz");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
