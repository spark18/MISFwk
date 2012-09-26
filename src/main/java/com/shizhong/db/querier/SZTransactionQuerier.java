package com.shizhong.db.querier;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

class SZTransactionQuerier implements Querier<List<SZTransaction>> {
    @SuppressWarnings({ "rawtypes" })
    @Override
    public List<SZTransaction> retrieve(Map<String, Parameter> restrictions) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Criteria criteria = session.createCriteria(SZTransaction.class);
        Parameter idParam = restrictions.remove("id");
        Parameter entityParam = restrictions.remove("entity");
        String entity = entityParam.getValue().toString();
        criteria.add(Restrictions.eq(entity + ".id", idParam.getValue()));
        for (Parameter restriction : restrictions.values()) {
            criteria.add(Restrictions.eq(restriction.getName(), restriction.getValue()));
        }
        criteria.addOrder(Order.desc("timeStamp"));
        List<SZTransaction> szTransactions = new ArrayList<SZTransaction>();
        SZTransaction szTransaction = (SZTransaction) criteria.uniqueResult();
        Gson convertor = new Gson();
        try {
            while (szTransaction != null) {
                szTransactions.add(szTransaction);
                Method entityGetter = SZTransaction.class.getDeclaredMethod("get" + entity.toUpperCase().charAt(0)
                        + entity.substring(1), (Class<?>[])null);
                Object entityValue = entityGetter.invoke(szTransaction, (Object[])null);
                if (entityValue == null) {
                    break;
                }
                ModifiedFields modifiedFields = null;

                modifiedFields = convertor.fromJson(szTransaction.getComments(), ModifiedFields.class);
                String modifiedEntity = modifiedFields.getEntity().toLowerCase();
                String id = modifiedFields.getId();
                criteria = session.createCriteria(SZTransaction.class);
                criteria.add(Restrictions.eq(modifiedEntity + ".id", Long.parseLong(id)));
                szTransaction = (SZTransaction) criteria.uniqueResult();
                entity = modifiedFields.getEntity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        transaction.commit();
        session.close();

        return szTransactions;
    }

}
