package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name, String language, String address) throws IOException {

//		DataProviders data = new DataProviders();
//		List<String> nam = data.getData("name");
//		List<String> lan = data.getData("language");
//		List<String> add = data.getData("address");

		AddPlace addPlace = new AddPlace();
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addPlace.setLocation(location);
		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setName(name);
		addPlace.setLanguage(language);
		/*
		 * addPlace.setAddress(add.get(1)); addPlace.setName(nam.get(1));
		 * addPlace.setLanguage(lan.get(1));
		 */		addPlace.setPhone_number("+91525226565");
		List<String> MyType = new ArrayList<String>();
		MyType.add("Shoe Park");
		MyType.add("Park");
		MyType.add("River");
		addPlace.setTypes(MyType);
		addPlace.setWebsite("https://www.google.com");
		return addPlace;
	}

	public DeletePlace deletePlacePayLoad(String place_id) {
		DeletePlace deletePlace = new DeletePlace();
		deletePlace.setPlace_id(place_id);
		return deletePlace;
	}
}
