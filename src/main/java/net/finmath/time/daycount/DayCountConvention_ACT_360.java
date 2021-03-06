/*
 * (c) Copyright Christian P. Fries, Germany. All rights reserved. Contact: email@christianfries.com.
 *
 * Created on 07.09.2013
 */

package net.finmath.time.daycount;

import java.util.Calendar;

/**
 * Implementation of ACT/360.
 * 
 * Calculates the day count by calculating the actual number of days between startDate and endDate.
 * 
 * The method is only exact, if the two calendar dates are (approximately) on the same time. A fractional day is
 * rounded to the approximately nearest day (since daylight saving is not considered, the notion of nearest may be off by one hour).
 * 
 * The day count fraction is calculated using ACT/360 convention, that is, the
 * day count divided by 360.
 * 
 * This day count convention is sometime called <i>Money Market basis</i>.
 * 
 * <ul>
 * 	<li>
 * 		The method {@link #getDaycountFraction(Calendar, Calendar) getDaycountFraction} corresponds to the implementation of the "ACT/360 method" of Excel function YEARFRAC, i.e., YEARFRAC(startDate,endDate,2).
 * 	</li>
 * </ul>
 * 
 * @author Christian Fries
 */
public class DayCountConvention_ACT_360 extends DayCountConvention_ACT implements DayCountConventionInterface {

	/**
	 * Create an ACT/360 day count convention.
	 */
	public DayCountConvention_ACT_360() {
	}
	

	/* (non-Javadoc)
	 * @see net.finmath.time.daycount.DayCountConventionInterface#getDaycountFraction(java.util.GregorianCalendar, java.util.GregorianCalendar)
	 */
	@Override
	public double getDaycountFraction(Calendar startDate, Calendar endDate) {
		if(startDate.after(endDate)) return -getDaycountFraction(endDate,startDate);

		double daycountFraction = getDaycount(startDate, endDate) / 360.0;

		return daycountFraction;
	}
}
