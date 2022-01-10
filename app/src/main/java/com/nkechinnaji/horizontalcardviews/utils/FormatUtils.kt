package com.nkechinnaji.horizontalcardviews.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Date formatting object
 */
object FormatUtils {
    /**
     *formats the date String to 8:26 PM
     */
    @JvmStatic
    fun formatDateWithDateOnly(dateAsString: String?): String {
        val formatDateWithTimeAndTimeZoneInfo = formatDateWithTimeAndTimeZoneInfo(dateAsString)
        val simpleDateFormat = selectedDatePattern("h:mm a")
        return simpleDateFormat.format(formatDateWithTimeAndTimeZoneInfo)
    }

    /**
     *formats the date String to Mon, Jan 10 '22
     */
    @JvmStatic
    fun formatDateWithDayOnly(dateAsString: String?): String {
        val formatDateWithTimeAndTimeZoneInfo = formatDateWithTimeAndTimeZoneInfo(dateAsString)
        val simpleDateFormat = selectedDatePattern("EEE, MMM d, ''yy")
        return simpleDateFormat.format(formatDateWithTimeAndTimeZoneInfo)
    }

    /**
     * Used for adding pattern selected
     */

    private fun selectedDatePattern(patternString: String?): SimpleDateFormat {
        return SimpleDateFormat(patternString, Locale.getDefault())
    }

    /**
     *formats the date String to Mon Jan 10 15:57:13 CDT 2022
     */
    @JvmStatic
    fun formatDateWithTimeAndTimeZoneInfo(dateAsString: String?, pattern: String = "yyyy-MM-dd'T'HH:mm:ss"): Date {
        dateAsString ?: return Date()
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
        return try {
            return simpleDateFormat.parse(dateAsString) ?: Date()
        } catch (e: ParseException) {
            e.printStackTrace()
            Date()
        }
    }
}