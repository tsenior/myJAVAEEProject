package ocm.tinyiko.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*", asyncSupported = true)
public class ParameterLoggingFilter implements Filter {

    public ParameterLoggingFilter() {
    }


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("A logging interceptor created with a filter::::");
		request.getParameterMap().entrySet().stream().forEach(entry ->{
			System.out.println(String.format("%s:%s", entry.getKey(), entry.getValue()[0]));
		});
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
