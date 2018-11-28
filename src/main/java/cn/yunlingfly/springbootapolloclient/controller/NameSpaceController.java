package cn.yunlingfly.springbootapolloclient.controller;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 指定命名空间
@EnableApolloConfig("aaa")
public class NameSpaceController {
    @Value("${namespace:test}")//如果配置中心没有值，使用命名空间aaa，默认key为namespace的value值为test
    private String namespace;

    // 使用命名空间
    @RequestMapping(value = "/namespace",method = RequestMethod.GET)
    public String namespace(){
        return namespace;
    }
}
