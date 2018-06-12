package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * 指定配置类
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    /**
     * 将 DipactcherServert 映射到"/"
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }
}
