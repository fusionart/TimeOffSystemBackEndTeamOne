package com.tos.timeoffserver.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tos.timeoffserver.domain.repositories.TimeOffRequestRepository;
import com.tos.timeoffserver.domain.entites.TimeOffRequest;

@Service
public class TimeOffRequestService {
	@Autowired
	private TimeOffRequestRepository requestRepository;

	public void approveRequest(TimeOffRequest request) {
		request.setStatus("approved");
		requestRepository.save(request);
	}

	// public Date getStartDate(Date date) {
	// // TODO Auto-generated method stub
	//
	// // return sql.date!!!
	// return null;
	// }
	//
	// public Date getFinishDate(Date date) {
	// // TODO Auto-generated method stub
	//
	// return sql.Date(date);
	// //return null;
	// }

	public int getTimeOffDays(Date startDate, Date finishDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int workingDays = 0;
		Date[] dates = orderDates(startDate, finishDate);
		// Date tempDate;
		 try {
		// if (startDate.after(finishDate)) {
		// tempDate = startDate;
		// startDate = finishDate;
		// finishDate = tempDate;
		// }
		Calendar start = Calendar.getInstance();
		start.setTime(dates[0]);
		Calendar end = Calendar.getInstance();
		end.setTime(dates[1]);
		while (!start.after(end)) {
			int day = start.get(Calendar.DAY_OF_WEEK);
			day = day + 2;
			if (day > 7) {
				day = day - 7;
			}
			if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY))
				workingDays++;
			start.add(Calendar.DATE, 1);
		}
	}catch(

	Exception e)
	{
		e.printStackTrace();
	}return workingDays;
	}

	public Date startDate(Date startDate, Date finishDate) {
		Date[] dates = orderDates(startDate, finishDate);
		return dates[0];
	}

	public Date finishDate(Date startDate, Date finishDate) {
		Date[] dates = orderDates(startDate, finishDate);
		return dates[1];
	}
	

	private Date[] orderDates(Date startDate, Date finishDate) {
		Date tempDate;
		Date[] dates = new Date[2];
		if (startDate.after(finishDate)) {
			tempDate = startDate;
			startDate = finishDate;
			finishDate = tempDate;
		}
		dates[0]=startDate;
		dates[1]=finishDate;
		return dates;
	}

}