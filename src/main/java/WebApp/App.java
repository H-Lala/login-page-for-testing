package WebApp;

import WebApp.db.DbConnection;
import WebApp.filter.LoginFilter;
import WebApp.servlets.LoginServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class App {
    public static void main(String[] args) throws Exception {
        Connection connection = DbConnection.getConnection();
        Server server = new Server(8085);
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new LoginServlet(connection)), "/login/*");
        handler.addFilter(new FilterHolder(new LoginFilter(connection)),"/login/*", EnumSet.of(DispatcherType.REQUEST));


        server.setHandler(handler);
        server.start();
        server.join();

    }

}
