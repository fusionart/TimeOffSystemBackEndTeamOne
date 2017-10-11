package com.tos.timeoffserver.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


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

	public Date getStartDate(String startDate) {
		// TODO Auto-generated method stub

		// return sql.date!!!
		return null;
	}

	public Date getFinishDate(String finishDate) {
		// TODO Auto-generated method stub

		// return sql.date!!!
		return null;
	}

	public int getTimeOffDays(String startDate, String finishDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int workingDays = 0;
		try {
			Calendar start = Calendar.getInstance();
			start.setTime(dateFormat.parse(startDate));
			Calendar end = Calendar.getInstance();
			end.setTime(dateFormat.parse(finishDate));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workingDays;
	}

}