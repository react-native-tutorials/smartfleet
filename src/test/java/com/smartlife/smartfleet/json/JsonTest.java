/**
 * 
 */
package com.smartlife.smartfleet.json;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

/**
 * @author Marius Iulian Grigoras
 *
 */

public class JsonTest {
	final Log logger = LogFactory.getLog(getClass());

	@Test
	public void parseStringToJson() {
		
		Gson gson = new Gson();
		String objStr = "{\"dato\": \"ubicacion\",\"id_equipo\": 2,\"zona_utm\": \"18L\",\"x\":\"275621\",\"y\":\"8681028\",\"z\":\"37\",\"velocidad\":46}" ;
		String jsonInString = gson.toJson(objStr);
		logger.info(jsonInString);
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject json = (JSONObject) parser.parse(objStr);
			logger.info(json);
			logger.info(json.get("dato"));
			logger.info(json.get("id_equipo"));
			logger.info(json.get("zona_utm"));
			logger.info(json.get("x"));
			logger.info(json.get("y"));
			logger.info(json.get("z"));
			logger.info(json.get("velocidad"));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		Assert.assertTrue(Boolean.TRUE);
	}
}
