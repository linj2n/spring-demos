package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class};
    }

    /**
     * 指定配置类
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class};
    }

    /**
     * 将 DipactcherServert 映射到"/"
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }
}
