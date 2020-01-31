package WebApp.freemaker;

import javax.servlet.http.HttpServletRequest;

public class FromRequest {
    private final HttpServletRequest request;

    public FromRequest(HttpServletRequest request) {
        this.request = request;
    }

    public int getParamInt(String name) {
        if (request.getParameter(name) == null || request.getParameter(name).equals("")) {
            throw new IllegalStateException("GetParameterFrom request error");
        }
        return Integer.parseInt(request.getParameter(name));
    }
    public  String getParamString(String name){
        if(request.getParameter(name)==null || request.getParameter(name).equals("")){
            throw new  IllegalStateException("GetParameterFromRequest error");
        }
        return request.getParameter(name);
    }

}
