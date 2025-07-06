import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors {

    private Connection connection;
    private Scanner scanner;

    DBConnection dbConnection = new DBConnection();

    public Doctors() throws SQLException {
        this.connection = dbConnection.getConnections();
        this.scanner = dbConnection.getScanner();
    }

    public void viewDoctors(){

        try {

            System.out.println("Doctors");

            System.out.println("+----------+----------------------+-----------------------------+-----------------+------------------------+");
            System.out.println("| DoctorId |        Name          |       Specializations       |     PhoneNo     |          Address       |");
            System.out.println("+----------+----------------------+-----------------------------+-----------------+------------------------+");

            String query = "SELECT * FROM doctors";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int doctorId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String specialization = resultSet.getString(3);
                int phoneNo = resultSet.getInt(4);
                String address = resultSet.getString(5);
                System.out.printf("| %-8s | %-20s | %-27s | %-15s | %-22s |\n", doctorId, name, specialization, phoneNo, address);
                System.out.println("+----------+----------------------+-----------------------------+-----------------+------------------------+");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    
    
    public void viewDoctorsById() {

        try {
            System.out.println("Enter Doctor ID : ");
            int doctorId = scanner.nextInt();
            String query = "SELECT * FROM doctors WHERE doctorId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String specialization = resultSet.getString(3);
                int phoneNo = resultSet.getInt(4);
                String address = resultSet.getString(5);
                System.out.println("Doctor By DoctorID : " + doctorId);

                System.out.println("+----------+----------------------+-----------------------------+-----------------+------------------------+");
                System.out.println("| DoctorId |        Name          |       Specializations       |     PhoneNo     |          Address       |");
                System.out.println("+----------+----------------------+-----------------------------+-----------------+------------------------+");

                System.out.printf("| %-8s | %-20s | %-27s | %-15s | %-22s |\n", doctorId, name, specialization, phoneNo, address);
                System.out.println("+----------+----------------------+-----------------------------+-----------------+------------------------+");
            }else{
                System.out.println("Doctor not found with ID : "+doctorId+", Please Enter the Valid DoctorId.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean viewDoctorsByDoctorId(int doctorId){

            try {
                String query = "SELECT * FROM doctors WHERE doctorId = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, doctorId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    return true;
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
    }


}
