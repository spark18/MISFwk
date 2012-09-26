package com.shizhong.db.cmd.ligan;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.google.gson.Gson;
import com.shizhong.db.cmd.BaseAjaxServlet;
import com.shizhong.db.dao.PreTransaction;
import com.shizhong.db.util.HibernateUtil;

public class TingZiNanHuiPreviewTransactionServlet extends BaseAjaxServlet {

	private static final long serialVersionUID = -989293557544197178L;

	@SuppressWarnings("unused")
	private static class TransactionList {
		private String total;
		private String page = "1";
		private List<TransactionRow> rows = new ArrayList<TransactionRow>();

		public String getPage() {
			return page;
		}

		public List<TransactionRow> getRows() {
			return rows;
		}

		public String getTotal() {
			return total;
		}

		public void setPage(String page) {
			this.page = page;
		}

		public void setRows(List<TransactionRow> rows) {
			this.rows = rows;
		}

		public void addRow(TransactionRow row) {
			this.rows.add(row);
		}

		public void setTotal(String total) {
			this.total = total;
		}

		@Override
		public String toString() {
			return "TingZiList [total=" + total + ", page=" + page + ", rows="
					+ rows + "]";
		}

	}

	@SuppressWarnings("unused")
	private static class TransactionRow {
		private long id;
		private String[] cell;

		public String[] getCells() {
			return cell;
		}

		public long getId() {
			return id;
		}

		public void setCells(String[] cells) {
			this.cell = cells;
		}

		public void setId(long id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "TingZiRow [id=" + id + ", cells=" + Arrays.toString(cell)
					+ "]";
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		String name = req.getParameter("username");
		Query query = session
				.createQuery("from PreTransaction transaction where transaction.user.name='"
						+ name
						+ "' and transaction.type='"
						+ PreTransaction.Type.TINGZINANHUIPREVIEWLOAD.name() + "' order by transaction.timeStamp DESC");

		List tingziTransactionList = query.list();
		transaction.commit();
		session.close();

		TransactionList transactionList = new TransactionList();
		SimpleDateFormat format = new SimpleDateFormat("yyyyƒÍMM‘¬dd»’ HH:mm:ss");
		for (Object tingziTransactionResult : tingziTransactionList) {
			TransactionRow row = new TransactionRow();
			PreTransaction preTransaction = (PreTransaction) tingziTransactionResult;
			row.setId(preTransaction.getId());
			String[] cells = new String[] {
					format.format(preTransaction.getTimeStamp()),
					preTransaction.getComments(), preTransaction.getId().toString(), };
			row.setCells(cells);
			transactionList.addRow(row);
		}

		if (tingziTransactionList.isEmpty()) {
			transactionList.setTotal("0");
		} else {
			transactionList
					.setTotal(String.valueOf(tingziTransactionList.size()));
		}

		prepareResponse(resp);

		Gson convertor = new Gson();
		String json = convertor.toJson(transactionList);
		resp.getWriter().write(json);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
}
