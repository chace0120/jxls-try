import entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.jxls.util.Util;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * @author chenx
 * @date 2018/4/20
 */
@RunWith(JUnit4.class)
public class DemoTest {
    @Test
    public void testObjectCollectionDemo() throws IOException {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Bob", LocalDate.of(1989, 10, 30),
                new BigDecimal("1000"), new BigDecimal("20")));
        employees.add(new Employee("Tomy", LocalDate.of(1989, 8, 30),
                new BigDecimal("1200"), new BigDecimal("10")));

        try(InputStream is = DemoTest.class.getResourceAsStream("object_collection.xlsx")) {
            try (OutputStream os = new FileOutputStream("target/object_collection_output.xlsx")) {
                Context context = new Context();
                context.putVar("employees", employees);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        }
    }

    @Test
    public void testImageDemo() throws IOException {
        try(InputStream is = DemoTest.class.getResourceAsStream("image.xlsx");
            OutputStream os = new FileOutputStream("target/image_output.xlsx");
            InputStream imageStream = DemoTest.class.getResourceAsStream("p1.jpg")) {
                Context context = new Context();
                context.putVar("image", Util.toByteArray(imageStream));
                JxlsHelper.getInstance().processTemplate(is, os, context);
        }
    }

    @Test
    public void testGridDemo() throws IOException {
        try(InputStream is = DemoTest.class.getResourceAsStream("grid.xlsx");
            OutputStream os = new FileOutputStream("target/grid_output.xlsx")) {
            Context context = new Context();
            context.putVar("headers", Arrays.asList("Arrival Date", "", "Hilton Hasbrouck Heights/Meadowlands", "Holiday Inn Hasbrouck Heights/Meadowlandss"));
            List<Map<String, Object>> datas = new ArrayList<>();
            Map<String, Object> data = new HashMap<>();
            data.put("arrivalDate", LocalDate.of(2017, 4, 20));
            data.put("holiday", "");
            data.put("hhhPrice", "$195.23(-25)");
            data.put("hihhPrice", "$198(0)");
            datas.add(data);
            data = new HashMap<>();
            data.put("arrivalDate", LocalDate.of(2017, 4, 21));
            data.put("holiday", "New Year");
            data.put("hhhPrice", "$190.25(+2)");
            data.put("hihhPrice", "$198(0)");
            datas.add(data);

            context.putVar("data", datas);
            JxlsHelper.getInstance().processGridTemplate(is, os, context, "arrivalDate,holiday,hhhPrice,hihhPrice");
        }
    }

    @Test
    public void testScheduleConfig() throws IOException {
        try (InputStream is = DemoTest.class.getResourceAsStream("schedule-config-tmpl.xlsx");
             OutputStream os = new FileOutputStream("target/schedule-config-tmpl.xlsx")) {
            Context context = new Context();
            List<String> weekDays = new ArrayList<>();
            weekDays.add("Fri");
            weekDays.add("Sat");
            weekDays.add("Sun");
            weekDays.add("Mon");
            weekDays.add("Tus");
            weekDays.add("Wed");
            weekDays.add("Thu");
            context.putVar("weekDays", weekDays);

            List<String> dates = new ArrayList<>();
            dates.add("05/05");
            dates.add("05/06");
            dates.add("05/07");
            dates.add("05/08");
            dates.add("05/09");
            dates.add("05/10");
            dates.add("05/11");
            context.putVar("dates", dates);

            List<Map<String, Object>> configs = new ArrayList<>();
            Map<String, Object> config = new HashMap<>();
            config.put("item", "foreacast on the book");
            List<String> datas = new ArrayList<>();
            datas.add("50%");
            datas.add("25%");
            datas.add("35%");
            datas.add("45%");
            datas.add("56%");
            datas.add("70%");
            datas.add("78%");
            config.put("datas", datas);
            configs.add(config);

            config = new HashMap<>();
            config.put("item", "Actual on the book");
            datas = new ArrayList<>();
            datas.add("50%");
            datas.add("25%");
            datas.add("35%");
            datas.add("45%");
            datas.add("56%");
            datas.add("70%");
            datas.add("78%");
            config.put("datas", datas);
            configs.add(config);

            context.putVar("configs", configs);

            JxlsHelper.getInstance().processTemplate(is, os, context);
        }
    }
}
