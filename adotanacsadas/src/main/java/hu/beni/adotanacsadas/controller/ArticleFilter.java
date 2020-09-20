package hu.beni.adotanacsadas.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import hu.beni.adotanacsadas.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ArticleFilter implements Filter {

        private final PageService pageService;

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                        throws IOException, ServletException {
                HttpServletRequest req = HttpServletRequest.class.cast(request);
                String url = req.getRequestURL().toString();
                String ipAddress = req.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                }
                log.info(ipAddress);
                if (url.contains("cikkek")) {
                        response.getWriter().append(pageService.article()).close();

                } else if (url.contains("idopontfoglalas")) {
                        response.getWriter().append(pageService.booking()).close();
                } else if (url.endsWith(".com/") || url.endsWith("5000/")) {
                        response.getWriter().append(pageService.main()).close();
                } else {
                        chain.doFilter(request, response);
                }

        }

}
