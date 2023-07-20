package com.shashi.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shashi.beans.TrainBean;
import com.shashi.beans.TrainException;
import com.shashi.service.TrainService;
import com.shashi.service.impl.TrainServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/adminupdatetrain")
public class AdminBusUpdate extends HttpServlet {

	private TrainService trainService = new TrainServiceImpl();

	/**
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		try {
			String trainNo = req.getParameter("trainnumber");
			TrainBean train = trainService.getTrainById(trainNo);
			if (train != null) {
				RequestDispatcher rd = req.getRequestDispatcher("AdminHome.html");
				rd.include(req, res);
				pw.println("<div class='tab'>Bus Schedule Update</div>");
				pw.println("<div class='tab'>" + "<table><form action='updatetrainschedule' method='post'>"
						+ "<tr><td>Bus No :</td><td><input type='text' name='trainno' value='" + train.getTr_no()
						+ "'></td></tr>" + "<tr><td>Bus Name :</td><td><input type='text' name='trainname' value='"
						+ train.getTr_name() + "'></td></tr>"
						+ "<tr><td>From Bus Depot :</td><td><input type='text' name='fromstation' value='"
						+ train.getFrom_stn() + "'></td></tr>"
						+ "<tr><td>To Bus Depot :</td><td><input type='text' name='tostation' value='" + train.getTo_stn()
						+ "'></td></tr>"
						+ "<tr><td>Available seats:</td><td><input type='text' name='available' value='"
						+ train.getSeats() + "'></td></tr>"
						+ "<tr><td>Fare (LKR) :</td><td><input type='text' name='fare' value='" + train.getFare()
						+ "'></td></tr>"
						+ "<tr><td></td><td><input type='submit' name='submit' value='Update Bus Schedule'></td></tr>"
						+ "</form></table>" + "</div>");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("AdminUpdateTrain.html");
				rd.include(req, res);
				pw.println("<div class='tab'>Bus Not Available</div>");
			}
		} catch (Exception e) {
			throw new TrainException(422, this.getClass().getName() + "_FAILED", e.getMessage());

		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}

}
