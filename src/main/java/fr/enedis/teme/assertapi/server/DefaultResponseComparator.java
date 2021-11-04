package fr.enedis.teme.assertapi.server;

import java.util.Arrays;
import java.util.Objects;

import org.skyscreamer.jsonassert.JSONCompareResult;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;

import fr.enedis.teme.assertapi.core.ResponseComparator;

public final class DefaultResponseComparator implements ResponseComparator {

	@Override
	public void assumeEnabled(boolean enable) {
		if(!enable) {
			throw new TestAbortException();
		}
	}

	@Override
	public ResponseEntity<byte[]> assertNotResponseException(SafeSupplier<ResponseEntity<byte[]>> supp) {
		try {
			return supp.get();
		} catch(RestClientResponseException e) {
			throw new RuntimeException("Actual response exception", e);
		} catch (Exception e) {
			throw new RuntimeException("Uncatched exception", e);
		}
	}

	@Override
	public RestClientResponseException assertResponseException(SafeSupplier<?> supp) {
		try {
			supp.get();
		} catch(RestClientResponseException e) {
			return e;
		} catch (Exception e) {
			throw new RuntimeException("Uncatched exception", e);
		}
		throw new RuntimeException("Expected response exception");
	}

	@Override
	public void assertStatusCode(int expectedStatusCode, int actualStatusCode) {
		if(expectedStatusCode != actualStatusCode) {
			throw new RuntimeException("Status code");
		}
	}

	@Override
	public void assertContentType(MediaType expectedContentType, MediaType actualContentType) {
		if(!Objects.equals(expectedContentType, actualContentType)) {
			throw new RuntimeException("Content Type");
		}
	}

	@Override
	public void assertByteContent(byte[] expectedContent, byte[] actualContent) {
		if(!Arrays.equals(expectedContent, actualContent)) {
			throw new RuntimeException("Response content");
		}
	}

	@Override
	public void assertTextContent(String expectedContent, String actualContent) {
		if(!Objects.equals(expectedContent, actualContent)) {
			throw new RuntimeException("Response content");
		}
	}

	@Override
	public void assertJsonCompareResut(JSONCompareResult res) {
		if(res.failed()) {
			throw new RuntimeException("not equal " + res.getMessage());
		}
	}
	
}
