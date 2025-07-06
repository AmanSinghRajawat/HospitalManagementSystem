import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Patients patients = null;
        Doctors doctors = null;
        Appointments appointments = null;

        try{
            patients = new Patients();
            doctors = new Doctors();
            appointments = new Appointments();
        }catch (SQLException e){
            e.printStackTrace();
        }




        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patient");
            System.out.println("3. View Patient By ID");
            System.out.println("4. View Doctors");
            System.out.println("5. View Doctor By ID");
            System.out.println("6. Book Appointment");
            System.out.println("7. Exit");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    patients.addPatients();
                    break;
                case 2:
                    patients.viewPatients();
                    break;
                case 3:
                    patients.viewPatientsById();
                    break;
                case 4:
                    doctors.viewDoctors();
                    break;
                case 5:
                    doctors.viewDoctorsById();
                    break;
                case 6:
                    appointments.bookAppointments();
                    break;
                case 7:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Enter the valid choice!");
            }

        }

    }
}