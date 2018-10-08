/**
 * 
 */
package com.smartlife.smartfleet.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author Marius Iulian Grigoras
 *
 * @date 2 ago. 2018
 */
public class Utils {
	public static boolean isNullOrEmpty( final Collection< ? > c ) {
	    return c == null || c.isEmpty();
	}

	public static boolean isNullOrEmpty( final Map< ?, ? > m ) {
	    return m == null || m.isEmpty();
	}
}
