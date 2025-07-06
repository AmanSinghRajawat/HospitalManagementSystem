import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointments {

    private Connection connection;
    private Scanner scanner;
    DBConnection dbConnection = new DBConnection();

    Patients patients = new Patients();
    Doctors doctors = new Doctors();

    public Appointments() throws SQLException {
        this.connection = dbConnection.getConnections();
        this.scanner = dbConnection.getScanner();
    }


    public void bookAppointments(){

        try {

            System.out.print("Enter Patient ID : ");
            int patientId = scanner.nextInt();
            System.out.print("Enter Doctors ID : ");
            int doctorId = scanner.nextInt();
            System.out.print("Enter Appointment Date (DD/MM/YYYY) : ");
            String bookAppointmentDate = scanner.next();

            String isAppointmentDateAvailable = "SELECT * FROM appointments WHERE appointmentDate = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(isAppointmentDateAvailable);
            preparedStatement.setString(1,bookAppointmentDate);
            ResultSet isAppointmentAvailable = preparedStatement.executeQuery();

            if(!isAppointmentAvailable.next()){

//                int appointmentId = isAppointmentAvailable.getInt(1);  resultSet null h. to getInt(1) kuch v return ni krega. isliye ye line exception throw kregi.

                boolean isPatientPresent = patients.viewPatientByPatientId(patientId);
                boolean isDoctorPresent = doctors.viewDoctorsByDoctorId(doctorId);

                String query = "INSERT INTO appointments(patientId, doctorId, appointmentDate) VALUES (?,?,?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query);  // creating second PreparedStatement object, kyuki ek preparedStatement object ek he bar use kr sakte h.
                // yaha par parameterIndexing "(patientId, doctorId, appointmentDate) ya values (?,?,?)" k base pr ho rhi h.
                preparedStatement1.setInt(1, patientId);
                preparedStatement1.setInt(2, doctorId);
                preparedStatement1.setString(3, bookAppointmentDate);


                if(isPatientPresent && isDoctorPresent) {
                    preparedStatement1.executeUpdate();
                    System.out.println("Appointment Booked Successfully!");
                }else {
                    System.out.println("Failed to booked Appointment!");
                }
            }else {
                System.out.println("Failed to booked Appointment, Because Appointment Date is not available, Please Select the Other Date to Book Appointment.");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
