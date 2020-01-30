package SomeStuffs;

import SomeStuffs.LoginServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebApp {
    public static void main(String[] args) throws Exception {
        String string = "To Check Sonarqube changing on server";
        Server server = new Server(8082);
        LoginServlet loginServlet = new LoginServlet();
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(loginServlet), "/login");
        server.setHandler(handler);
        server.start();
        server.join();

    }
}
