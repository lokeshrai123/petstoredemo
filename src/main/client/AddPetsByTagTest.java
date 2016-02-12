package main.client;
 
import org.junit.Test;

/**
 * Test class to test adding pets by tag name
 *
 * @author Lokesh Rai
 *
 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
 
public class AddPetsByTagTest {
	
	public static void main(String[] args) {
		try {
			
			AddPetsByTagTest addPetsByTagTest = new AddPetsByTagTest();
			addPetsByTagTest.addPetsByTag();
			//petStoreClient.getPets();
			//petStoreClient.getPetsByTag(); 			
			//petStoreClient.addPets();			

		  } catch (Exception e) {

			e.printStackTrace();

		  }

		}
	
    /**
    *
    * Add pets by tag name
    *
    * @param
    * @return void
    */
	@Test
	public void addPetsByTag()
	{
		try {

			System.out.println("Test::addPetsByTag::Adding pets to Petstore:: ");

	        Client client = Client.create();

	        WebResource webResource = client.resource("http://localhost:8080/PetStore/petstore/addByTags/tag1");

	       String input = "";
	        ClientResponse response = webResource.type("application/json")
	           .post(ClientResponse.class, input);

	        if (response.getStatus() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                 + response.getStatus());
	        }

	        System.out.println("Test::addPetsByTag::Pets added to Petstore:: \n");
	        String output = response.getEntity(String.class);
	        System.out.println(output);
	        
	        System.out.println("Test::addPetsByTag::Adding pets to Petstore::Success!! ");

	      } catch (Exception e) {

	        e.printStackTrace();

	      }
	}
	
    /**
    *
    * Get pets by tag name
    *
    * @param
    * @return void
    */
	public void getPets() {
		
		System.out.println("\ngetPets from client .... ");
		
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:8080/PetStore/petstore/1");

			ClientResponse response = webResource.type("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Service .... \n");
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }
	}
	
    /**
    *
    * Find pets by tag name
    *
    * @param
    * @return void
    */
	public void getPetsByTag() {
		
		System.out.println("\ngetPets from client by tags.... ");
		
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:8080/PetStore/petstore/findByTags");

			ClientResponse response = webResource.type("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Service .... \n");
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }
	}
	
    /**
    *
    * Add pets with Json
    *
    * @param
    * @return void
    */
	public void addPets () {
		try {

			System.out.println("addPets from client .... \n");

	        Client client = Client.create();

	        WebResource webResource = client.resource("http://localhost:8080/PetStore/petstore/add");

	        String input = "{\"id\":0,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";

	         String in = "";
	        
	        ClientResponse response = webResource.type("application/json")
	           .post(ClientResponse.class, input);

	        if (response.getStatus() != 201) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                 + response.getStatus());
	        }

	        System.out.println("Output from Service .... \n");
	        String output = response.getEntity(String.class);
	        System.out.println(output);

	      } catch (Exception e) {

	        e.printStackTrace();

	      }
	}
}