package com.shizhong.db.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.shizhong.db.dao.EntityPreviewStatus;
import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.dao.TingZiPreview;
import com.shizhong.db.dao.User;
import com.shizhong.db.util.HibernateUtil;

public class TingZiConvertor implements InfoConvertor {

    private static final int FACTORY = 12;
    private static final int TYPE = 11;
    private static final int FINISHDATE = 10;
    private static final int NEXTSTOP = 9;
    private static final int FINALSTOP = 8;
    private static final int DIGDATE = 7;
    private static final int LINE = 6;
    private static final int DIR = 5;
    private static final int ADDR = 4;
    private static final int STOP = 3;
    private static final int ROAD = 2;
    private static final int NUMBER = 1;
    private static final int AREA = 0;

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
        transaction.setComments("导入亭子信息 - [" + "操作人： " + curUser.getName() + "]");
        transaction.setType(PreTransaction.Type.TINGZIPREVIEWLOAD.name());
        while (null != line) {
            if (!isEmpltyLine(line)) {
                addToReview(line, transaction);
                System.out.println(line);
            }
            line = reader.readLine();
        }
        return transaction;
    }

    @SuppressWarnings("deprecation")
    private void addToReview(String line, PreTransaction tran) {
        String[] cells = line.split(",");
        if(cells.length <= FINISHDATE) {
            String[] newCells = {"", "", "", "", "", "", "", "", "", "", "", "", ""};
            System.arraycopy(cells, 0, newCells, 0, cells.length);
            cells = newCells;
        }
        TingZiPreview preview = new TingZiPreview();
        preview.setTransaction(tran);

        preview.setArea(cells[AREA].trim());
        preview.setNumber(cells[NUMBER].trim());
        preview.setRoad(cells[ROAD].trim());
        preview.setStop(cells[STOP].trim());
        preview.setAddr(cells[ADDR].trim());
        preview.setDirection(cells[DIR].trim());
        preview.setLine(cells[LINE].trim());
        String digDateStr = cells[DIGDATE].trim();
        Date digdate = null;
        try {
            String[] nyr = digDateStr.split("\\.");
            digdate = new Date();
            digdate.setYear(Integer.parseInt(nyr[0]) - 1900);
            digdate.setMonth(Integer.parseInt(nyr[1]) - 1);
            digdate.setDate(Integer.parseInt(nyr[2]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        preview.setDigdate(digdate);
        preview.setFinalstop(cells[FINALSTOP].trim());
        preview.setNextstop(cells[NEXTSTOP].trim());
        preview.setStatus(EntityPreviewStatus.PREVIEW.name());
        String finDateStr = cells[FINISHDATE].trim();
        Date findate = null;
        try {
            String[] nyr = finDateStr.split("\\.");
            findate = new Date();
            findate.setYear(Integer.parseInt(nyr[0]) - 1900);
            findate.setMonth(Integer.parseInt(nyr[1]) - 1);
            findate.setDate(Integer.parseInt(nyr[2]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        preview.setType(cells[TYPE].trim());
        preview.setFactory(cells[FACTORY].trim());
        preview.setFinishdate(findate);
        System.out.println(preview);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        session.save(preview);
        transaction.commit();
        session.close();
    }

    private boolean isEmpltyLine(String line) {
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
        if (null == headers || headers.length == 0 || !"区域".equals(headers[0].trim())
                || !"铭牌号".equals(headers[1].trim()) || !"路名".equals(headers[2].trim())
                || !"站名".equals(headers[3].trim()) || !"地址".equals(headers[4].trim())
                || !"车向".equals(headers[5].trim()) || !"路线".equals(headers[6].trim())
                || !"挖坑日期".equals(headers[7].trim()) || !"开往方向".equals(headers[8].trim())
                || !"线路下一站".equals(headers[9].trim()) || !"完成日期".equals(headers[10].trim())) {
            throw new RuntimeException("原始数据表头错误");
        }
    }
}
