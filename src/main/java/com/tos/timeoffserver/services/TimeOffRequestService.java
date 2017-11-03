package com.tos.timeoffserver.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tos.timeoffserver.domain.repositories.HolidayRepository;
import com.tos.timeoffserver.domain.repositories.TimeOffDatesRepository;
import com.tos.timeoffserver.domain.repositories.TimeOffRequestRepository;
import com.tos.timeoffserver.utilities.DateUtility;

import com.tos.timeoffserver.domain.entites.Holiday;
import com.tos.timeoffserver.domain.entites.TimeOffRequest;

@Service
// @Configurable
// @Component
public class TimeOffRequestService {
	@Autowired
	private TimeOffRequestRepository requestRepository;
	@Autowired
	private HolidayRepository holidayRepository;
	@Autowired
	private TimeOffDatesRepository datesRepository;

	DateUtility dateUtility = new DateUtility();

	public void approveRequest(TimeOffRequest request) {
		request.setStatus("approved");
		requestRepository.save(request);
	}

	public int getTimeOffDays(Date startDate, Date finishDate, int workingDays) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date[] dates = orderDates(startDate, finishDate);
		Iterable<Holiday> holidayDates = holidayRepository.findAll();
		try {
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
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return workingDays;
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

	public String getDates(Date[] dates) {
		Arrays.sort(dates);

		Date dateStart = dates[0];
		Date dateFinish = dates[dates.length - 1];
		System.out.println("---------------------------------getDates-------------Inteval: " + dateStart + " - "
				+ dateFinish + "-------------------------------");
		ArrayList<java.util.Date> workdays = new ArrayList<>(Arrays.asList(dates));
		// for (int index = 0; index < workdays.size(); index++) {
		// System.out.println(index + ": " + workdays.get(index));
		// }
		Calendar start = Calendar.getInstance();
		start.setTime(dateStart);
		Calendar end = Calendar.getInstance();
		end.setTime(dateFinish);
		String datesString = "";
		if (getDifferenceDays(dateStart, dateFinish) == 0) {
			datesString = start.get(Calendar.DAY_OF_MONTH) + " " + getMonthNameFromCalendar(start) + " "
					+ start.get(Calendar.YEAR);
			System.out.println(datesString + " ----> #1");
			System.out.println(datesString + " ------------------------> RESULT!");
			return datesString;
		} else {
			datesString = "" + start.get(Calendar.DAY_OF_MONTH);
			System.out.println(datesString + " ----> #0");
		}
		for (int index = 0; index < workdays.size(); index++) {
			System.out.println(index + ": " + workdays.get(index) + "String ******************* " + datesString);
			if (index == workdays.size() - 1) {
				// last date
				String prefix = isDatesEquals(dateFinish, addDaysToDate(workdays.get(index - 1), 1))
						&& isSameMonth(workdays.get(index), workdays.get(index - 1)) ? "-" : ", ";
				datesString = datesString + getPrefix(workdays.get(index - 1), workdays.get(index))
						+ end.get(Calendar.DAY_OF_MONTH) + " " + getMonthNameFromCalendar(end) + " "
						+ end.get(Calendar.YEAR);
				System.out.println(datesString + " ----------> #2");
				return datesString;
			} else {
				if (isDatesEquals(dateStart, workdays.get(index))) {
					// first date
					if (!isSameYear(workdays.get(index), workdays.get(index + 1))) {
						datesString = datesString + " " + getMonthNameFromDate(workdays.get(index)) + " "
								+ getYear(workdays.get(index)) + ", " + getDayOfMonth(workdays.get(index + 1));
						index++;
						System.out.println(datesString + " ----> #4");
						continue;
					}
					if (!isSameMonth(workdays.get(index), workdays.get(index + 1))) {
						// datesString = datesString + " " + getMonthNameFromDate(workdays.get(index));
						System.out.println(datesString + " ----> #3.0");
						continue;
					}
//					if (isDatesEquals(workdays.get(index + 1), addDaysToDate(workdays.get(index), 1))) {
//						continue;
//					}
//					String prefix = isDatesEquals(workdays.get(index + 1), addDaysToDate(workdays.get(index), 1))
//							? "-"
//							: "";
//					datesString = datesString + "";
//					System.out.println(datesString + " ----> #3");
					continue;
				} else {
					if (isDatesEquals(workdays.get(index - 1), addDaysToDate(workdays.get(index), -1))
							&& isDatesEquals(workdays.get(index + 1), addDaysToDate(workdays.get(index), 1))
							&& isSameMonth(workdays.get(index), workdays.get(index + 1))) {
						// skip if there are serial dates
						continue;
					}
					datesString = datesString + getPrefix(workdays.get(index - 1), workdays.get(index))
							+ getDayOfMonth(workdays.get(index));
					System.out.println("prefix: " + datesString);
				}
				// if (isSåparateDate(workdays.get(index - 1), workdays.get(index),
				// workdays.get(index + 1))) {
				// // a lonely date
				// System.out.println("lonely date");
				// if (isLastDateForMonth(workdays.get(index), workdays.get(index + 1))) {
				// datesString = datesString + ", " + getDayOfMonth(workdays.get(index + 1));
				// System.out.println(datesString + " ----> #4");
				// continue;
				// } else {
				// datesString = datesString + ", " + getDayOfMonth(workdays.get(index + 1));
				// System.out.println(datesString + " ----> #4");
				// continue;
				// }
				// }
				//
				// if (isSameMonth(workdays.get(index), workdays.get(index + 1))) {
				// // same month
				//
				// if (!isDatesEquals(workdays.get(index - 1),
				// addDaysToDate(workdays.get(index), -1))
				// && !isDatesEquals(workdays.get(index + 1), dateFinish)) {
				// // same month !fist !last
				//
				// }
				// if (getDayOfMonth(workdays.get(index)) == 1) {
				// continue;
				// }
				// String prefix = getDayOfMonth(workdays.get(index)) != 1 ? "-" : "";
				// datesString = datesString + prefix + getDayOfMonth(workdays.get(index)) + ",
				// ";
				// System.out.println(datesString + " ----> #5");
				// if (!isDatesEquals(dateFinish, workdays.get(index + 1))) {
				// // if next date is't last
				// datesString = datesString + getDayOfMonth(workdays.get(index + 1));
				// System.out.println(datesString + " ----> #6");
				// }
				// } else if (!isSameMonth(workdays.get(index), workdays.get(index + 1))) {
				// // different months
				// if (!isSameYear(workdays.get(index), workdays.get(index + 1))) {
				// // ...and a different year
				// if (!isDatesEquals(workdays.get(index - 1),
				// addDaysToDate(workdays.get(index), -1))) {
				// datesString = datesString + " " + getMonthNameFromDate(workdays.get(index)) +
				// ", "
				// + getYear(workdays.get(index)) + ", ";
				// continue;
				// }
				// datesString = datesString + "-" + getDayOfMonth(workdays.get(index)) + " "
				// + getMonthNameFromDate(workdays.get(index)) + " " +
				// getYear(workdays.get(index)) + ", ";
				// System.out.println(datesString + " ----> #7");
				// if (!isDatesEquals(dateFinish, workdays.get(index + 1))) {
				// datesString = datesString + getDayOfMonth(workdays.get(index + 1));
				// System.out.println(datesString + " ----> #8");
				// }
				// } else {
				// if (isDatesEquals(dateStart, workdays.get(index))) {
				// datesString = datesString + " " + getMonthNameFromDate(workdays.get(index)) +
				// ", ";
				// System.out.println(datesString + " ----> #9");
				// continue;
				// }
				// if (!isDatesEquals(workdays.get(index - 1),
				// addDaysToDate(workdays.get(index), -1))) {
				// datesString = datesString + " " + getMonthNameFromDate(workdays.get(index)) +
				// ", ";
				// continue;
				// }
				// datesString = datesString + "-" + getDayOfMonth(workdays.get(index)) + " "
				// + getMonthNameFromDate(workdays.get(index)) + ", ";
				// System.out.println(datesString + " ----> #10");
				// if (!isDatesEquals(dateFinish, workdays.get(index + 1))) {
				// datesString = datesString + getDayOfMonth(workdays.get(index + 1));
				// System.out.println(datesString + " ----> #11");
				// }
				// }
				// }
			}
		}
		System.out.println("Results: " + datesString);
		return datesString;
	}

	public String getDates2(Date[] dates) {
		Arrays.sort(dates);
		Date dateStart = dates[0];
		Date dateFinish = dates[dates.length - 1];
		System.out.println("---------------------------------getDates-------------Inteval: " + dateStart + " - "
				+ dateFinish + "-------------------------------");
		ArrayList<java.util.Date> workdays = new ArrayList<>(Arrays.asList(dates));
		// List<java.util.Date> workdays = getWorkdaysDates(timeOffRequest);
		Calendar start = Calendar.getInstance();
		start.setTime(dateStart);
		Calendar end = Calendar.getInstance();
		end.setTime(dateFinish);
		String datesString = "";
		// java.util.Date currentDay = dateStart;
		// java.util.Date nextDay = addDaysToDate(currentDay, 1);
		// java.util.Date theDayAfterNextDay = addDaysToDate(nextDay, 1);
		if (getDifferenceDays(dateStart, dateFinish) == 0) {
			datesString = start.get(Calendar.DAY_OF_MONTH) + " " + getMonthNameFromCalendar(start) + " "
					+ start.get(Calendar.YEAR);
			System.out.println(datesString + " ----> #1");
			System.out.println(datesString + " ------------------------> RESULT!");
			return datesString;
		} else {
			datesString = "" + start.get(Calendar.DAY_OF_MONTH);
		}
		for (int index = 0; index < workdays.size(); index++) {
			if (index == workdays.size() - 1) {
				// last date
				String prefix = isDatesEquals(dateFinish, addDaysToDate(workdays.get(index - 1), 1))
						&& isSameMonth(workdays.get(index), workdays.get(index - 1)) ? "-" : "";
				datesString = datesString + prefix + end.get(Calendar.DAY_OF_MONTH) + " "
						+ getMonthNameFromCalendar(end) + " " + end.get(Calendar.YEAR);
				System.out.println(datesString + " ------------------------> RESULT! #2");
				return datesString;
			} else if (isDatesEquals(workdays.get(index + 1), addDaysToDate(workdays.get(index), 1))
					&& isSameMonth(workdays.get(index), workdays.get(index + 1))) {
				// skip if there are consecutive dates
				continue;
			} else {
				if (isSameMonth(workdays.get(index), workdays.get(index + 1))) {
					// same month
					if (isDatesEquals(dateStart, workdays.get(index))) {
						// first date
						datesString = datesString + ", " + getDayOfMonth(workdays.get(index + 1));
						System.out.println(datesString + " ----> #3");
						continue;
					}
					if (!isDatesEquals(workdays.get(index - 1), addDaysToDate(workdays.get(index), -1))
							&& !isDatesEquals(workdays.get(index + 1), dateFinish)) {
						datesString = datesString + ", " + getDayOfMonth(workdays.get(index + 1));
						System.out.println(datesString + " ----> #4");
						continue;
					}
					if (getDayOfMonth(workdays.get(index)) == 1) {
						continue;
					}
					String prefix = getDayOfMonth(workdays.get(index)) != 1 ? "-" : "";
					datesString = datesString + prefix + getDayOfMonth(workdays.get(index)) + ", ";
					System.out.println(datesString + " ----> #5");
					if (!isDatesEquals(dateFinish, workdays.get(index + 1))) {
						// if next date is't last
						datesString = datesString + getDayOfMonth(workdays.get(index + 1));
						System.out.println(datesString + " ----> #6");
					}
				} else if (!isSameMonth(workdays.get(index), workdays.get(index + 1))) {
					// different months
					if (!isSameYear(workdays.get(index), workdays.get(index + 1))) {
						// ...and a different year
						if (!isDatesEquals(workdays.get(index - 1), addDaysToDate(workdays.get(index), -1))) {
							datesString = datesString + " " + getMonthNameFromDate(workdays.get(index)) + ", "
									+ getYear(workdays.get(index)) + ", ";
							continue;
						}
						datesString = datesString + "-" + getDayOfMonth(workdays.get(index)) + " "
								+ getMonthNameFromDate(workdays.get(index)) + " " + getYear(workdays.get(index)) + ", ";
						System.out.println(datesString + " ----> #7");
						if (!isDatesEquals(dateFinish, workdays.get(index + 1))) {
							datesString = datesString + getDayOfMonth(workdays.get(index + 1));
							System.out.println(datesString + " ----> #8");
						}
					} else {
						if (isDatesEquals(dateStart, workdays.get(index))) {
							datesString = datesString + " " + getMonthNameFromDate(workdays.get(index)) + ", ";
							System.out.println(datesString + " ----> #9");
							continue;
						}
						if (!isDatesEquals(workdays.get(index - 1), addDaysToDate(workdays.get(index), -1))) {
							datesString = datesString + " " + getMonthNameFromDate(workdays.get(index)) + ", ";
							continue;
						}
						datesString = datesString + "-" + getDayOfMonth(workdays.get(index)) + " "
								+ getMonthNameFromDate(workdays.get(index)) + ", ";
						System.out.println(datesString + " ----> #10");
						if (!isDatesEquals(dateFinish, workdays.get(index + 1))) {
							datesString = datesString + getDayOfMonth(workdays.get(index + 1));
							System.out.println(datesString + " ----> #11");
						}
					}
				}
			}
		}
		System.out.println("Results: " + datesString);
		return datesString;
	}

	private String getPrefix(java.util.Date previousDate, java.util.Date currentDate) {
		String prefix = "";
		String interval = isSåparateDate(previousDate, currentDate) ? ", " : "-";
		if (!isSameYear(currentDate, previousDate)) {
			prefix = prefix + " " + getMonthNameFromDate(currentDate) + " " + getYear(previousDate) + interval;
			System.out.println(currentDate + " -----1---- " + prefix);
			return prefix;
		}
		if (isSameMonth(previousDate, currentDate)) {
			prefix = prefix + interval;
			System.out.println(currentDate + " -----2---- " + prefix);
			return prefix;
		} else if (!isSameMonth(previousDate, currentDate)) {
			prefix = prefix + " " + getMonthNameFromDate(previousDate) + ", ";
			System.out.println(currentDate + " -----3---- " + prefix);
		}
		return prefix;
	}

	private boolean isLastDateForMonth(java.util.Date currentDate, java.util.Date nextDate) {
		return !isSameMonth(currentDate, nextDate);
	}

	private boolean isSåparateDate(java.util.Date previousDate, java.util.Date currentDate, java.util.Date nextDate) {
		return !isDatesEquals(previousDate, addDaysToDate(currentDate, -1))
				&& !isDatesEquals(nextDate, addDaysToDate(currentDate, 1));
	}

	private boolean isSåparateDate(java.util.Date previousDate, java.util.Date currentDate) {
		return !isDatesEquals(previousDate, addDaysToDate(currentDate, -1));
	}

	private List<java.util.Date> getWorkdays(Date dateStart, Date dateFinish) {
		List<java.util.Date> workdays = new ArrayList<java.util.Date>();
		for (java.util.Date date = dateStart; !date.after(dateFinish); date = addDaysToDate(date, 1)) {
			if (!isHoliday(date) && !isSaturday(date) && !isSunday(date)) {
				workdays.add(date);
			}
		}
		return workdays;
	}

	private java.util.Date getNextWorkday(java.util.Date theDayAfterNextDay) {
		do {
			theDayAfterNextDay = addDaysToDate(theDayAfterNextDay, 1);
		} while (isHoliday(theDayAfterNextDay));
		return theDayAfterNextDay;
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

	private boolean isSameMonth(java.util.Date currentDate, java.util.Date nextDate) {
		Calendar calendarCurrentDay = Calendar.getInstance();
		Calendar calendarNextDay = Calendar.getInstance();
		calendarCurrentDay.setTime(currentDate);
		calendarNextDay.setTime(nextDate);
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

	private int getMonthNumber(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
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

	private boolean isHoliday(java.util.Date date) {
		Iterable<Holiday> holidayDates = holidayRepository.findAll();
		for (Holiday holiday : holidayDates) {
			if (isDatesEquals(holiday.getDate(), date)) {
				return true;
			}
		}
		return false;
	}

	public List<java.util.Date> getWorkdaysDates(TimeOffRequest timeOffRequest) {
		// TODO Auto-generated method stub

		return null;
	}

	private String englishMonthName(String monthNumber) {
		switch (monthNumber) {
		case "01":
			return "January";
		case "02":
			return "February";
		case "03":
			return "March";
		case "04":
			return "April";
		case "05":
			return "May";
		case "06":
			return "June";
		case "07":
			return "July";
		case "08":
			return "August";
		case "09":
			return "September";
		case "10":
			return "October";
		case "11":
			return "November";
		case "12":
			return "December";
		default:
			return monthNumber;
		}
	}
}