package com.gdmatstaffs.demoJSON;

import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/jsondemo")
public class DemoController
{
    @GetMapping
    public List<String> getObjectAsJSONstring()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("Dear Mickey,");
        list.add("I think you're great!");
        list.add("Love,");
        list.add("Minnie xx");

        return list;
    }

    @PostMapping(path = "/create")
    public Person createPerson(@RequestBody Person p)
    {
        return p;
    }

    @GetMapping(path = "/problem")
    public MyA getMyA()
    {
        MyA a = new MyA(1);
        MyB b = new MyB(2);

        a.setB(b);
        b.setA(a);

        return a;
    }
}
