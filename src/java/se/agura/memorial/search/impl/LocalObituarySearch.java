package se.agura.memorial.search.impl;

import java.util.Collection;

import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.ObituarySearch;

public class LocalObituarySearch implements ObituarySearch {

	public Collection findGraves(String firstName, String lastName,
			String personIdentifier, String dateOfBirthFrom,
			String dateOfBirthTo, String dateOfDeathFrom, String dateOfDeathTo,
			String hometown, String graveyard) 
	{
		// TODO make this work using data.GraveLocallyStored
		return null;
	}

	public Collection getGraveyards() {
		// TODO make this work using GraveCemetery
		return null;
	}

	public Grave findGrave(String graveId) {
		// TODO make this work using data.GraveLocallyStored
		return null;
	}

}
