package com.bitofcode.rockpapersscissors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bitofcode.rockpapersscissors.game.ShapeType;
import com.bitofcode.rockpapersscissors.web.GameResult;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RockPaperScissorsIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private HttpHeaders createJsonHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	@Test
	public void playAgainstTheComputer_should_return_game() {
		HttpHeaders headers = createJsonHeader();

		HttpEntity<ShapeType> entity = new HttpEntity<ShapeType>(ShapeType.ROCK, headers);

		GameResult didIWin = restTemplate.postForEntity("/api/rock-paper-scissors/play", entity, GameResult.class)
				.getBody();

		assertNotNull(didIWin.getResult());
	}

	@Test
	public void playAgainstTheComputer_with_invalidInput_shoudl_fails() throws Exception {
		HttpHeaders headers = createJsonHeader();
		HttpEntity<String> entity = new HttpEntity<String>("RO12345CK", headers);

		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> response = restTemplate.postForEntity("/api/rock-paper-scissors/play", entity, Map.class);

		assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));

	}

}
