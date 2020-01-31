package WebApp.servlets;

import WebApp.cookie.Cookies;
import WebApp.db.DbConnection;
import WebApp.entity.User;
import WebApp.freemaker.FreeMaker;
import WebApp.freemaker.FromRequest;
import WebApp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private Cookies cookies;
    FreeMaker freeMaker = new FreeMaker();
    private Connection connection;
    UserService userService = new UserService();


    public LoginServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        List<String> fields = new ArrayList<>();
        fields.add("Username");
        fields.add("Password");
        data.put("fields", fields);
        data.put("message", "Please sign in");
        data.put("rout","/login");
        freeMaker.render("form.ftl",data,resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FromRequest request = new FromRequest(req);
        cookies = new Cookies(req, resp);
        String username = request.getParamString("Username");
        String password = request.getParamString("Password");
        User user = new User(username, password);
        cookies.saveCookies(userService.getLogin(user));
        resp.sendRedirect("https://owasp.org/");
    }

}
