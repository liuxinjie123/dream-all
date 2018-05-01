package com.dream.controller.java8;

import com.dream.dao.user.UserDAO;
import com.dream.vo.Result;
import org.jboss.netty.handler.codec.frame.FixedLengthFrameDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Comparator.comparing;

@RequestMapping("/java8")
@RestController
public class JavaEightTestController {

    @GetMapping("/test001/{type}")
    public Result java8Test001(@PathVariable("type") String type) {
        List<UserDAO> userList = new ArrayList<>();
        Supplier<UserDAO> c1 = UserDAO::new;
        UserDAO u1 = c1.get();
        u1.setUsername("jack");
        userList.add(u1);
        Function<String, UserDAO> c2 = UserDAO::new;
        UserDAO u2 = c2.apply("liuxinjie");
        userList.add(u2);
        if ("01".equals(type)) {
            userList.sort(comparing(UserDAO::getUsername));
        } else if ("02".equals(type)) {
            userList.sort(comparing(UserDAO::getUsername).reversed());
        }
        return Result.success().setData(userList);
    }

    @GetMapping("/test002/{type}")
    public Result java8Test002(@PathVariable("type") String type) {
        if ("01".equals(type)) {
            return Result.success().setData(new File(".").listFiles(File::isHidden));
        } else if ("02".equals(type)) {
            return Result.success().setData(new File(".").listFiles(File::isHidden));
        } else {
            return Result.success();
        }
    }
}
