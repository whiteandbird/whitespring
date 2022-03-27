package per.spring.support;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  11:26
 */
public class BeanDefinition {

    public static String SINGLE_SCOPE = "single";

    public static String PROTO_SCOPE = "proto";
    /**
     * 存储的类
     */
    private Class clazz;

    private String beanName;
}
