package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import net.kielsaenz.consultorio.model.Departamento;
import net.kielsaenz.consultorio.model.Distrito;
import net.kielsaenz.consultorio.model.Provincia;

import org.junit.Test;

public class UbigeoDaoHbnTest {

	@Test
	public void ubigeoTest(){
		try{
			UbigeoDaoHbn ubigeoDao = new UbigeoDaoHbn();
			
			List<Departamento> departamentos = ubigeoDao.getDepartamentos();
			Assert.assertEquals(departamentos.size(), 25);
			
			Departamento departamento = ubigeoDao.getDepartamentoPorId("15");
			Assert.assertEquals(departamento.getNombre(), "LIMA");
			
			List<Provincia> provincias = ubigeoDao.getProvinciasPorDepartamento(departamento);
			Assert.assertEquals(provincias.size(), 10);
			
			Provincia provincia = ubigeoDao.getProvinciaPorId("15", "01");
			Assert.assertEquals(provincia.getNombre(), "LIMA");
			
			List<Distrito> distritos = ubigeoDao.getDistritosPorProvincia(provincia);
			Assert.assertEquals(distritos.size(), 43);
			
			Distrito distrito = ubigeoDao.getDistritoPorId("15", "01", "40");
			Assert.assertEquals(distrito.getNombre(), "SANTIAGO DE SURCO");
		}catch(Exception ex){
			ex.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}
