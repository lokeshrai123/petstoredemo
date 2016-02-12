package main.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import main.sort.SortById;

/**
 * Main service class using Jersey to expose GET and POST methods for clients
 *
 * @author Lokesh Rai
 *
 */

@Path("/petstore")
public class PetStoreService {
	
	@Path("/addByTags/{tagname}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addPetsByTag(@PathParam("tagname") String tagname) {
		System.out.println("Add Pets by tag from service .... \n");
		String output = "";
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://petstore.swagger.io/v2/pet");

			System.out.println("Output from Service .... \n");

			for (int i = 0; i < 5; i++) {

				String input = "{\"id\":0," + "\"category\":{\"id\":0,\"name\":\"string\"},"
						+ "\"name\":\"doggie\",\"photoUrls\":[\"string\"]," + "\"tags\":[{\"id\":" + i + ",\"name\":\""
						+ tagname + "\"}]," + "\"status\":\"available\"}";

				ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

				if (response.getStatus() == 500) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				}

				output += response.getEntity(String.class) + "\n";

			}

			System.out.println(output);
			
			//Print results into a file
			SimpleDateFormat dateformat3 = new SimpleDateFormat("yyyy-MM-dd - HH-mm-ss");

			String date = dateformat3.format(new Date());

			File file = new File("/Users/lokesh/Documents/workspace/PetStore3/Files/"+"addPetsByTag_"+date+".txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(output);
			bw.close();

			System.out.println("Completed");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}
	
	@Path("/findByStatus/{status}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPetsByStatus(@PathParam("status") String status) {

		System.out.println("\nGet Pets from service by status .... ");
		
		String outputString = "";

		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://petstore.swagger.io/v2/pet/findByStatus?status=" + status);

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... ");
			//System.out.println("output.... " + output);
			
			/*JSONObject entries = new JSONObject(output);
			JSONArray jsonObj2 = entries.getJSONArray("id");
			System.out.println("jsonObj2.... " + jsonObj2.toString());*/

			JSONArray jsonArray = new JSONArray(output);
			
			//System.out.println("jsonObj.... " + jsonArray.toString());
			
			JSONArray sortedJSONArray = getSortedList(jsonArray);
			
			//Change length to jsonObj.length() to pring all records into console/file, this is for testing 100 records only, because of memory issues
			for (int i = 0; i < 100; i++) {

				JSONObject obj = sortedJSONArray.getJSONObject(i);
				
				String id = obj.get("id").toString();
				
				//Exclude pets invalid Ids
				if (!id.startsWith("-")) {
					outputString += obj.toString() + "\n";
				}
			}
			
			System.out.println("Pets sorted by IDs:\n" + outputString);
			
			//Print results into a file
			SimpleDateFormat dateformat3 = new SimpleDateFormat("yyyy-MM-dd - HH-mm-ss");

			String date = dateformat3.format(new Date());

			File file = new File("/Users/lokesh/Documents/workspace/PetStore3/Files/"+"getPetsByStatus_"+date+".txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(outputString);
			bw.close();

			System.out.println("Completed");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return outputString;

	}
	
    /**
    *
    * Sort JSONArray
    *
    * @param JSONArray
    * @return JSONArray
    */
	public static JSONArray getSortedList(JSONArray array) throws JSONException {
		List<JSONObject> list = new ArrayList<JSONObject>();
		for (int i = 0; i < array.length(); i++) {
			//System.out.println("===obj==="+array.getJSONObject(i));
			list.add(array.getJSONObject(i));
		}
		
		/*for (int i = 0; i < list.size(); i++) {
			System.out.println("===i==="+list.get(i));
		}*/
		
		Collections.sort(list, new SortById());

		JSONArray resultArray = new JSONArray(list);

		return resultArray;
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPets(@PathParam("id") long id) {

		String output = "";

		System.out.println("\nGet Pets from service .... ");

		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://petstore.swagger.io/v2/pet/" + id);

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			output = response.getEntity(String.class);

			System.out.println("Output from Server .... ");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return output;

	}





	@Path("/findByTags/{tagname}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPetByTag(@PathParam("tagname") String tagname) {

		System.out.println("\nGet Pets from service by tag .... ");

		String output = "";

		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://petstore.swagger.io/v2/pet/findByTags?tags=" + tagname);

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			output = response.getEntity(String.class);

			System.out.println("Output from Server .... ");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return output;

	}


	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPets(String data) {
		System.out.println("Add Pets from service .... \n");
		String output = "";
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://petstore.swagger.io/v2/pet");

			String input = "{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Service .... \n");
			output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return Response.status(201).entity(output).build();
	}

}