package com.tos.timeoffserver.services;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tos.timeoffserver.domain.repositories.TimeOffRequestRepository;
import com.tos.timeoffserver.utilities.DateUtility;

import com.tos.timeoffserver.domain.entites.TimeOffRequest;

@Service
public class TimeOffRequestService {
	@Autowired
	private TimeOffRequestRepository requestRepository;

	DateUtility dateUtility = new DateUtility();

	public void approveRequest(TimeOffRequest request) {
		request.setStatus("approved");
		requestRepository.save(request);
	}
	
	public void cancelRequest(TimeOffRequest request) {
		request.setStatus("canceled");
		requestRepository.save(request);
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
		dates[0] = startDate;
		dates[1] = finishDate;
		return dates;
	}

}