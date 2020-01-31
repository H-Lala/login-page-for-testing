package WebApp.filter;

import WebApp.dao.DaoUserSql;
import WebApp.entity.User;
import WebApp.freemaker.FreeMaker;
import WebApp.freemaker.FromRequest;
import WebApp.service.UserService;
import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.HashMap;

public class LoginFilter implements Filter {
    private UserService userService = new UserService();
    private FreeMaker freeMarker = new FreeMaker();
    private final Connection connection;


    public LoginFilter(Connection connection) {
        this.connection = connection;
        this.userService = new UserService(new DaoUserSql(connection));
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1;
        if (request instanceof HttpServletRequest) {
            request1 = (HttpServletRequest) request;
        } else {
            throw new IllegalArgumentException("HttpServletRequest error");
        }
        HashMap<String, Object> userData = new HashMap<>();
        if (HttpMethod.POST.name().equalsIgnoreCase(request1.getMethod())) {
            try {
                FromRequest fromRequest = new FromRequest(request1);
                String username = fromRequest.getParamString("Username");
                String password = fromRequest.getParamString("Password");

                User user = new User(username, password);

                if (!userService.checkUsers(user)) {
                    throw new Exception("Incorrect username or password");
                }

            } catch (Exception e) {
                e.printStackTrace();
                userData.put("Information",e.getMessage());
                userData.put("rout", "login");
                ((HttpServletResponse)response).sendRedirect("/login");
            }
            chain.doFilter(request,response);

        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
