package com.shizhong.db.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.shizhong.db.dao.EntityPreviewStatus;
import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.dao.TingZiNanHuiPreview;
import com.shizhong.db.dao.User;
import com.shizhong.db.util.HibernateUtil;

public class TingZiNanHuiConvertor implements InfoConvertor {

    private static final int AREA = 0;
    private static final int STOPNUM = 1;
    private static final int PICNUMBER = 2;
    private static final int ENTITYNUM = 3;
    private static final int NANHUITINGTYPE = 4;
    private static final int ADOP = 5;
    private static final int ADSTART = 6;
    private static final int ADEND = 7;
    private static final int ROAD = 8;
    private static final int STOP = 9;
    private static final int ADDR = 10;
    private static final int DIR = 11;
    private static final int LINE = 12;
    private static final int FINALSTOP = 13;
    private static final int NEXTSTOP = 14;
    private static final int DIGDATE = 15;
    private static final int FINISHDATE = 16;
    private static final int LASTCARE = 17;
    private static final int COMMENTS = 18;

    @Override
    public PreTransaction persist(BufferedReader reader, User curUser) throws IOException {
        String headerLine = reader.readLine();
        String[] headers = headerLine.split(",");
        checkHeaders(headers);

        String line = reader.readLine();
        if (null == line) {
            throw new RuntimeException("空数据表");
        }

        PreTransaction transaction = new PreTransaction();
        Date now = new Date();
        transaction.setTimeStamp(now);
        transaction.setUser(curUser);
        transaction.setComments("导入南汇亭信息 - [" + "操作人： " + curUser.getName() + "]");
        transaction.setType(PreTransaction.Type.TINGZINANHUIPREVIEWLOAD.name());
        while (null != line) {
            if (!isEmptyLine(line)) {
                addToReview(line, transaction);
                System.out.println(line);
            }
            line = reader.readLine();
        }
        return transaction;
    }

    private void addToReview(String line, PreTransaction tran) {
        String[] cells = line.split(",");
        if (cells.length < COMMENTS) {
            String[] newCells = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
            System.arraycopy(cells, 0, newCells, 0, cells.length);
            cells = newCells;
        }
        TingZiNanHuiPreview preview = new TingZiNanHuiPreview();
        preview.setTransaction(tran);

        preview.setArea(cells[AREA].trim());
        preview.setStopnum(cells[STOPNUM].trim());
        preview.setPicnumber(cells[PICNUMBER].trim());
        preview.setEntitynum(cells[ENTITYNUM].trim());
        preview.setNanhuitingType(cells[NANHUITINGTYPE].trim());
        preview.setAdop(cells[ADOP].trim());

        String adstartstr = cells[ADSTART].trim();
        if (null != adstartstr && !adstartstr.isEmpty()) {
            Date adStart = parseDate(adstartstr);
            preview.setAdstart(adStart);
        }

        String adendstr = cells[ADEND].trim();
        if (null != adendstr && !adendstr.isEmpty()) {
            Date adEnd = parseDate(adendstr);
            preview.setAdend(adEnd);
        }

        preview.setRoad(cells[ROAD].trim());
        preview.setStop(cells[STOP].trim());
        preview.setAddr(cells[ADDR].trim());
        preview.setDirection(cells[DIR].trim());
        preview.setLine(cells[LINE].trim());
        preview.setFinalstop(cells[FINALSTOP].trim());
        preview.setNextstop(cells[NEXTSTOP].trim());

        String digDateStr = cells[DIGDATE].trim();
        if (null != digDateStr && !digDateStr.isEmpty()) {
            Date digdate = parseDate(digDateStr);
            preview.setDigtime(digdate);
        }

        preview.setStatus(EntityPreviewStatus.PREVIEW.name());
        String finDateStr = cells[FINISHDATE].trim();
        if (null != finDateStr && !finDateStr.isEmpty()) {
            Date findate = parseDate(finDateStr);
            preview.setFinishdate(findate);
        }

        String lastCareDateStr = cells[LASTCARE].trim();
        if (null != lastCareDateStr && !lastCareDateStr.isEmpty()) {
            preview.setLastCareDate(parseDate(lastCareDateStr));
        }
        preview.setComments(cells[COMMENTS].trim());

        System.out.println(preview);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        session.save(preview);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings("deprecation")
    private Date parseDate(String digDateStr) {
        Date digdate = new Date();
        try {
            String[] nyr = digDateStr.split("\\.");
            digdate.setYear(Integer.parseInt(nyr[0]) - 1900);
            digdate.setMonth(Integer.parseInt(nyr[1]) - 1);
            digdate.setDate(Integer.parseInt(nyr[2]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return digdate;
    }

    private boolean isEmptyLine(String line) {
        if (null == line || line.isEmpty()) {
            return true;
        }
        String[] cells = line.split(",");
        if (cells.length == 0) {
            return true;
        }
        boolean isEmpty = true;
        for (String cell : cells) {
            if (!cell.isEmpty()) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    private void checkHeaders(String[] headers) {
        if (null == headers || headers.length == 0 || !"区域".equals(headers[AREA].trim())
                || !"站点编号".equals(headers[STOPNUM].trim()) || !"画面编号".equals(headers[PICNUMBER].trim())
                || !"设施编号".equals(headers[ENTITYNUM].trim()) || !"设施型号".equals(headers[NANHUITINGTYPE].trim())
                || !"广告客户/画面名称".equals(headers[ADOP].trim()) || !"画面上画日期".equals(headers[ADSTART].trim())
                || !"画面到期日期".equals(headers[ADEND].trim()) || !"路名".equals(headers[ROAD].trim())
                || !"站名".equals(headers[STOP].trim()) || !"地址".equals(headers[ADDR].trim())
                || !"车向".equals(headers[DIR].trim()) || !"线路".equals(headers[LINE].trim())
                || !"开往方向".equals(headers[FINALSTOP].trim()) || !"各线路下一站".equals(headers[NEXTSTOP].trim())
                || !"挖坑日期".equals(headers[DIGDATE].trim()) || !"完成日期".equals(headers[FINISHDATE].trim())
                || !"上次养护日期".equals(headers[LASTCARE].trim()) || !"备注".equals(headers[COMMENTS].trim())) {
            throw new RuntimeException("原始数据表头错误");
        }
    }
}
