package com.coruptiaucide.vavedem.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tiberiugolaes on 07/05/2017.
 */
object DateExtensions {

    @JvmStatic
    fun getLongDateTimeFormat(locale: Locale = Locale.getDefault(), timeZone: TimeZone = TimeZone.getDefault()): DateFormat {
        return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, locale).apply { this.timeZone = timeZone }
    }

    @JvmStatic
    fun getShortDateFormat(locale: Locale = Locale.getDefault(), timeZone: TimeZone = TimeZone.getDefault()): DateFormat {
        return DateFormat.getDateInstance(DateFormat.SHORT, locale).apply { this.timeZone = timeZone }
    }

    @JvmStatic
    fun getMediumDateFormat(locale: Locale = Locale.getDefault(), timeZone: TimeZone = TimeZone.getDefault()): DateFormat {
        return DateFormat.getDateInstance(DateFormat.MEDIUM, locale).apply { this.timeZone = timeZone }
    }

    @JvmStatic
    fun Date.toShortDateString(locale: Locale = Locale.getDefault(), timeZone: TimeZone = TimeZone.getDefault()): String {
        return DateExtensions.getShortDateFormat(locale, timeZone).format(this)
    }

    @JvmStatic
    fun Date.toLongDateTimeString(locale: Locale = Locale.getDefault(), timeZone: TimeZone = TimeZone.getDefault()): String {
        return DateExtensions.getLongDateTimeFormat(locale, timeZone).format(this)
    }

    @JvmStatic
    fun Date.toMediumDateString(locale: Locale = Locale.getDefault(), timeZone: TimeZone = TimeZone.getDefault()): String {
        return DateExtensions.getMediumDateFormat(locale, timeZone).format(this)
    }

    @JvmStatic
    fun toMonthYear(month: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.set(Calendar.MONTH, month - 1) //inconsistency between Card.IO and Java standard
        calendar.set(Calendar.YEAR, year)
        val formatter = SimpleDateFormat("MM/yy")
        return formatter.format(calendar.time)
    }

    @JvmStatic
    fun getDayOfWeek(): Int{
        val calendar = Calendar.getInstance(Locale.UK)
        calendar.time = Date()
        return if ((calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0) 7 else (calendar.get(Calendar.DAY_OF_WEEK) - 1)
    }
}
