package com.tos.timeoffserver.utilities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.tos.timeoffserver.domain.entites.Holiday;
import com.tos.timeoffserver.domain.repositories.HolidayRepository;

public class DateUtility {

	
	public String getDates(Date dateStart, Date dateFinish, Iterable<Holiday> holidayDates) {
		System.out.println("---------------------------------getDates-------------Inteval: " + dateStart + " - "
				+ dateFinish + "-------------------------------");
		List<java.util.Date> workdays = getWorkdays(dateStart, dateFinish, holidayDates);
		Calendar start = Calendar.getInstance();
		start.setTime(dateStart);
		Calendar end = Calendar.getInstance();
		end.setTime(dateFinish);
		String dates = "";
		if (getDifferenceDays(dateStart, dateFinish) == 0) {
			dates = start.get(Calendar.DAY_OF_MONTH) + " " + getMonthNameFromCalendar(start) + " "
					+ start.get(Calendar.YEAR);
			System.out.println(dates + " ------------------------> RESULT!");
			return dates;
		} else {
			dates = "" + start.get(Calendar.DAY_OF_MONTH);
		}
		for (int index = 0; index < workdays.size(); index++) {
			if (index == workdays.size() - 1) {
				String prefix = isDatesEquals(dateFinish, addDaysToDate(workdays.get(index - 1), 1))
						&& isSameMonth(workdays.get(index), workdays.get(index - 1)) ? "-" : "";
				dates = dates + prefix + end.get(Calendar.DAY_OF_MONTH) + " " + getMonthNameFromCalendar(end) + " "
						+ end.get(Calendar.YEAR);
				System.out.println(dates + " ------------------------> RESULT!");
				return dates;
			} else if (isDatesEquals(workdays.get(index + 1), addDaysToDate(workdays.get(index), 1))
					&& isSameMonth(workdays.get(index), workdays.get(index + 1))) {
				// skip if there are consecutive dates
				continue;
			} else {
				if (isSameMonth(workdays.get(index), workdays.get(index + 1))) {
					// same month
					String prefix = getDayOfMonth(workdays.get(index)) != 1 ? "-" : "";
					dates = dates + prefix + getDayOfMonth(workdays.get(index)) + ", ";
					if (!isDatesEquals(dateFinish, workdays.get(index + 1))) {
						// if next date is't last
						dates = dates + getDayOfMonth(workdays.get(index + 1));
					}
				} else if (!isSameMonth(workdays.get(index), workdays.get(index + 1))) {
					System.out.println(
							"RAZLICHNI MESECI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
					// different months
					if (!isSameYear(workdays.get(index), workdays.get(index + 1))) {
						// ...and a different year
						dates = dates + "-" + getDayOfMonth(workdays.get(index)) + " "
								+ getMonthNameFromDate(workdays.get(index)) + " " + getYear(workdays.get(index)) + ", ";
						if (!isDatesEquals(dateFinish, workdays.get(index + 1))) {
							dates = dates + getDayOfMonth(workdays.get(index + 1));
						}
					} else {
						if (isDatesEquals(dateStart, workdays.get(index))) {
							dates = dates + " " + getMonthNameFromDate(workdays.get(index)) + ", ";
							// /* if (!isDatesEquals(dateFinish, workdays.get(index + 1))) {
							// dates = dates + getDayOfMonth(workdays.get(index + 1));
							// }*/
							continue;
						}
						dates = dates + "-" + getDayOfMonth(workdays.get(index)) + " "
								+ getMonthNameFromDate(workdays.get(index)) + ", ";
						if (!isDatesEquals(dateFinish, workdays.get(index + 1))) {
							dates = dates + getDayOfMonth(workdays.get(index + 1));
						}
					}
				}
			}
		}
		System.out.println("Results: " + dates);
		return dates;
	}

	private List<java.util.Date> getWorkdays(Date dateStart, Date dateFinish, Iterable<Holiday> holidayDates) {
		List<java.util.Date> workdays = new ArrayList<java.util.Date>();
		for (java.util.Date date = dateStart; !date.after(dateFinish); date = addDaysToDate(date, 1)) {
			if (!isHoliday(date, holidayDates) && !isSaturday(date) && !isSunday(date)) {
				workdays.add(date);
			}
		}
		return workdays;
	}

	private int getDayOfMonth(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	private java.util.Date addDaysToDate(java.util.Date nextDay, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nextDay);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	private boolean isDatesEquals(java.util.Date dateOne, java.util.Date dateTwo) {
		Calendar calendarOne = Calendar.getInstance();
		Calendar calendarTwo = Calendar.getInstance();
		calendarOne.setTime(dateOne);
		calendarTwo.setTime(dateTwo);
		boolean isSameDay = calendarOne.get(Calendar.YEAR) == calendarTwo.get(Calendar.YEAR)
				&& calendarOne.get(Calendar.DAY_OF_YEAR) == calendarTwo.get(Calendar.DAY_OF_YEAR);
		return isSameDay;
	}

	private boolean isSameMonth(java.util.Date currentDay, java.util.Date nextDay) {
		Calendar calendarCurrentDay = Calendar.getInstance();
		Calendar calendarNextDay = Calendar.getInstance();
		calendarCurrentDay.setTime(currentDay);
		calendarNextDay.setTime(nextDay);
		boolean isSameMonth = calendarCurrentDay.get(Calendar.MONTH) == calendarNextDay.get(Calendar.MONTH);
		return isSameMonth;
	}

	private boolean isSameYear(java.util.Date currentDay, java.util.Date nextDay) {
		Calendar calendarCurrentDay = Calendar.getInstance();
		Calendar calendarNextDay = Calendar.getInstance();
		calendarCurrentDay.setTime(currentDay);
		calendarNextDay.setTime(nextDay);
		boolean isSameYear = calendarCurrentDay.get(Calendar.YEAR) == calendarNextDay.get(Calendar.YEAR);
		return isSameYear;
	}

	private boolean isSaturday(java.util.Date nextDay) {
		Calendar calendarNextDay = Calendar.getInstance();
		calendarNextDay.setTime(nextDay);
		int day = calendarNextDay.get(Calendar.DAY_OF_WEEK);
		if (day > 7) {
			day = day - 7;
		}
		return day == Calendar.SATURDAY;
	}

	private boolean isSunday(java.util.Date nextDay) {
		Calendar calendarNextDay = Calendar.getInstance();
		calendarNextDay.setTime(nextDay);
		int day = calendarNextDay.get(Calendar.DAY_OF_WEEK);
		if (day > 7) {
			day = day - 7;
		}
		return day == Calendar.SUNDAY;
	}

	private String getYear(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return "" + calendar.get(Calendar.YEAR);
	}


	private Calendar getCalendarForDate(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	private long getDifferenceDays(java.util.Date dateStart, java.util.Date dateFinish) {
		long diff = dateFinish.getTime() - dateStart.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	private String getMonthNameFromCalendar(Calendar date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		String monthName = englishMonthName(dateFormat.format(date.getTime()));
		return monthName;
	}

	private String getMonthNameFromDate(java.util.Date date) {
		return getMonthNameFromCalendar(getCalendarForDate(date));
	}

	private boolean isHoliday(java.util.Date date, Iterable<Holiday> holidayDates) {
		for (Holiday holiday : holidayDates) {
			if (isDatesEquals(holiday.getDate(), date)) {
				return true;
			}
		}
		return false;
	}
	
	private String englishMonthName (String monthNumber) {
		switch (monthNumber) {
		case "01": return "January";
		case "02": return "February";
		case "03": return "March";
		case "04": return "April";
		case "05": return "May";
		case "06": return "June";
		case "07": return "July";
		case "08": return "August";
		case "09": return "September";
		case "10": return "October";
		case "11": return "November";
		case "12": return "December";
		default: return monthNumber;
		}
	}
}
