package main.client;

import org.junit.Test;

/**
 * Test class to test getting pets (if available) and sorting them by id
 *
 * @author Lokesh Rai
 *
 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class ListByStatusTest {

	public static void main(String[] args) {
		try {

			ListByStatusTest listByStatusTest = new ListByStatusTest();

			listByStatusTest.getPetsByStatus();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
    /**
    *
    * Get pets (if available) and sorting them by id
    *
    * @param
    * @return void
    */
	@Test
	public void getPetsByStatus() {
		try {

			System.out.println("Test::getPetsByStatus::Getting Pets By status (sorted by id) :: \n");

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8080/PetStore/petstore/findByStatus/available");

			ClientResponse response = webResource.type("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Test::getPetsByStatus::Got Pets By status (sorted by id) :: \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
			System.out.println("\nTest::getPetsByStatus::Got Pets By status (sorted by id) ::Success!! \n");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}