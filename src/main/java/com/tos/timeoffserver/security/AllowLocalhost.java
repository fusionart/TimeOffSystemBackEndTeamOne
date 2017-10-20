package com.tos.timeoffserver.security;

 

	import java.io.IOException;
	import javax.servlet.Filter;
	import javax.servlet.FilterChain;
	import javax.servlet.FilterConfig;
	import javax.servlet.ServletException;
	import javax.servlet.ServletRequest;
	import javax.servlet.ServletResponse;
	import javax.servlet.http.HttpServletResponse;
	import org.springframework.stereotype.Component;
	
	@Component
	public class AllowLocalhost implements Filter {

	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	        HttpServletResponse response = (HttpServletResponse) res;
	        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, UPDATE");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "Origin, Authorization, isadmin, X-Requested-With, Content-Type, Accept");
	        chain.doFilter(req, res);
	    }

	    public void init(FilterConfig filterConfig) {}

	    public void destroy() {}
	}