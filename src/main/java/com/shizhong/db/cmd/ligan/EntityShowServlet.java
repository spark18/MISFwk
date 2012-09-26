package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.cmd.EntityList;
import com.shizhong.db.cmd.EntityRow;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.querier.ModifiedFields;
import com.shizhong.db.querier.QuerierRepo;
import com.shizhong.web.bizmodel.EntityType;
import com.shizhong.web.meta.Command;
import com.shizhong.web.meta.Parameter;

public class EntityShowServlet extends BaseAjaxServlet {

    private static final long serialVersionUID = 430195510740094785L;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command cmd = new Command(getServletName(), getInitParameter("param-meta"), req.getParameterMap());

        Parameter<String> entityParam = cmd.getParameters().get("entity");
        QuerierRepo querierRepo = QuerierRepo.getInstance();
        EntityType entityType = cmd.entityType();

        Object entity = querierRepo.getQuerier(entityType).retrieve(cmd.getParameters());

        prepareResponse(resp);

        SimpleDateFormat format = new SimpleDateFormat("yyyyƒÍMM‘¬dd»’ HH:mm:ss", Locale.CHINESE);

        if (null != entity) {
            String json = "";
            // TODO remove hard code logic for SZTransaction
            if (entityType == EntityType.SZTransaction) {
                String entityName = entityParam.getValue().toString();
                String getterName = "get" + entityName.toUpperCase().charAt(0) + entityName.substring(1);
                EntityList elist = new EntityList();
                List transList = (List) entity;
                elist.setTotal(String.valueOf(transList.size()));
                for (Object info : transList) {
                    SZTransaction trans = (SZTransaction) info;
                    EntityRow row = new EntityRow();

                    String id = "0";
                    try {
                        Method getter = SZTransaction.class.getDeclaredMethod(getterName, (Class<?>[]) null);
                        Object value = getter.invoke(trans, (Object[]) null);

                        Method idGetter = value.getClass().getDeclaredMethod("getId", (Class<?>[]) null);
                        id = idGetter.invoke(value, (Object[]) null).toString();

                        String[] cells = new String[] { format.format(trans.getTimeStamp()), trans.getComments(),
                                String.valueOf(trans.getId()), id, entityName };
                        row.setCells(cells);
                        elist.addRow(row);
                        
                        ModifiedFields modifiedFields = null;
                        
                        modifiedFields = convertor.fromJson(trans.getComments(), ModifiedFields.class);
                        entityName = modifiedFields.getEntity().toLowerCase();
                        getterName = "get" + entityName.toUpperCase().charAt(0) + entityName.substring(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                json = convertor.toJson(elist);
            } else {
                json = convertor.toJson(entity);
            }
            resp.getWriter().write(json);
        } else {
            resp.getWriter().write(FAILURE);
        }
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
