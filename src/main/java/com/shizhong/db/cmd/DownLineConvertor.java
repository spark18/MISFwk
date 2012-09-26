package com.shizhong.db.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.shizhong.db.dao.BusTicketAirPreview;
import com.shizhong.db.dao.BusTicketPreview;
import com.shizhong.db.dao.DownLinePreview;
import com.shizhong.db.dao.EntityPreviewStatus;
import com.shizhong.db.dao.IBusTicketPreview;
import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.dao.User;
import com.shizhong.db.util.HibernateUtil;

public class DownLineConvertor implements InfoConvertor {

	private static final int STARTEND = 3;
	private static final int STOPADDR = 2;
	private static final int ALIAS = 1;
	private static final int NAME = 0;

	private static final int BTYPE = 0;
	private static final int NORM = 1;
	private static final int AIR = 3;
	private static final int TTYPE = 4;
	private static final int NOSALE = 5;
	private static final int OPUNIT = 6;
	private static final int LINE = 7;

	@Override
	public PreTransaction persist(BufferedReader reader, User curUser)
			throws IOException {
		String headerLine = reader.readLine();
		String[] headers = headerLine.split(",");
		checkBusTicketHeaders(headers);

		IBusTicketPreview[] busTickets = parseBusTicket(reader);
		if(null == busTickets || busTickets.length == 0) {
			return null;
		}

		headerLine = reader.readLine();
		headers = headerLine.split(",");
		checkStopHeaders(headers);

		PreTransaction transaction = new PreTransaction();
		Date now = new Date();
		transaction.setTimeStamp(now);
		transaction.setUser(curUser);
		transaction.setComments("导入线路 (" + busTickets[0].getLine() + ") - [" + "操作人： "
				+ curUser.getName() + "]");
		transaction.setType(PreTransaction.Type.DOWNLINEPREVIEWLOAD.name());

		String line = reader.readLine();
		int index = 0;
		while (null != line) {
			if (!isEmpltyLine(line)) {
				addToReview(index++, line, transaction, busTickets);
				System.out.println(line);
			}
			line = reader.readLine();
		}
		return transaction;
	}

	private IBusTicketPreview[] parseBusTicket(BufferedReader reader)
			throws IOException {
		String line = reader.readLine();
		if (null == line) {
			throw new RuntimeException("空数据表");
		}

		List<IBusTicketPreview> busTicketPreviews = new ArrayList<IBusTicketPreview>();
		while (!isEmpltyLine(line)) {
			busTicketPreviews.add(convert2BusTicketPreview(line));
			System.out.println(line);
			line = reader.readLine();
		}

		return busTicketPreviews.toArray(new IBusTicketPreview[0]);
	}

	private IBusTicketPreview convert2BusTicketPreview(String line) {
		String[] cells = line.split(",");
		String bustype = cells[BTYPE].trim();
		IBusTicketPreview preview = null;
		if ("空调车".equals(bustype)) {
			preview = new BusTicketAirPreview();
		} else {
			preview = new BusTicketPreview();
		}

		Double normPrice = 1.0;
		try {
			normPrice = Double.parseDouble(cells[NORM].trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		preview.setNormPrice(normPrice);

		Double airPrice = 2.0;
		try {
			airPrice = Double.parseDouble(cells[AIR].trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		preview.setAirPrice(airPrice);

		String pType = "single";
		if("单一".equals(cells[TTYPE].trim())) {
			pType = "single";
		} else {
			pType = "multi";
		}
		preview.setPricetype(pType);
		String autosale = "y";
		if("是".equals(cells[NOSALE].trim())) {
			autosale = "y";
		} else {
			autosale = "n";
		}
		preview.setAutoSale(autosale);
		preview.setOpunit(cells[OPUNIT].trim());
		preview.setLine(cells[LINE].trim());
		if("空调车".equals(bustype)) {
			bustype = "air";
		} else {
			bustype = "norm";
		}
		preview.setType(bustype);
		preview.setStatus(EntityPreviewStatus.PREVIEW.name());
		System.out.println(preview);

		return preview;
	}

	private void addToReview(int index, String line, PreTransaction tran,
			IBusTicketPreview[] busTickets) {
		String[] cells = line.split(",");
		DownLinePreview preview = new DownLinePreview();
		preview.setTransaction(tran);
		preview.setLineindex(index);

		preview.setName(cells[NAME]);
		preview.setStatus(EntityPreviewStatus.PREVIEW.name());

		if (ALIAS < cells.length) {
			preview.setAlias(cells[ALIAS]);
		}

		if (STOPADDR < cells.length) {
			preview.setStopAddress(cells[STOPADDR]);
		}

		if (STARTEND < cells.length) {
			preview.setStartend(cells[STARTEND]);
		}
		
		if (STARTEND < cells.length) {
			preview.setStartend(cells[STARTEND]);
		}
		
		preview.setLine(busTickets[0].getLine());
		
		System.out.println(preview);

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (int i = 0; i < busTickets.length; i++) {
			IBusTicketPreview busPreview = busTickets[0];
			String busline = busPreview.getLine();
			if (busPreview instanceof BusTicketAirPreview) {
				Criteria criteria = session
						.createCriteria(BusTicketAirPreview.class);
				criteria.add(Restrictions.eq("line", busline));
				BusTicketAirPreview existBusPreview = (BusTicketAirPreview) criteria
						.uniqueResult();
				if (null != existBusPreview) {
					preview.setBusTicketPreviewAir(existBusPreview);
				} else {
					preview.setBusTicketPreviewAir((BusTicketAirPreview) busPreview);
				}
			} else {
				Criteria criteria = session
						.createCriteria(BusTicketPreview.class);
				criteria.add(Restrictions.eq("line", busline));
				BusTicketPreview existBusPreview = (BusTicketPreview) criteria
						.uniqueResult();
				if (null != existBusPreview) {
					preview.setBusTicketPreview(existBusPreview);
				} else {
					preview.setBusTicketPreview((BusTicketPreview) busPreview);
				}
			}
		}

		
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

	private void checkStopHeaders(String[] headers) {
		if (null == headers || headers.length == 0
				|| !"下行站名".equals(headers[0].trim())
				|| !"副站名".equals(headers[1].trim())
				|| !"站址".equals(headers[2].trim())
				|| !"首末班时刻".equals(headers[3].trim())) {
			throw new RuntimeException("原始数据表头错误");
		}
	}

	private void checkBusTicketHeaders(String[] headers) {
		if (null == headers || headers.length == 0
				|| !"车型".equals(headers[0].trim())
				|| !"普通车票价".equals(headers[1].trim())
				|| !"多级票价".equals(headers[2].trim())
				|| !"空调车票价".equals(headers[3].trim())
				|| !"票价类型".equals(headers[4].trim())
				|| !"无人售票".equals(headers[5].trim())
				|| !"运营单位".equals(headers[6].trim())
				|| !"线路".equals(headers[7].trim())
				) {
			throw new RuntimeException("原始数据表头错误");
		}
	}
}
