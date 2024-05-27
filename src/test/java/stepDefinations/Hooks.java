package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenarion() throws IOException {
		// Write a code that will give you place id
		// execute this code only when place id is null
		StepDefination stepDefination = new StepDefination();
		if (StepDefination.place_id == null) {
			stepDefination.add_place_payload_with_markettime_hindi_vesu_piplod("Pratham", "Gujarati", "Asia");
			stepDefination.user_call_with_http_request("addPlaceAPI", "POST");
			stepDefination.verify_place_Id_created_maps_to_using("Pratham", "getPlaceAPI");
		}
	}

}
