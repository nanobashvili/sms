package ge.digitaledu.sms.model;

import java.util.List;

/**
 * ეს არის მთავარი მოდელის (მშობელი მოდელის) ინტერფეისის მაგალითი, რომელიც პრინციპით გაქვს აბსტრაქტულ კლასს, მაგრად ოდნავ განსხვავებები აქვს, რომელსაც ორშაბათს გავივლით
 * აღწერილია 2 მეთოდი, შენახვა და წაშლა
 * მოცემულია Generics მაგალითი
 * // TODO ორშაბათის მასალა
 * <T> ნიშნავს, რომ წინასწარ არ მეთოდმა არ იცის რა ტიპის ობიექტი გადმოეცემა და რას დააბრუნებს
 * შეიძლება ეს იყოს ჩვენს შემთხვევაში Student, ან სხვა რომელსაც შევქმნით მომავალში
 * შესაბამისად, რომ არ გავადუბლიროთ save და delete  მეთოდები ყოველი მოდელისთვის, მარტო იმიტომ, რომ ტიპი არ ვიცით, ვიყენებთ generic-ს.
 */
public interface MainModel {
    <T> void save(T obj);

    <T> void delete(T obj, int id);

    <T> List<T> getAll(T obj);
}
