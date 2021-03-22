package hu.beni.tax.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import hu.beni.tax.entity.Trafic;
import hu.beni.tax.repository.TraficRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaxFilter implements Filter {

	static {
		String html = null;
		try (InputStream is = new ClassPathResource("public/index.html").getInputStream()) {
			html = new String(is.readAllBytes(), StandardCharsets.ISO_8859_1);
		} catch (IOException e) {
			// dev run
		}
		INDEX = html;
	}

	private static final String INDEX;

	private static final String[] URL_WHITELIST = { "/api/", "/img/", "/js/", "/css/", "/fonts/", "favicon" };

	private final TraficRepository traficRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		ContentCachingRequestWrapper httpRequest = new ContentCachingRequestWrapper(
				HttpServletRequest.class.cast(request));
		ContentCachingResponseWrapper httpResponse = new ContentCachingResponseWrapper(
				HttpServletResponse.class.cast(response));

		String requestId = UUID.randomUUID().toString();
		String ip = request.getRemoteAddr();
		String url = httpRequest.getRequestURL().toString();
		String methodType = httpRequest.getMethod();
		String requestHeaders = toStream(httpRequest.getHeaderNames())
				.collect(Collectors.toMap(headerName -> headerName,
						headerName -> toStream(httpRequest.getHeaders(headerName)).collect(Collectors.joining(","))))
				.toString();
		String requestBody = new String(httpRequest.getContentAsByteArray());

		traficRepository.save(Trafic.builder().requestId(requestId).ip(ip).url(url).methodType(methodType)
				.headers(requestHeaders).body(requestBody).request(true).build());

		if (Stream.of(URL_WHITELIST).anyMatch(url::contains)) {
			chain.doFilter(httpRequest, httpResponse);
		} else {
			httpResponse.getWriter().append(INDEX).close();
		}

		String responseHeaders = httpResponse.getHeaderNames().stream()
				.collect(Collectors.toMap(headerName -> headerName,
						headerName -> httpResponse.getHeaders(headerName).stream().collect(Collectors.joining(","))))
				.toString();
		String responseBody = new String(httpResponse.getContentAsByteArray());

		traficRepository.save(
				Trafic.builder().requestId(requestId).ip(ip).url(url).methodType(methodType).headers(responseHeaders)
						.body(responseBody).request(false).responseStatus(httpResponse.getStatus()).build());

	}

	private <T> Stream<T> toStream(Enumeration<T> enumeration) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(enumeration.asIterator(), Spliterator.ORDERED),
				false);
	}

}
