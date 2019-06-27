
package com.educacionit.delivery.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class AuthenticationFilter implements Filter {


	private ServletContext context;

	private ArrayList<String> urlList;


	private static final Logger logger = Logger.getLogger (AuthenticationFilter.class);


	public AuthenticationFilter () {

		super ();
	}


	public void init (FilterConfig config) {

		String urls = config.getInitParameter ("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");

		urlList = new ArrayList<> ();

		while (token.hasMoreTokens ()) {

			urlList.add(token.nextToken ());
		}
	}
	
	public void doFilter (ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {


		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getServletPath ();
		boolean allowedRequest = false;

		logger.debug ("**************** " + url + " ****************");

		if (urlList.contains (url)) {
			allowedRequest = true;
		}

		if (!allowedRequest) {

			HttpSession session = request.getSession(false);

			if (null == session) {

				response.sendRedirect("index.jsp");
				return;
			}
		}

		chain.doFilter (request, response);
	}

	public void destroy () {}
}