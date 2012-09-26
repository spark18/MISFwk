package com.shizhong.db.cmd.line;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.google.gson.JsonObject;
import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.dao.BusTicket;
import com.shizhong.db.dao.BusTicketAir;
import com.shizhong.db.dao.BusTicketAirPreview;
import com.shizhong.db.dao.BusTicketPreview;
import com.shizhong.db.dao.EntityPreviewStatus;
import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.dao.STATUS;
import com.shizhong.db.dao.SZTransaction;
import com.shizhong.db.dao.UpLine;
import com.shizhong.db.dao.UpLinePreview;
import com.shizhong.db.dao.User;
import com.shizhong.db.util.HibernateUtil;
import com.shizhong.web.session.UserPool;

public class UpLinePreviewApproveServlet extends BaseAjaxServlet {

	private static final long serialVersionUID = 430195510740094785L;

	@SuppressWarnings("rawtypes")
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String approverName = req.getParameter("approver");
		String approverPasswd = req.getParameter("approverpasswd");

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Query query = session.createQuery("from User user where user.name='"
				+ approverName + "' and user.passwd='" + approverPasswd + "'");
		List users = query.list();

		transaction.commit();
		session.close();

		prepareResponse(resp);

		JsonObject returnStatus = new JsonObject();
		String retCode = SUCCESS;
		if (users.isEmpty()) {
			retCode = FAILURE;
		} else {
			User approver = (User) users.get(0);
			String role = approver.getRole();

			if (!User.Role.MANAGER.name().equals(role)) {
				retCode = FAILURE;
			}
		}

		if (FAILURE.equals(retCode)) {
			returnStatus.addProperty("retCode", retCode);
			resp.getWriter().write(convertor.toJson(returnStatus));
			resp.getWriter().flush();
			resp.getWriter().close();
			return;
		}
		String username = req.getParameter("username");
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		transaction.begin();
		query = session
				.createQuery("from UpLinePreview upLinePreview where upLinePreview.transaction.user.name='"
						+ username
						+ "' and upLinePreview.status='"
						+ EntityPreviewStatus.PREVIEW.name() + "'");
		List lineList = query.list();

		for (Object lineObj : lineList) {
			UpLinePreview uplinePreview = (UpLinePreview) lineObj;

			uplinePreview.setStatus(EntityPreviewStatus.COMMITTED.name());
			BusTicketPreview busTicket = uplinePreview.getBusTicketPreview();
			if (null != busTicket) {
				busTicket.setStatus(EntityPreviewStatus.COMMITTED.name());
				session.save(busTicket);
			}

			BusTicketAirPreview busTicketPreviewAir = uplinePreview
					.getBusTicketPreviewAir();
			if (null != busTicketPreviewAir) {
				busTicketPreviewAir.setStatus(EntityPreviewStatus.COMMITTED.name());
				session.save(busTicketPreviewAir);
			}
			session.save(uplinePreview);
			
			// session.evict(uplinePreview);
		}

		transaction.commit();
		session.close();

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		transaction.begin();

		List<UpLine> uplines = new ArrayList<UpLine>();
		for (Object liganObj : lineList) {
			UpLinePreview uplinePreview = (UpLinePreview) liganObj;
			UpLine upline = convert(uplinePreview);
			uplines.add(upline);

			SZTransaction szTrans = attachToTransaction(upline, username,
					(User) users.get(0));
			session.save(szTrans);

		}

		if(lineList.isEmpty()) {
		    returnStatus.addProperty("retCode", retCode);
	        resp.getWriter().write(convertor.toJson(returnStatus));
	        resp.getWriter().flush();
	        resp.getWriter().close();
	        return;
		}
		
		UpLinePreview linePreview = ((UpLinePreview) lineList.get(0));
		BusTicket ticket = convertTicket(linePreview.getBusTicketPreview());
		BusTicketAir tikAir = convertTicketAir(linePreview
				.getBusTicketPreviewAir());
		for (UpLine line : uplines) {
			line.setBusTicket(ticket);
			line.setBusTicketAir(tikAir);
			session.save(line);
		}

		transaction.commit();
		session.close();

		returnStatus.addProperty("retCode", retCode);
		resp.getWriter().write(convertor.toJson(returnStatus));
		resp.getWriter().flush();
		resp.getWriter().close();
	}

	private SZTransaction attachToTransaction(UpLine upline, String username,
			User approver) {
		Date now = new Date();
		SZTransaction transaction = new SZTransaction();
		transaction.setTimeStamp(now);
		User operator = UserPool.getUser(username);
		transaction.setUser(operator);
		transaction.setApprover(approver);
		transaction.setComments("�ύ��·��Ϣ  - [�����ˣ� " + operator.getName()
				+ " | ��ˣ�" + approver.getName() + "]");
		transaction.setType(PreTransaction.Type.UPLINEPREVIEWAPPROVE.name());
		transaction.setUpline(upline);
		return transaction;
	}

	private UpLine convert(UpLinePreview uplinePreview) {
		UpLine upline = new UpLine();
		upline.setAlias(uplinePreview.getAlias());
		upline.setLine(uplinePreview.getLine());
		upline.setName(uplinePreview.getName());
		upline.setStartend(uplinePreview.getStartend());
		upline.setStopaddress(uplinePreview.getStopAddress());
		upline.setStatus(STATUS.normal.name());
		upline.setLineindex(uplinePreview.getLineindex());

		return upline;
	}

	private BusTicket convertTicket(BusTicketPreview busTicketPreview) {
		if (null != busTicketPreview) {
		    String ticketline = busTicketPreview.getLine();
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(BusTicket.class);
            criteria.add(Restrictions.eq("line", ticketline));
            BusTicket existTicket = (BusTicket) criteria.uniqueResult();
            transaction.commit();
            session.close();
            
            if(null != existTicket) {
                return existTicket;
            }
            
			BusTicket ticket = new BusTicket();
			ticket.setAutoSale(busTicketPreview.getAutoSale());
			ticket.setImage(busTicketPreview.getImage());
			ticket.setLine(busTicketPreview.getLine());
			ticket.setNormPrice(busTicketPreview.getNormPrice());
			ticket.setOpunit(busTicketPreview.getOpunit());
			ticket.setPricetype(busTicketPreview.getPricetype());
			ticket.setType(busTicketPreview.getType());
			ticket.setStatus(STATUS.normal.name());
			return ticket;
		}
		return null;
	}

	private BusTicketAir convertTicketAir(
			BusTicketAirPreview busTicketPreviewAir) {
		if (null != busTicketPreviewAir) {
		    String ticketline = busTicketPreviewAir.getLine();
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(BusTicketAir.class);
            criteria.add(Restrictions.eq("line", ticketline));
            BusTicketAir existTicketAir = (BusTicketAir) criteria.uniqueResult();
            transaction.commit();
            session.close();
            
            if(null != existTicketAir) {
                return existTicketAir;
            }

			BusTicketAir ticketAir = new BusTicketAir();
			ticketAir.setAutoSale(busTicketPreviewAir.getAutoSale());
			ticketAir.setImage(busTicketPreviewAir.getImage());
			ticketAir.setLine(busTicketPreviewAir.getLine());
			ticketAir.setAirPrice(busTicketPreviewAir.getAirPrice());
			ticketAir.setOpunit(busTicketPreviewAir.getOpunit());
			ticketAir.setPricetype(busTicketPreviewAir.getPricetype());
			ticketAir.setType(busTicketPreviewAir.getType());
			ticketAir.setStatus(STATUS.normal.name());
			return ticketAir;
		}
		return null;
	}
}
