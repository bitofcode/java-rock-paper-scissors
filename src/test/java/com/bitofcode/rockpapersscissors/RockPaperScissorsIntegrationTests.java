package com.bitofcode.rockpapersscissors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import com.bitofcode.rockpapersscissors.web.GameInput;
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
		HttpEntity<GameInput> entity = new HttpEntity<GameInput>(new GameInput("Rock"), headers);
		ResponseEntity<GameResult> result = restTemplate.postForEntity("/api/rock-paper-scissors/play", entity,
				GameResult.class);

		assertThat(result.getStatusCode(), is(HttpStatus.OK));
		assertThat(result.getBody().getClass(), is(equalTo(GameResult.class)));
	}

	@Test
	public void playAgainstTheComputer_with_invalidInput_shoudl_fails() throws Exception {
		HttpHeaders headers = createJsonHeader();
		HttpEntity<String> entity = new HttpEntity<String>("{\"guess\":\"RO12345CK\"}", headers);
		ResponseEntity<String> response = restTemplate.postForEntity("/api/rock-paper-scissors/play", entity,
				String.class);

		assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
		assertThat(response.getBody().contains("RO12345CK"), is(true));
		assertThat(response.getBody().contains("invalid"), is(true));
	}

}
