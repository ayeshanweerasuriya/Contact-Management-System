import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validator {

    public static boolean phoneNumberValidator(String phoneNumber) {
        try {
            if (phoneNumber.charAt(0) != '0' || phoneNumber.length() > 10 || phoneNumber.length() < 10) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    // salary validator
    public static boolean salaryValidator(String salary) {
        try {
            double salaryDouble = Double.parseDouble(salary);
            if (salaryDouble < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        for (int i = 0; i < salary.length(); i++) {
            if (Character.isLetter(salary.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // validate birthday
    public static boolean dataValidator(String birthdayString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate birthday = LocalDate.parse(birthdayString, dateFormatter);

            LocalDate currentDate = LocalDate.now();

            return !birthday.isAfter(currentDate);
        } catch (Exception e) {
            return false;
        }
    }
}
