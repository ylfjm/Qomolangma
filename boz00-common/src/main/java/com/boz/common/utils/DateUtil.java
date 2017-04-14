package com.boz.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 *
 * @version 1.0
 * @creator zhangqiang
 * @create-time May 13, 2015   5:58:30 PM
 */
public class DateUtil {

    /**
     * 格式化年月字符串
     */
    public static String fmtYYYYMMM = "yyyyMM";
    public static String fmtYYYYMMDDHH = "yyyyMMddHH";
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm, MMM d, yyyy", Locale.ENGLISH);
    private static SimpleDateFormat sdf4 = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
    private static String[] weeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static Calendar calendar = Calendar.getInstance(Locale.CHINA);

    public static String chineseDateFormat(Date date) {
        return sdf1.format(date);
    }
    public static String chineseDateFormatWithoutTime(Date date) {
        return sdf2.format(date);
    }

    public static String enDateFormat(Date date) {
        if (date == null) {
            return "";
        }
        return sdf3.format(date);
    }
    public static String enDateFormatWithoutTime(Date date) {
        if (date == null) {
            return "";
        }
        return sdf3.format(date);
    }
    public static String enDateFormatWithoutTimeWithDaySuffix(Date date) {
        if (date == null) {
            return "";
        }
        String pattern = "MMM d {0}, yyyy";
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        switch (day) {
            case 1:
            case 21:
            case 31:
                pattern = MessageFormat.format(pattern, "'st'");
                break;
            case 2:
            case 22:
                pattern = MessageFormat.format(pattern, "'nd'");
                break;
            case 3:
            case 23:
                pattern = MessageFormat.format(pattern, "'rd'");
                break;
            default:
                pattern = MessageFormat.format(pattern, "'th'");
                break;
        }
        SimpleDateFormat enSdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        return enSdf.format(date);
    }

    public static String formatDate(Date d, String pattern) {
        if (d == null) {
            return "";
        }
        sdf.applyPattern(pattern);
        return sdf.format(d);
    }


    public static String format(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String cnDateFormat(Date date) {
        if(date == null) {
            return "";
        }
        return sdf1.format(date);
    }
    public static String cnDateFormatWithoutTime(Date date) {
        if(date == null) {
            return "";
        }
        return sdf2.format(date);
    }

    public static Date parseDate(String date, String pattern) {
		sdf.applyPattern(pattern);
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }

    /**
     * 获取整点日期
     *
     * @param date
     * @return
     */
    public static Date getDayZeroTime(Date date) {
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    public static Date parseDate(String date, String pattern, Locale locale) {
        Date result = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
            result = simpleDateFormat.parse(date);
            return result;
        } catch (ParseException e) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", locale);
                result = simpleDateFormat.parse(date);
                return result;
            } catch (ParseException e1) {
                e1.printStackTrace();
                return result;
            }
        }

    }

    /**
     * 获取是周几 1表示周1 0表示周日
     *
     * @param d
     * @return
     */
    public static int getChineseWeekNum(Date d) {
        calendar.setTime(d);

        int week = calendar.get(Calendar.DAY_OF_WEEK);

        week = week - 1;    //默认周日为1

        return week;
    }

    /**
     * 获取星期几
     *
     * @param num
     * @return
     */
    public static String getChineseWeekNameByNum(int num) {
        return weeks[num];
    }

    /**
     * 获取年份
     *
     * @param now
     * @return
     */
    public static int getYear(Date now) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(now);
        int year = c.get(Calendar.YEAR);

        return year;
    }

    /**
     * 判断是否超过多少分钟
     *
     * @param start
     * @param end
     * @param subMinute 相差的分钟
     * @return boolean
     */
    public static boolean judgmentDate(Date start, Date end, int subMinute) {
        long cha = end.getTime() - start.getTime();
        if (cha < 0) {
            return false;
        }
        double result = cha * 1.0 / (1000 * 60);
        if (result <= subMinute) {
            return true;
        }
        return false;
    }

    /**
     * 获取
     *
     * @param now
     * @return
     */
    public static int getPrizeWeekByTime(Date now) {
        Date compareDate = getDayZeroTime(now);
        int week = 1;    //默认第一周
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(new Date());
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 12);    //一月12日
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        //	Date firstWeekBegin = c.getTime();
        c.set(Calendar.DAY_OF_MONTH, 18);    //一月18日
        Date firstWeekEnd = c.getTime();
        //	c.set(Calendar.DAY_OF_MONTH, 19);	//一月19日
        //	Date secondWeekBegin = c.getTime();
        c.set(Calendar.DAY_OF_MONTH, 25);    //一月25日
        Date secondWeekEnd = c.getTime();
        //	c.set(Calendar.DAY_OF_MONTH, 26);	//一月26日
        //	Date thirdWeekBegin = c.getTime();
        c.set(Calendar.MONDAY, 1);    //二月
        c.set(Calendar.DAY_OF_MONTH, 1);    //二月1日
        Date thirdWeekEnd = c.getTime();
        //	c.set(Calendar.DAY_OF_MONTH, 2);	//二月2日
        //	Date fourthWeekBegin = c.getTime();
        c.set(Calendar.DAY_OF_MONTH, 8);    //二月8日
        Date fourthWeekEnd = c.getTime();
        //	c.set(Calendar.DAY_OF_MONTH, 9);	//二月9日
        //	Date fifthWeekBegin = c.getTime();
        //	c.set(Calendar.DAY_OF_MONTH, 15);	//二月15日
        //	Date fifthWeekEnd = c.getTime();


        if (!compareDate.after(firstWeekEnd)) {
            week = 1;
        } else if (!compareDate.after(secondWeekEnd)) {
            week = 2;
        } else if (!compareDate.after(thirdWeekEnd)) {
            week = 3;
        } else if (!compareDate.after(fourthWeekEnd)) {
            week = 4;
        } else {
            week = 5;
        }


        return week;

    }

    /**
     * 计算两个日期之间相差几天
     * dateformat:yyyy-MM-dd
     *
     * @param begin
     * @param end
     * @return
     * @throws ParseException
     * @author hys
     */
    public static Integer calculateDateDay(Date begin, Date end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateBegin = sdf.format(begin);
        String dateEnd = sdf.format(end);
        try {
            begin = sdf.parse(dateBegin);
            end = sdf.parse(dateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long date = begin.getTime() - end.getTime();
        long result = date / (1000 * 3600 * 24);
        return Integer.valueOf(String.valueOf(Math.abs(result)));
    }

    /**
     * 计算两个日期之间相差几个月
     * dateformat:yyyy-MM
     *
     * @param begin
     * @param end
     * @return
     * @throws ParseException
     * @author hys
     */
    public static Integer calculateDateMonth(Date begin, Date end) {
        Calendar beginCal = Calendar.getInstance();
        beginCal.setTime(begin);
        int sYear = beginCal.get(Calendar.YEAR);
        int sMonth = beginCal.get(Calendar.MONTH);
        int sDay = beginCal.get(Calendar.DAY_OF_MONTH);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(end);
        int eYear = endCal.get(Calendar.YEAR);
        int eMonth = endCal.get(Calendar.MONTH);
        int eDay = endCal.get(Calendar.DAY_OF_MONTH);
        long monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));
        if (sDay < eDay) {
            monthday = monthday + 1;
        }
        return Integer.valueOf(String.valueOf(monthday));
    }

    /**
     * 计算两个日期之间相差几年
     * dateformat:yyyy
     *
     * @param begin
     * @param end
     * @return
     * @throws ParseException
     * @author hys
     */
    public static Integer calculateDateYear(Date begin, Date end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        int beginDate = Integer.valueOf(sdf.format(begin));
        int endDate = Integer.valueOf(sdf.format(end));
        return Math.abs(beginDate - endDate);
    }

    public static Date hourAfter(Date date, int h) {
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, h);
        return calendar.getTime();
    }
    
    /**
     * 获取一天前的日期时间
     * @param d
     * @return
     */
    public static Date dayBefore(Date date) {
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
    
    /**
     * 获取一周前的日期时间
     * @param d
     * @return
     */
    public static Date weekBefore(Date date) {
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
        return calendar.getTime();
    }
    
    /**
     * 获取一月前的日期时间
     * @param d
     * @return
     */
    public static Date monthBefore(Date date) {
    	calendar.setTime(date);
    	calendar.add(Calendar.MONTH, -1);
    	return calendar.getTime();
    }
    
    /**
     * 获取一季度前的日期时间
     * @param d
     * @return
     */
    public static Date quarterBefore(Date date) {
    	calendar.setTime(date);
    	calendar.add(Calendar.MONTH, -3);
    	return calendar.getTime();
    }
    
    public static void main(String[] args) {
        String begin = "1995";
        String end = "2015";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            System.out.println(DateUtil.calculateDateYear(sdf.parse(begin), sdf.parse(end)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//		String begin = "2014-01-02";
//		String end = "2015-10-02";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//		try {
//			System.out.println(DateUtil.calculateDateMonth(sdf.parse(begin), sdf.parse(end)));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    
    public static int getMonthOfYear(Date nowDate) {
        calendar.setTime(nowDate);
        return calendar.get(Calendar.MONTH) + 1;
    }
    
    public static int getDayOfMonth(Date nowDate) {
        calendar.setTime(nowDate);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getHourMinuteOfDay(Date nowDate) {
    	calendar.setTime(nowDate);
    	String minuteStr = calendar.get(Calendar.HOUR_OF_DAY) + "" + calendar.get(Calendar.MINUTE);
    	return Integer.parseInt(minuteStr);
    }

    /**
     * 获取当前时分，例如：12点30分（1230），12点5分（1205）
     * @param nowDate
     * @return
     */
    public static int getHourMinuteOfDayWithZore(Date nowDate) {
    	calendar.setTime(nowDate);
    	int hour = calendar.get(Calendar.HOUR_OF_DAY);
    	int minute = calendar.get(Calendar.MINUTE);
        String minuteStr;
        if(minute < 10) {
            minuteStr = hour + "0" + minute;
        } else {
            minuteStr = hour + "" + minute;
        }
    	return Integer.parseInt(minuteStr);
    }

    public static boolean checkDay(Date nowDate, Integer minute) {
    	// 如果清算检查时间间隔设置为一分钟的话，则判断当前时间和设置的时间间隔是不是在一分钟之内，如果是则表示本次遍历满足生成账单条件
		if(minute != null && getHourMinuteOfDay(nowDate) > minute.intValue() && (getHourMinuteOfDay(nowDate) - minute.intValue()) <= 1) {
			return true;
		}
		return false;
    }

	public static boolean checkWeek(Date nowDate, Byte week, Integer minute) {
		if(week == getChineseWeekNum(nowDate) && checkDay(nowDate, minute)) {
			return true;
		}
		return false;
	}

	public static boolean checkMonth(Date nowDate, Byte day, Integer minute) {
		if(day == getDayOfMonth(nowDate) && checkDay(nowDate, minute)) {
			return true;
		}
		return false;
	}


	public static boolean checkQuarter(Date nowDate, Byte monthType, Byte day, Integer minute) {
		if(monthType == 1) {
			if(getMonthOfYear(nowDate) == 1 || getMonthOfYear(nowDate) == 4 || getMonthOfYear(nowDate) == 7 || getMonthOfYear(nowDate) == 10) {
				if(day == getDayOfMonth(nowDate) && checkDay(nowDate, minute)) {
					return true;
				}
			}
		} else if(monthType == 2) {
			if(getMonthOfYear(nowDate) == 3 || getMonthOfYear(nowDate) == 6 || getMonthOfYear(nowDate) == 9 || getMonthOfYear(nowDate) == 12) {
				if(day == getDayOfMonth(nowDate) && checkDay(nowDate, minute)) {
					return true;
				}
			}
		}
		return false;
	}
	
    /**
     * 根据类型获取日期时间
     * @param d
     * @return
     */
    public static Date buildAvailableDate1(Date date, String str, byte type) {
    	if(StringUtils.isNotBlank(str)) {
    		// 1分钟、2小时、3日、4周、5月、6指定
    		if(type == 6) {
    			return parseDate(str, "yyyy-MM-dd HH:mm");
    		} else {
    			int after = Integer.parseInt(str);
    			calendar.setTime(date);
    			switch(type) {
					case 1: // 分钟
						calendar.add(Calendar.MINUTE, after);
						break;
					case 2: // 小时
						calendar.add(Calendar.HOUR_OF_DAY, after);
						break;
					case 3: // 日
						calendar.add(Calendar.DAY_OF_YEAR, after);
						break;
					case 4: // 周
						calendar.add(Calendar.WEEK_OF_YEAR, after);
						break;
					case 5: // 月
						calendar.add(Calendar.MONTH, after);
						break;
					default:
						break;
				}
    			return calendar.getTime();
    		}
    	}
    	return null;
    }
    
    /**
     * 根据类型获取日期时间
     * @param d
     * @return
     */
    public static Date buildAvailableDate2(Date date, String str, byte type) {
    	if(type == 1) {
    		return date;
    	} else if(type == 6) {
			return parseDate(str, "yyyy-MM-dd HH:mm");
		} else {
			if(StringUtils.isNotBlank(str)) {
				int after = Integer.parseInt(str);
				calendar.setTime(date);
				switch(type) {
					case 2: // 小时
						calendar.add(Calendar.HOUR_OF_DAY, after);
						break;
					case 3: // 日
						calendar.add(Calendar.DAY_OF_YEAR, after);
						break;
					case 4: // 周
						calendar.add(Calendar.WEEK_OF_YEAR, after);
						break;
					case 5: // 月
						calendar.add(Calendar.MONTH, after);
						break;
					default:
						break;
				}
				return calendar.getTime();
			}
		}
    	return null;
    }
    
    public static int daysBetween(Date date1, Date date2) {
    	calendar.setTime(date1);
    	int day1 = calendar.get(Calendar.DAY_OF_YEAR);
    	calendar.setTime(date2);
    	int day2 = calendar.get(Calendar.DAY_OF_YEAR);
    	return day2 - day1;
    }
    
    public static Date hourBegin(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date hourEnd(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
    
	public static Date dayBegin(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date dayEnd(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
    
	public static Date weekBegin(Date date) {
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DATE, 2 - week);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date weekEnd(Date date) {
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DATE, 8 - week);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
    
	public static Date monthBegin(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
    }
    
    public static Date monthEnd(Date date) {
    	calendar.setTime(date);
    	int month = calendar.get(Calendar.MONTH);
    	calendar.set(Calendar.MONTH, month + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
    }
    
    public static Date quarterBegin(Date date) {
		calendar.setTime(date);
		int month = getQuarterInMonth(calendar.get(Calendar.MONTH) + 1, true);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date quarterEnd(Date date) {
		calendar.setTime(date);
		int month = getQuarterInMonth(calendar.get(Calendar.MONTH) + 1, false);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	private static int getQuarterInMonth(int month, boolean isQuarterStart) {
		int months[] = { 1, 4, 7, 10 };
		if (!isQuarterStart) {
			months = new int[] { 3, 6, 9, 12 };
		}
		if (month >= 1 && month <= 3) {
			return months[0];
		} else if (month >= 4 && month <= 6) {
			return months[1];
		} else if (month >= 7 && month <= 9) {
			return months[2];
		} else {
			return months[3];
		}
	}
	
	public static Date yearBegin(Date date) {
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
    }
    
    public static Date yearEnd(Date date) {
    	calendar.setTime(date);
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
    }
    
    public static String EnDateFormatWidthDaySuffix(Date date) {
		String pattern;
		Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        switch (day) {
		case 1:
		case 21:
		case 31:
			pattern = "MMM d'st', yyyy";
			break;
		case 2:
		case 22:
			pattern = "MMMM d'nd', yyyy";
			break;
		case 3:
		case 23:
			pattern = "MMM d'rd', yyyy";
			break;

		default:
			pattern = "MMM d'th', yyyy";
			break;
		}
        SimpleDateFormat enSdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        return enSdf.format(date);
	}
    
}
