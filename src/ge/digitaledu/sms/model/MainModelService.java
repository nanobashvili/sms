package ge.digitaledu.sms.model;

import ge.digitaledu.sms.utils.Utils;

import java.lang.reflect.Field;

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

    /**
     * ამ კონკრეტულ იმპლემენტაციაში მე ვიგებ, რომ Student ენთითი მომმართავს და შესაბამისად სტუდენტის ცხრილშ უნდა შევინახო მონაცემები
     * მარტივად ვაწყობ INSERT query-ს
     * TODO ორშაბათის მასალა
     */
    @Override
    public <T> void save(T obj) {
        try {
            StringBuilder saveStr = new StringBuilder("INSERT INTO `" + obj.getClass().getSimpleName().toLowerCase() + "` (");

            Field[] fields = obj.getClass().getDeclaredFields();

            String properties = "", values = "";
            for (Field field : fields) {
                if (!field.equals("id")) {
                    properties += field.getName() + ", ";
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
                if (!field.equals("id")) {
                    values += field.get(obj) + ", ";
                }
            }
            saveStr.append(Utils.trimRightToLeft(values.toString(), 2));
            saveStr.append(")");

            /**
             * დაბეჭდავს ბაზის INSERT-ს
             */
            System.out.println(saveStr); // TODO
        } catch (Exception ex) {
            ex.printStackTrace();
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
}