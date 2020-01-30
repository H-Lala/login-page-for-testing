package WebApp.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class Cookies  {
    private String cookieName = "users";
    private HttpServletRequest request;
    private HttpServletResponse response;

    public Cookies(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    public Cookie getCookies(){
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length>0){
            for (Cookie c: cookies){
                if(c.getName().equalsIgnoreCase(cookieName)){
                    cookie = c;
                }
            }
        }
        return cookie;
    }
    public void saveCookies(int value){
        response.addCookie(new Cookie(cookieName,String.valueOf(value)));
    }

    public void  deleteCookies(int value){
        Arrays.stream(request.getCookies())
                .filter(c->c.getName().equals(cookieName))
                .map(c->new Cookie(c.getName(),c.getValue())
                {{setMaxAge(0);}})
                .forEach(response::addCookie);;
    }
}
