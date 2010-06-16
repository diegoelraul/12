package net.kielsaenz.consultorio.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.kielsaenz.consultorio.dao.EmpresaDao;
import net.kielsaenz.consultorio.model.Empresa;
import net.kielsaenz.consultorio.model.Especialidad;

public class EmpresaDaoHbn implements EmpresaDao {

	public List<Empresa> getEmpresas() {
		System.out.println("Inicio del método getEmpresas()");
        List<Empresa> empresas = null;
        Session hs = null;
        Transaction htx = null;
        System.out.println("Recuperando el SessionFactory");
        SessionFactory hsf = HibernateUtil.getSessionFactory();
         try {
        	System.out.println("Obteniendo la sección actual");
            hs = hsf.getCurrentSession();
            System.out.println("Iniciando transacción...");
            htx = hs.beginTransaction();
            Query hqlQuery = hs.createQuery("from Empresa e order by e.razonSocial asc");
            empresas = hqlQuery.list();
            htx.commit();
        } catch (HibernateException e) {
            if(htx != null && htx.isActive()){
                try {
                    htx.rollback();
                } catch (HibernateException e2) {
                    System.out.println("No se pudo realizar el rollback...");
                }
            }
            empresas = new ArrayList<Empresa>();
            e.printStackTrace();
        }
        return empresas;
	}

}
