package hu.beni.adotanacsadas.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;

import hu.beni.adotanacsadas.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
public class ArticleFilter implements Filter {

        private final PageService pageService;

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                        throws IOException, ServletException {

                // chain.doFilter(request, response);
                response.getWriter().append(pageService.article()).close();
        }

}
