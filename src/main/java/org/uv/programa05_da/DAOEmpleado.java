/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa05_da;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emili
 */
public class DAOEmpleado implements IDAOGeneral<Empleado, Long> {

    @Override
    public Empleado create(Empleado p) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        session.save(p);
        t.commit();
        session.close();
        return p;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        boolean res;
        Empleado empleado = findById(id);
        if (empleado == null) {
            res = false;
        } else {
            session.delete(empleado);
            res = true;
        }
        tx.commit();
        session.close();
        return res;
    }

    @Override
    public Empleado update(Empleado p, Long id) {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Empleado empleado = findById(id);
        if (empleado != null) {
            session.update(empleado);
        }

        tx.commit();
        session.close();
        return empleado;
    }

    @Override
    public List<Empleado> findAll() {
        List<Empleado> lstRes = null;
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        lstRes = session.createQuery("from empleados").list();
        t.commit();
        session.close();
        return lstRes;

    }

    @Override
    public Empleado findById(Long id) {
        Empleado empleado = null;
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        empleado = session.get(Empleado.class, id);
        t.commit();
        session.close();
        return empleado;

    }

}
