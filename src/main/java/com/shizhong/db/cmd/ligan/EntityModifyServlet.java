package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.updater.UpdaterRepo;
import com.shizhong.web.bizmodel.EntityType;
import com.shizhong.web.meta.Command;

public class EntityModifyServlet extends BaseAjaxServlet {

    private static final long serialVersionUID = 430195510740094785L;

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command cmd = new Command(getServletName(), getInitParameter("param-meta"), req.getParameterMap());

        UpdaterRepo updatersRepo = UpdaterRepo.getInstance();
        EntityType entityType = cmd.entityType();

        Object success = updatersRepo.getUpdater(entityType).update(cmd.getParameters());
        Map<String, Object> returnStatus = new HashMap<String, Object>();
        String retCode = SUCCESS;
        if (null == success) {
            retCode = FAILURE;
        } else {
            returnStatus.put("entity", success);
        }
        returnStatus.put("retCode", retCode);

        prepareResponse(resp);
        resp.getWriter().write(convertor.toJson(returnStatus));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
