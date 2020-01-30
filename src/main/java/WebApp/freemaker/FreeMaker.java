package WebApp.freemaker;

import freemarker.template.*;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;


public class FreeMaker {
private  Configuration configuration;

    public FreeMaker(final String path) {
        this.configuration = new Configuration(Configuration.VERSION_2_3_28){
            {
                try {
                    setDirectoryForTemplateLoading(new File(path));
                    setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
                    setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                    setLogTemplateExceptions(false);
                    setWrapUncheckedExceptions(true);

                } catch (IOException e) {
                    throw new IllegalStateException("Something went wrong , freemarker");
                }
            }



        };
    }
    public  void render(final String templateFile, final Map<String, Object> data, final HttpServletResponse response){
        try {
            response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            configuration.getTemplate(templateFile).process(data,response.getWriter());
        } catch (IOException | TemplateException e) {
            throw  new RuntimeException("Something went wrong , problem related with render",e);
        }

    }
    public FreeMaker(){
        this("./content");
    }
}
