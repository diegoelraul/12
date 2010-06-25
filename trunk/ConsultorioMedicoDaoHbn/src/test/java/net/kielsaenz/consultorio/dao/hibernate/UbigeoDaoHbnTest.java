package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import net.kielsaenz.consultorio.model.Departamento;
import net.kielsaenz.consultorio.model.Distrito;
import net.kielsaenz.consultorio.model.Provincia;

import org.junit.Test;

public class UbigeoDaoHbnTest {

	private UbigeoDaoHbn ubigeoDao = new UbigeoDaoHbn();
	
	@Test
	public void getDepartamentoPorIdTest(){
		try{
			Departamento departamento = ubigeoDao.getDepartamentoPorId("15");
			Assert.assertNotNull(departamento);
			Assert.assertEquals(departamento.getNombre(), "LIMA");
		}catch(Exception ex){
			ex.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void getDepartamentosTest(){
		try{
			List<Departamento> departamentos = ubigeoDao.getDepartamentos();
			Assert.assertNotNull(departamentos);
			Assert.assertEquals(departamentos.size(), 25);
		}catch(Exception ex){
			ex.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void getDistritoPorIdTest(){
		try{
			Distrito distrito = ubigeoDao.getDistritoPorId("15", "01", "40");
			Assert.assertNotNull(distrito);
			Assert.assertEquals(distrito.getNombre(), "SANTIAGO DE SURCO");
		}catch(Exception ex){
			ex.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void getDistritosPorProvinciaTest(){
		try{
			List<Distrito> distritos = ubigeoDao.getDistritosPorProvincia(new Provincia("15", "01", "LIMA"));
			Assert.assertNotNull(distritos);
			Assert.assertEquals(distritos.size(), 43);
		}catch(Exception ex){
			ex.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void getProvinciaPorIdTest(){
		try{
			Provincia provincia = ubigeoDao.getProvinciaPorId("15", "01");
			Assert.assertNotNull(provincia);
			Assert.assertEquals(provincia.getNombre(), "LIMA");
		}catch(Exception ex){
			ex.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void getProvinciasPorDepartamentoTest(){
		try{
			List<Provincia> provincias = ubigeoDao.getProvinciasPorDepartamento(new Departamento("15", "LIMA"));
			Assert.assertNotNull(provincias);
			Assert.assertEquals(provincias.size(), 10);
		}catch(Exception ex){
			ex.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
