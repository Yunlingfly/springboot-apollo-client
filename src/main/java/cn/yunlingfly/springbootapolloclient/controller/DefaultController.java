package cn.yunlingfly.springbootapolloclient.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableApolloConfig
public class DefaultController {
    @ApolloConfig("application")
    private Config config; //inject config for namespace application

    @Value("${key2:test}")//如果配置中心没有值，默认key为key2的value值为test
    private String name;

    //config change listener for namespace application
    @ApolloConfigChangeListener("application")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        ConfigChange change = changeEvent.getChange("key2");
        System.out.println(String.format("Found change - key: %s, oldValue: %s,"
                + " newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
    }

    // 通过注解的方式
    @RequestMapping(value = "/getIndex",method = RequestMethod.GET)
    public String index(){
        return name;
    }

    // 通过Api的方式（不推荐）
    @RequestMapping(value = "/apiIndex",method = RequestMethod.GET)
    public String apiIndex(){
        // 使用默认命名空间"application"
        String someKey = "key2";
        String someDefaultValue = "defaultValue";
        String value = config.getProperty(someKey, someDefaultValue);
        return value;
    }
}
