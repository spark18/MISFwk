package com.shizhong.db.querier;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.BusTicketAir;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

class BusTicketAirViewQuerier implements Querier<BusTicketAir> {

    @SuppressWarnings("rawtypes")
    @Override
    public BusTicketAir retrieve(Map<String, Parameter> restrictions) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Criteria criteria = session.createCriteria(BusTicketAir.class);
        for (Parameter restriction : restrictions.values()) {
            if (restriction.getName().equals("id") && Long.parseLong(restriction.getValue().toString()) == -1l) {
                continue;
            }
            if (restriction.getName().equals("status") && restriction.getValue().toString().equals("as-is")) {
                continue;
            }
            criteria.add(Restrictions.eq(restriction.getName(), restriction.getValue()));
        }
        // criteria.add(Restrictions.eq("status", STATUS.normal.name()));
        List tickets = criteria.list();
        if (tickets.isEmpty()) {
            transaction.commit();
            session.close();
            return null;
        }
        BusTicketAir busticketAir = (BusTicketAir) (tickets.get(0));

        transaction.commit();
        session.close();

        return busticketAir;
    }

}
