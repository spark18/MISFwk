package com.shizhong.db.updater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.YangZhaoDianPreview;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;

public class YangZhaoDianPreviewUpdater implements Updater<YangZhaoDianPreview> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public YangZhaoDianPreview update(Map<String, Parameter> parameters) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Criteria criteria = session.createCriteria(YangZhaoDianPreview.class);
        Parameter<Long> id = parameters.get("id");
        criteria.add(Restrictions.eq(id.getName(), id.getValue()));
        YangZhaoDianPreview yangzhaodianPreview = (YangZhaoDianPreview) criteria.uniqueResult();

        if (null == yangzhaodianPreview) {
            return yangzhaodianPreview;
        }

        Parameter<String> area = parameters.get("area");
        Parameter<String> stopnum = parameters.get("stopnum");
        Parameter<String> entitynum = parameters.get("entitynum");
        Parameter<String> nanhuitingtype = parameters.get("nanhuitingtype");
        Parameter<String> picnumber = parameters.get("picnumber");
        Parameter<String> road = parameters.get("road");
        Parameter<String> address = parameters.get("address");
        Parameter<String> direction = parameters.get("direction");
        Parameter<String> lastCareDate = parameters.get("lastcaredate");
        Parameter<String> adop = parameters.get("adop");
        Parameter<String> adstart = parameters.get("adstart");
        Parameter<String> adend = parameters.get("adend");

        yangzhaodianPreview.setArea(area.getValue());
        yangzhaodianPreview.setStopnum(stopnum.getValue());
        yangzhaodianPreview.setEntitynum(entitynum.getValue());
        yangzhaodianPreview.setYangzhaodianType(nanhuitingtype.getValue());
        yangzhaodianPreview.setPicnumber(picnumber.getValue());
        yangzhaodianPreview.setRoad(road.getValue());
        yangzhaodianPreview.setAddr(address.getValue());
        yangzhaodianPreview.setDirection(direction.getValue());
        yangzhaodianPreview.setAdop(adop.getValue());

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy",
                Locale.CHINA);
        try {
            yangzhaodianPreview.setAdstart(formatter.parse(adstart.getValue()));
            yangzhaodianPreview.setAdend(formatter.parse(adend.getValue()));
            yangzhaodianPreview.setLastCareDate(formatter.parse(lastCareDate.getValue()));
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            session.save(yangzhaodianPreview);
            transaction.commit();
            session.close();
        }
        return yangzhaodianPreview;
    }

}
