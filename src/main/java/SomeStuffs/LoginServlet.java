package SomeStuffs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String name = req.getParameter("name");
         String password = req.getParameter("password");
        PrintWriter writer = resp.getWriter();
            if(name.equalsIgnoreCase("user") && password.equalsIgnoreCase("111")) {
                String htmlRespone = "<html>";
                htmlRespone += "<h2>Your username is: " + name + "<br/>";
                htmlRespone += "Your password is: " + password + "</h2>";
                htmlRespone += "</html>";

                // return response
                writer.println(htmlRespone);
                writer.close();
            }
    }
}
