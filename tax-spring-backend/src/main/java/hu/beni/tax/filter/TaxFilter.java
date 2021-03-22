package hu.beni.tax.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.util.ContentCachingRequestWrapper;

import hu.beni.tax.entity.Request;
import hu.beni.tax.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	private final RequestRepository traficRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ContentCachingRequestWrapper httpRequest = new ContentCachingRequestWrapper(
				HttpServletRequest.class.cast(request));

		String ip = null;
		String url = null;
		String methodType = null;
		String requestHeaders = null;
		String requestBody = null;

		try {

			ip = request.getRemoteAddr();
			url = httpRequest.getRequestURL().toString();
			methodType = httpRequest.getMethod();
			requestHeaders = toStream(httpRequest.getHeaderNames()).collect(Collectors.toMap(headerName -> headerName,
					headerName -> toStream(httpRequest.getHeaders(headerName)).collect(Collectors.joining(","))))
					.toString();
			requestBody = new String(httpRequest.getContentAsByteArray());

			traficRepository.save(Request.builder().ip(ip).url(url).methodType(methodType).headers(requestHeaders)
					.body(requestBody).build());

		} catch (Throwable t) {
			log.error("Error:", t);
		} finally {
			if (Objects.isNull(requestBody)) {
				try {
					requestBody = new String(httpRequest.getContentAsByteArray());
				} catch (Throwable t) {
					log.error("Error:", t);
				}
			}
			log.info("ip: {}, url: {}, methodType: {}, requestHeader: {}, requestBody: {}", ip, url, methodType,
					requestHeaders, requestBody);
		}

		if (Stream.of(URL_WHITELIST).anyMatch(url::contains)) {
			chain.doFilter(httpRequest, response);
		} else {
			response.getWriter().append(INDEX).close();
		}

	}

	private <T> Stream<T> toStream(Enumeration<T> enumeration) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(enumeration.asIterator(), Spliterator.ORDERED),
				false);
	}

}
