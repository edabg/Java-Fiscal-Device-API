package com.taliter.fiscal.device;

import java.io.*;
import java.math.*;
import java.util.*;

/** A request to or response from a fiscal device.
A packet is a vector of byte arrays called fields. Fields cannot be null.
Fields are accessed using a zero-based index up to getSize() - 1.
All operations that set values of fields may grow the size of the vector as needed,
adding empty fields if necessary. */
public interface FiscalPacket extends Serializable
{
	/** Normal text style. */
	public int STYLE_NORMAL = 0;
	/** Emphasized text style. */
	public int STYLE_EMPHASIZED = 1;
	/** Double height text style. */
	public int STYLE_DOUBLE_HEIGHT = 2;
	/** Double width text style. */
	public int STYLE_DOUBLE_WIDTH = 4;
	/** Underlined text style. */
	public int STYLE_UNDERLINED = 8;

	/** 
         * Compares this and the specified packet by size and the corresponding fields.
         * @param o A specified object for compare.
         * @return Returns true if the both object are equals by size and corresponding fields.
        */
	public boolean equals(Object o);
        
	/** 
         * Clones this object.
         * @return A deep copy of this packet. Fields are themselves copied. 
         */
	public Object clone();
        
	/** 
         * Convert the packet to string.
         * @return A string representation of the packet contents. 
         */
	public String toString();
        
	/** 
         * Convert the packet to hexadecimal string.
         * @return A string representation of the packet contents in hexadecimal notation. 
         */
	public String toHexString();
        
	/** 
         * Convert the packet to vector of escaped ASCII strings.
         * @return A string representation of the packet contents as a vector of escaped ASCII strings. 
         */
	public String toASCIIString();

	// Size

	/** Remove all fields. */
	public void clear();

	/** 
         * Set the number of fields. Add empty fields to the end or remove fields from the end of the packet as needed. 
         * @param size The number of fields.
         */
	public void setSize(int size);
        
	/** 
         * Get the number of fields. 
         * @return The number of fields.
         */
	public int getSize();

	// Fields

	/**
         * Replace field with empty byte array. 
         * @param field The specified field.
         */
	public void clear(int field);

	/** 
         * Set the value of a field. A reference to the passed value is kept (value is not copied). 
         * @param field The field index
         * @param value The new value at the specified field
         */
	public void set(int field, byte[] value);
        
	/** Get the value of a field. A reference to the value is returned (the value is not copied). 
         * @param field The field index.
         * @return The value at specified field as a byte array.
         */
	public byte[] get(int field);

	/** 
         * Get the length of the specified byte array. 
         * @param field The field index.
         * @return The length of the specified byte array.
         */
	public int getLength(int field);

	// Field Copies

	/** 
         * Set the value of a field to a copy of the passed value. 
         * @param field The field index.
         * @param value The value at the specified field.
         */
	public void setCopy(int field, byte[] value);
        
	/** 
         * Set the value of a field to a copy of the passed value. 
         * @param field The field index.
         * @param value The value at the specified field.
         * @param offset The start index of value.
         * @param length The number of bytes to be copied.
         */
	public void setCopy(int field, byte[] value, int offset, int length);

        /** 
         * Get a copy of the value of a field. 
         * @param field The field index.
         * @return A copy of the value at the specified field.
         */
	public byte[] getCopy(int field);
        
	/** 
         * Copy at most <code>value.length</code> bytes of the value of a field to the provided array.
         * @param field The field index.
         * @param value The provided array.
         * @return the real length of the field which may be more than what was actually copied. 
        */
	public int getCopy(int field, byte[] value);
        
	/**
         * Copy at most <code>length</code> bytes of the value of a field to the provided array.
         * @param field The field index.
         * @param value The provided array.
         * @param offset The start index to copy from.
         * @param length the number of bytes to be copied.
         * @return the real length of the field which may be more than what was actually copied. 
        */
	public int getCopy(int field, byte[] value, int offset, int length);

	// String Fields

	/** 
         * Set a string field. The style used is STYLE_NORMAL. 
         * @param field The field index.
         * @param value The new value as a string.
         */
	public void setString(int field, String value);
        
	/** 
         * Set a string field in the specified style. 
         * @param field The field index.
         * @param value The new value as a string.
         * @param style The string style.
         */
	public void setString(int field, String value, int style);
        
	/** 
         * Get a string field. 
         * @param field The field index.
         * @return The string from the specified field.
         */
	public String getString(int field);
        
	/**
         * Get the style of a string field. 
         * @param field The field index.
         * @return The string style of the value at the specified field.
         */
	public int getStringStyle(int field);

	// Special Numeric Fields

	/** 
         * Set a field of 1 byte. 
         * @param field The field index.
         * @param value The new value at the specified field.
         */
	public void setByte(int field, int value);
        
	/** 
         * Get a field of 1 byte. 
         * @param field The field index.
         * @return An one byte value at the specified field.
         */
	public int getByte(int field);

	/** 
         * Set a field of 4 bytes representing the ASCII codes of the passed value in upper-case hexadecimal notation,
	 * most significant nibble first. The value is truncated to 16 bits. 
         * @param field The field index.
         * @param value The new value of the field.
        */
	public void setHex16(int field, int value);
        
	/** 
         * Get a 16-bit value from a field of 4 bytes representing the ASCII codes of the value in case insensitive hexadecimal notation,
	 * most significant nibble first. 
         * @param field The field index.
         * @return A 16-bite value of the specified value.
        */
	public int getHex16(int field);

	// Numeric Fields

	/** 
         * Set a numeric field from an integer. 
         * @param field The field index.
         * @param value The new value of the specified index.
         */
	public void setInt(int field, int value);

        /** 
         * Get a numeric field as an integer. 
         * @param field The field index.
         * @return The value of the specified field as a 32-bit number.
        */
	public int getInt(int field);

	/** 
         * Set a numeric field from a long. 
         * @param field The field index.
         * @param value The new value of the specified index.
        */
	public void setLong(int field, long value);
        
	/** 
         * Get a numeric field as a long. 
         * @param field The field index.
         * @return The value of the specified field as a 64-bit number.
         */
	public long getLong(int field);

	/** 
         * Set a numeric field from a float. 
         * @param field The field index.
         * @param value The new value of the specified index.
         */
	public void setFloat(int field, float value);
        
	/** 
         * Get a numeric field as a float. 
         * @param field The field index.
         * @return The value of the specified field as a floating-point number.
         */
	public float getFloat(int field);

	/** 
         * Set a numeric field from a double. 
         * @param field The field index.
         * @param value The new value of the specified field.
         */
	public void setDouble(int field, double value);
        
	/** 
         * Get a numeric field as a double. 
         * @param field The field index.
         * @return The value of the specified field as a floating-point number with double precision.
         */
	public double getDouble(int field);

	/** 
         * Set a numeric field from a BigInteger. 
         * @param field The field index.
         * @param value The new value of the specified field.
         */
	public void setBigInteger(int field, BigInteger value);
        
	/** 
         * Get a numeric field as a BigInteger. 
         * @param field The field index.
         * @return The value of the specified field as a BigInteger object.
         */
	public BigInteger getBigInteger(int field);

	/** 
         * Set a numeric field from a BigDecimal. 
         * @param field The field index.
         * @param value The new value of the specified field.
         */
	public void setBigDecimal(int field, BigDecimal value);
        
	/** 
         * Get a numeric field as a BigDecimal. 
         * @param field The field index.
         * @return The value of the specified field as a BigDecimal object.
        */
	public BigDecimal getBigDecimal(int field);

	// Date And Time Fields

	/** 
         * Set a date field by specifying 
         * @param  field The field index.
         * @param year full 4 digit number 
         * @param month the month of year (1 to 12)
         * @param day the day of month (1 to 31). 
         */
	public void setDate(int field, int year, int month, int day);
        
	/** 
         * Get the year part of a date field (full 4 digit value). 
         * @param field The field index.
         * @return The year part of the date.
        */
	public int getDateYear(int field);
        
	/** 
         * Get the month of year part of a date field (1 to 12). 
         * @param field The field index.
         * @return The month part of the date.
         */
	public int getDateMonth(int field);
        
	/** 
         * Get the day of month part of a date field (1 to 31). 
         * @param field The field index.
         * @return The day part of the date.
         */
	public int getDateDay(int field);

	/** 
         * Set a time field by specifying hour (0 to 23), minute (0 to 59) and second (0 to 59). 
         * @param field The field index.
         * @param hour The hour part of the time.
         * @param minute The minute part of the time.
         * @param second The second part of the time.
         */
	public void setTime(int field, int hour, int minute, int second);
        
	/** 
         * Get the hour part of a time field (0 to 23). 
         * @param field The field index.
         * @return The hour part of the time.
         */
	public int getTimeHour(int field);
        
	/** 
         * Get the minute part of a time field (0 to 59). 
         * @param field The field index.
         * @return The minute part of the time.
         */
	public int getTimeMinute(int field);
        
	/** 
         * Get the second part of a time field (0 to 59). 
         * @param field The field index.
         * @return The second part of the time.
         */
	public int getTimeSecond(int field);

	/** 
         * Set a date field and a time field
         * @param dateField The date field index.
         * @param timeField The time field index.
         * @param year full 4 digit value 
         * @param month the month of year (1 to 12)
         * @param day the day of month (1 to 31),
	 * @param hour the hour part of the time (0 to 23)
         * @param minute the minute part of  the time (0 to 59)
         * @param second the second part of the time (0 to 59). 
         */
	public void setDateAndTime(int dateField, int timeField, int year, int month, int day, int hour, int minute, int second);

	/** 
         * Set a date field from a Date object. Uses default calendar and time-zone. 
         * @param field The field index.
         * @param date The new date value.
         */
	public void setDate(int field, Date date);
        
	/** 
         * Get a date field as a Date object. Uses default calendar and time-zone. 
         * @param field The field index.
         * @return The date as a Date object.
         */
	public Date getDate(int field);

	/** 
         * Set a date field and a time field from a Date object. Uses default calendar and time-zone. 
         * @param dateField The date field index.
         * @param timeField The time field index.
         * @param date The new date value.
         */
	public void setDateAndTime(int dateField, int timeField, Date date);
        
	/** 
         * Get a date field and a time field as a Date object. Uses default calendar and time-zone. 
         * @param dateField The date field index.
         * @param timeField The time field index.
         * @return The date as a Date object.
         */
	public Date getDateAndTime(int dateField, int timeField);

	/** 
         * Set a date field from a Calendar object. 
         * @param field The field index.
         * @param calendar The source of the date.
         */
	public void setDate(int field, Calendar calendar);
        
	/**
         * Get the contents of a date field into the passed Calendar object
         * @param field The field index.
         * @param calendar The source of date.
         * @return the passed calendar. 
         */
	public Calendar getDate(int field, Calendar calendar);

	/** 
         * Set a date field and a time field from a Calendar object. 
         * @param dateField The date field index.
         * @param timeField The time field index.
         * @param calendar The source of the date.
         */
	public void setDateAndTime(int dateField, int timeField, Calendar calendar);
        
	/** 
         * Get the contents of a date field and a time field into the passed Calendar object.
         * @param dateField The date field index.
         * @param timeField The time field index.
         * @param calendar The source of the date.
         * @return the passed calendar. 
        */
	public Calendar getDateAndTime(int dateField, int timeField, Calendar calendar);

	// Special Fields

	/** 
         * Set the command code of this packet. 
         * @param value The command code.
         */
	public void setCommandCode(int value);
        
	/**
         * Get the command code of this packet. 
         * @return The command code of the packet.
         */
	public int getCommandCode();

	/** 
         * Set the printer status of this response packet. 
         * @param value The status of the printer.
         */
	public void setPrinterStatus(int value);
        
	/** 
         * Get the printer status of this response packet. 
         * @return The printer status.
         */
	public int getPrinterStatus();

	/** 
         * Set the fiscal status of this response packet. 
         * @param value The fiscal status.
         */
	public void setFiscalStatus(int value);
        
	/** 
         * Get the fiscal status of this response packet. 
         * @return The fiscal status.
         */
	public int getFiscalStatus();
}
