package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcConnectionTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testSelectQuery() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS test");
        jdbcTemplate.execute(
                "CREATE TABLE test (" +
                        "id SERIAL NOT NULL, " +
                        "title VARCHAR(20), " +
                        "sub VARCHAR(20), " +
                        "PRIMARY KEY(id))");

        String[][] data = {{"AAA", "aaa"}, {"BBB", "bbb"}, {"CCC", "ccc"}};
        for (String[] datum : data) {

            jdbcTemplate.update(
                    "INSERT INTO test (title, sub) VALUES (?, ?)",
                    datum[0],
                    datum[1]);
        }

        String sql = "SELECT * FROM test";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        for (int i = 0; i < data.length; i++) {
            String title = ((String) maps.get(i).get("title"));
            String sub = (String) maps.get(i).get("sub");

            assert data[i][0].equals(title);
            assert data[i][1].equals(sub);
        }
    }
}
