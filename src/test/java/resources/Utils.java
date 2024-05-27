package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static RequestSpecification requestSpecification;
//	protected static ResponseSpecification responseSpecification;

	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	public RequestSpecification requestSpecification() throws IOException {

		if (requestSpecification == null) {
			PrintStream printStream = new PrintStream(new FileOutputStream("logger.txt"));
			requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(printStream))
					.addFilter(ResponseLoggingFilter.logResponseTo(printStream)).setContentType(ContentType.JSON)
					.build();
			return requestSpecification;
		}
		return requestSpecification;
	}

//	public void responseSpecification() {
//		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//	}
	
	public String getJsonPath(Response response, String key) {
		String resp =response.asString();
		JsonPath jsonPath = new JsonPath(resp);
		return jsonPath.get(key).toString();
	}

}
