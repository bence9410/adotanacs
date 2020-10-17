package hu.beni.adotanacsadas.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AdoFilter implements Filter {

	static {
		String html = "";

		try {
			html = new String(new ClassPathResource("public/index.html").getInputStream().readAllBytes(), "ISO-8859-1");
		} catch (IOException e) {
			log.error("Error:", e);
		}
		INDEX = html;

	}

	private static final String INDEX;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = HttpServletRequest.class.cast(request);
		String url = req.getRequestURL().toString();

		if (url.contains("/api/") || url.contains("/img/") || url.contains("/js/") || url.contains("/css/")
				|| url.contains("/fonts/") || url.contains("favicon")) {
			chain.doFilter(request, response);
		} else {
			response.getWriter().append(INDEX).close();

		}

	}

}
