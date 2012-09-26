package com.shizhong.db.imgupdater;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.google.gson.Gson;
import com.shizhong.db.dao.BusTicketAir;
import com.shizhong.db.dao.Displayable;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.meta.Parameter;
import com.shizhong.web.session.UserPool;

public class BusTicketAirImgUpdater implements ImgUpdater<BusTicketAir> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public boolean update(Map<String, Parameter> parameters, HttpServletRequest req) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        Criteria criteria = session.createCriteria(BusTicketAir.class);
        Parameter<Long> id = parameters.get("id");
        criteria.add(Restrictions.eq(id.getName(), id.getValue()));
        BusTicketAir busTicketAir = (BusTicketAir) criteria.uniqueResult();

        if (null == busTicketAir) {
            return false;
        }

        Displayable displayable = (Displayable) busTicketAir;

        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");

        List fileList = null;
        try {
            fileList = upload.parseRequest(req);
        } catch (FileUploadException ex) {
            return false;
        }

        Iterator<FileItem> it = fileList.iterator();
        Blob img = displayable.getImage();
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                img = session.getLobHelper().createBlob(new byte[(int) item.getSize()]);
                try {
                    img.setBytes(1, item.get());
                    // img.setBinaryStream(1).write(item.get());
                    // img.setBinaryStream(1).flush();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        // displayable.setImage(img);
        BusTicketAir air = (BusTicketAir) displayable;
        air.setStatus(STATUS.archive.name());
        session.save(displayable);
        // transaction.commit();

        BusTicketAir newAir = new BusTicketAir();
        newAir.setAirPrice(air.getAirPrice());
        newAir.setAutoSale(air.getAutoSale());
        newAir.setImage(img);
        newAir.setLine(air.getLine());
        newAir.setOpunit(air.getOpunit());
        newAir.setPricetype(air.getPricetype());
        newAir.setStatus(STATUS.normal.name());
        newAir.setType(air.getType());

        String user = req.getParameter("username");
        SZTransaction szTransaction = new SZTransaction();
        szTransaction.setUser(UserPool.getUser(user));
        szTransaction.setTimeStamp(new Date());
        Gson convertor = new Gson();
        Map<String, Object> modifiedFieldsMeta = new HashMap<String, Object>();
        modifiedFieldsMeta.put("entity", "busticketair");

        Set<String> modifiedFields = new HashSet<String>();
        modifiedFields.add("image");
        modifiedFieldsMeta.put("fields", modifiedFields);
        modifiedFieldsMeta.put("id", busTicketAir.getId());

        Parameter<String> username = parameters.get("username");
        modifiedFieldsMeta.put("modifier", username.getValue());
        szTransaction.setComments(convertor.toJson(modifiedFieldsMeta));

        szTransaction.setBusticketair(newAir);

        session.save(szTransaction);
        transaction.commit();
        session.close();

        return true;
    }

}
