package ge.digitaledu.sms.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import ge.digitaledu.sms.model.entity.Model;
import ge.digitaledu.sms.utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * ეს არის კონკრეტული იმპლემენტაცია ყველა მოდელის, სადაც უშუალოდ ხდება სტუდენტზე ან სხვა ენთითიზე მანიპულაცია (შენახვა, წაშლა, ცვლილება, წამოღება და ა.შ.)
 * Entity არის ყველა ინკაფსულირებული კლასი, რომელსაც შევქმნით ინფორმაციის შესანახად ან წამოსაღებად (Student, Manager, Payment, Syllabus და ა.შ.)
 * შესაბამისად, ეს კლასიც, როგორც მომავალში სხვა მოდელები იმპლემენტირებულია MainModel-დან.
 * MainModel-ის სანახავად გადადით მის ინტერფეისზე, აღწერები აქვს მასაც
 * ამ მოდელში კონკრეტულად დაწერილია ყველა მოდელისთვის მონაცემთა ბაზაში შენახვის ლოგიკა
 * ასევე, მოთხოვნის შესაბამისად შეგვიძლია აქვე გადავეთოთ ფაილში ჩაწერის იმპლემენტაციაც, თუ დავუშვათ დამკვეთს არ უნდა ბაზაში შენახვა ინფორმაციის // TODO ორშაბათის მასალა
 * // TODO  ასევე ორშაბათს გავივლით რატომ გამოვიყენე აქ interface და კონტროლერის შემთხვევაში abstract class
 * Hint - ინტერფეისში ლოგიკას ვერ დავწერთ, ვერც მეთოდებს შევქმნით და დავაბრუნებთ მნიშვნელობას, მასშ მხოლოდ აღწერა შეიძლება ინფრომაციის და შემდეგ მასზე მოქმედება
 * ინტერფეისად გამომდინარე კლასი კი შესაძლოა ამ ინტერფეისის ინსტანსიც იყოს. მაგალითი:
 * MainModelService service = new MainModelService(); true
 * MainModel service = new MainModelService(); true
 * MainModel service = new MainModel(); false შეცდომაა, რადგან ინტერფეისი კლასი არ არის და მისგან ობიექტს ვერ შექმნი, ხოლოდ მისიდან გამომდინარე კლასიდან ობიექტის შექმნა ორი ვარიანტით შეიძლება, ორივე ვარიანტი მაღლა წერია (true ვერსიები)
 */
public class MainModelService implements MainModel {

    private Connection databaseConnection() throws SQLException {
        /**
         * Mysql connection
         */
        MysqlDataSource dtSrc = new MysqlDataSource();
        dtSrc.setUser("root");
        dtSrc.setPassword("root");
        dtSrc.setServerName("localhost");
        dtSrc.setDatabaseName("sms");

        return dtSrc.getConnection();
    }

    /**
     * ამ კონკრეტულ იმპლემენტაციაში მე ვიგებ, რომ Student ენთითი მომმართავს და შესაბამისად სტუდენტის ცხრილშ უნდა შევინახო მონაცემები
     * მარტივად ვაწყობ INSERT query-ს
     * TODO ორშაბათის მასალა
     */
    @Override
    public <T> void save(T obj) {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            StringBuilder saveStr = new StringBuilder("INSERT INTO `" + obj.getClass().getSimpleName().toLowerCase() + "` (");

            Field[] fields = obj.getClass().getDeclaredFields();

            String properties = "", values = "";
            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    properties += field.getName().toLowerCase() + ", ";
                }
            }

            /**
             * აქ გვაქვს Utils კლასი, რომელიც დამხმარე, ერთგავერი helper კლასია, რომელიც public-ია და სტატიკური, რაც ნიშნავს, რომ პროექტის ჭრილში ყველგან და ყველასთვის წვდომადია და მისი შეცვლა და მანიპულაცია არ შეგვიძლია
             * TODO ესეც გავიაროთ ორშაბათს, ადრეც გითხარით და ეხლა მაგალითზე ნახავთ, რატომა რის სტატიკური და public
             */
            saveStr.append(Utils.trimRightToLeft(properties, 2));
            saveStr.append(") values (");

            for (Field field : fields) {
                field.setAccessible(true);

                if (!field.getName().equals("id")) {
                    if (field.get(obj) instanceof Model) {
                        values += "'" + ((Model) field.get(obj)).getId() + "', ";
                    } else {
                        values += "'" + field.get(obj) + "', ";
                    }
                }
            }
            saveStr.append(Utils.trimRightToLeft(values, 2));
            saveStr.append(")");

            conn = databaseConnection();
            stm = conn.createStatement();

            stm.execute(saveStr.toString());

            System.out.println("Successfully added to database");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                // catch error
            }
        }
    }

    @Override
    public <T> void delete(T obj, int id) {
        try {
            Field fieldId = obj.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            String dltString = null;
            if (fieldId != null) {
                dltString = "DELETE FROM `" + obj.getClass().getSimpleName().toLowerCase() + "` WHERE id = " + id;
            }

            /**
             * ეს დაბეჭდავს ბაზის DELETE-ს
             */
            System.out.println(dltString); // TODO
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public <T> List<T> getAll(T obj) {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        StringBuilder getStr = new StringBuilder("SELECT * FROM `" + obj.getClass().getSimpleName().toLowerCase() + "`");
        getStr.append(" LIMIT 500"); // TODO must be dynamic

        List<Object> arr = new ArrayList<>();

        try {
            conn = databaseConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery(getStr.toString());

            while (rs.next()) {
                List<Object> elements = new ArrayList<>();

                for (Field field : obj.getClass().getFields()) {
                    field.setAccessible(true);
                    if (field.getType().isEnum()) {
                        elements.add(Enum.valueOf((Class<Enum>) field.getType(), rs.getString(field.getName())));
//                    } else if (){
//                        elements.add(rs.getString(field.getName()));
//                    } else {
                        elements.add(rs.getString(field.getName()));
                    }
                }
                System.out.println(elements);
                Method method = obj.getClass().getMethod("setData", obj.getClass());
                method.invoke(obj, elements.toArray());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                // catch error
            }
        }

        return null;
    }
}
