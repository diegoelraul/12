package net.kielsaenz.consultorio.dao.hibernate;

import java.util.List;

import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.dao.InterfaceDao;
import net.kielsaenz.consultorio.model.Bean;
import net.kielsaenz.consultorio.model.Empresa;

import org.junit.Assert;
import org.junit.Test;

public class EmpresaDaoHbnTest {

	@Test
	public void empresa(){
        try {
        	EmpresaDao empresaDao = new EmpresaDaoHbn();
        	Empresa empresa = null;
        	
        	/*empresa = null;
            empresa = empresaDao.getEmpresaPorRUC("20492218783");
            Assert.assertEquals(empresa.getRazonSocial(), "InnovaSys Perú S.A.C.");
            Assert.assertTrue(empresaDao.eliminar(empresa));
            
        	empresa = new Empresa();
        	empresa.setRazonSocial("InnovaSys Perú S.A.C.");
        	empresa.setRuc("20492218783");
        	empresa.setActivo(Bean.NO_ACTIVO);
        	Assert.assertTrue(empresaDao.insertar(empresa));
        	
        	empresa.setActivo(Bean.ACTIVO);
        	Assert.assertTrue(empresaDao.actualizar(empresa));
        	
        	Integer empresaId = empresa.getEmpresaId();
        	empresa = null;
            empresa = empresaDao.getEmpresaPorId(empresaId);
            Assert.assertEquals(empresa.getActivo(), Bean.ACTIVO);
            */
            List<Empresa> empresas = null;
            empresas = empresaDao.getEmpresas();
            Assert.assertEquals(empresas.size(), 1);
            
            empresa = null;
            empresa = empresaDao.getEmpresaPorRUC("20492218783");
            Assert.assertEquals(empresa.getSucursales().size(), 1);
            
            empresas = null;
            empresas = empresaDao.getEmpresaPorRazonSocial("innovasys", true, InterfaceDao.NORMAL_CASE);
            Assert.assertEquals(empresas.size(), 0);
            
            empresas = null;
            empresas = empresaDao.getEmpresaPorRazonSocial("iNnOvAsYs", true, InterfaceDao.TO_UPPER_CASE);
            Assert.assertEquals(empresas.size(), 1);
            
            empresas = null;
            empresas = empresaDao.getEmpresaPorRazonSocial("innovasys", false, InterfaceDao.TO_LOWER_CASE);
            Assert.assertEquals(empresas.size(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
	}
}
