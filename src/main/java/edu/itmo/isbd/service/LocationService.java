package edu.itmo.isbd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.itmo.isbd.model.Location;
import edu.itmo.isbd.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationService {
	@Value("${app.map-service.api-key}")
	private String apiKey;
	private static final String baseURL = "https://api.tomtom.com/search/2/search/";
	private final LocationRepository locationRepository;

	@Transactional
	public Location save(Location location) {
		return locationRepository.save(location);
	}

	public Location saveIfNotExist(Location location) {
		if (!locationRepository.existsByAddress(location.getAddress()))
			return save(location);
		return get(location.getAddress());
	}

	public Location get(Integer id) {
		return locationRepository.getById(id);
	}

	public Location get(String address) {
		return locationRepository.getByAddress(address);
	}


	public Location newLocation(String address) {
		Location location = new Location();
		String url = buildUrl(address, Collections.singletonMap("limit", "1"));
		Map<String, Object> response = makeRequest(url);
		log.info("response from map service: {}", response);
		List responseResult = (List) response.get("results");
		if (responseResult.size() == 0)
			throw new IllegalArgumentException("Invalid location address was given. It can't be formatted: " + address);
		Map<String, Object> firstElm = (Map<String, Object>) responseResult.get(0);
		Map<String, Object> addressEntity = (Map<String, Object>) firstElm.get("address");
		location.setAddress((String) addressEntity.get("freeformAddress"));
		location.setCountry((String) addressEntity.get("country"));
		Map<String, Object> pos = (Map<String, Object>) firstElm.get("position");
		location.setCoordinates((Double) pos.get("lat"), (Double) pos.get("lon"));
		return location;
	}

	private Map<String, Object> makeRequest(String url) {
		try {
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpRequest httpRequest = HttpRequest.newBuilder()
					.uri(new URI(url))
					.header("Accept", "application/json")
					.GET()
					.build();
			log.info("request to map service: {}", httpRequest);
			HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			return new ObjectMapper().readValue(response.body(), HashMap.class);
		} catch (URISyntaxException e) {
			log.error("url isn't valid " + url, e);
		} catch (JsonProcessingException e) {
			log.error("parsing json response failed", e);
		} catch (InterruptedException e) {
			log.error("waiting response from " + url + " was interrupted", e);
		} catch (IOException e) {
			log.error("can't send request to " + url, e);
		}
		return new HashMap<>();
	}

	private String buildUrl(String address, Map<String, String> params) {
		return baseURL + URLEncoder.encode(address, StandardCharsets.UTF_8)
				+ ".json?key=" + apiKey + "&"
				+ params.keySet().stream().map(k -> k + "=" + params.get(k)).collect(Collectors.joining("&"));
	}

	private String buildUrl(String address) {
		return baseURL + URLEncoder.encode(address, StandardCharsets.UTF_8) + ".json?key=" + apiKey;
	}
}
