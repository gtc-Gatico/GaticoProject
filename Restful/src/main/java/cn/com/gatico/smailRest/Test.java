package cn.com.gatico.smailRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Test {
    @Autowired
    private JdbcTemplate clickhouseJdbcTemplate;

    @RequestMapping(value = "/test", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> modify() {
        List<String> stringList = new ArrayList<>();
        clickhouseJdbcTemplate.query("select * from dpi_flow", resultSet -> {
            if(resultSet.next()){
                String tmp = resultSet.getString("serial");
                stringList.add(tmp);
            }
        });
        return ResponseEntity.ok(stringList);
    }

}
