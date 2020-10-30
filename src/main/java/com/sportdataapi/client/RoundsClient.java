/**
 * 
 */
package com.sportdataapi.client;

import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import com.sportdataapi.data.Round;
import com.sportdataapi.util.AbstractClient;
import com.sportdataapi.util.Response;

/**
 * The rounds client.
 * <p><b>Attention!</b> You shall never create this client directly but use {@link SoccerClient#rounds()} instead.</p>
 * @author ralph
 *
 */
public class RoundsClient extends AbstractClient {

	/**
	 * Constructor.
	 * @param target - the target to request
	 */
	public RoundsClient(WebTarget target) {
		super(target.path("rounds"));
	}

	/**
	 * Request and return the list of teams for a country.
	 * @param seasonId - the ID of the season (of a league) to query
	 * @return list of seasons for the country
	 */
	public List<Round> list(int seasonId) {
		if (seasonId <= 0) throw new RuntimeException("seasonId must be a positive number");
		Response<List<Round>> response = getTarget().queryParam("season_id", ""+seasonId).request().get(new GenericType<Response<List<Round>>>() {});
		return response.getData();
	}
	
	/**
	 * Request and returns a specific round.
	 * @param id - id of round
	 * @return the round requested or {@code null}
	 */
	public Round get(int id) {
		Response<Round> response = getTarget().path(""+id).request().get(new GenericType<Response<Round>>() {});
		return response.getData();
	}

}
