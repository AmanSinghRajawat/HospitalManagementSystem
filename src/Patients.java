import java.sql.*;
import java.util.Scanner;

public class Patients {

//    private String url = "jdbc:mysql://localhost:3306/hospitals";
//    private String username = "root";
//    private String password = "root";

    private Connection connection;
    private Scanner scanner;

    DBConnection dbConnection = new DBConnection();

    public Patients() throws SQLException{
        this.connection = dbConnection.getConnections();
        this.scanner = dbConnection.getScanner();
    }


    public void addPatients(){
        System.out.print("Enter Patient ID : ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Patient Name : ");
        String name = scanner.next();
        System.out.println("Enter Patient Age : ");
        int age = scanner.nextInt();
        System.out.println("Enter Patient Gender : ");
        String gender = scanner.next();
        System.out.println("Enter Patient Address : ");
        String address = scanner.next();
        System.out.println("Enter Diseases's : ");
        String diseases = scanner.next();
        System.out.println("Enter Patient Phone No : ");
        int phoneNo = scanner.nextInt();

        try {
            String query = "INSERT INTO patients(patientId, name, age, gender, address, diseases, phoneNo ) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,patientId);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, diseases);
            preparedStatement.setInt(7, phoneNo);


            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected > 0){
                System.out.println("Patient Added Successfully!");
            }else {
                System.out.println("Failed to add patient!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }



    public void viewPatients(){

        try{
            System.out.println("Patients");

            System.out.println("+-----------+-------------------+-----+--------+---------------------+------------------+------------+");
            System.out.println("| patientId |       name        | age | gender |       address       |     diseases     |  phoneNo   |");
            System.out.println("+-----------+-------------------+-----+--------+---------------------+------------------+------------+");

            String query = "SELECT * FROM patients";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int patientId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String gender = resultSet.getString(4);
                String address = resultSet.getString(5);
                String diseases = resultSet.getString(6);
                int phoneNo = resultSet.getInt(7);

                System.out.printf("| %-9s | %-17s | %-3s | %-6s | %-19s | %-16s | %-10s |\n", patientId, name, age, gender,address,diseases,phoneNo);
                System.out.println("+-----------+-------------------+-----+--------+---------------------+------------------+------------+");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void viewPatientsById(){
        try{
            System.out.println("Enter Patient ID : ");
            int patientId = scanner.nextInt();

            String query = "SELECT * FROM patients WHERE patientId = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                String gender = resultSet.getString(4);
                String address = resultSet.getString(5);
                String diseases = resultSet.getString(6);
                int phoneNo = resultSet.getInt(7);

                System.out.println("+-----------+-------------------+-----+--------+---------------------+------------------+------------+");
                System.out.println("| patientId |       name        | age | gender |       address       |     diseases     |  phoneNo   |");
                System.out.println("+-----------+-------------------+-----+--------+---------------------+------------------+------------+");

                System.out.printf("| %-9s | %-17s | %-3s | %-6s | %-19s | %-16s | %-10s |\n", id, name, age, gender,address,diseases,phoneNo);
                System.out.println("+-----------+-------------------+-----+--------+---------------------+------------------+------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean viewPatientByPatientId(int patientId){

        try {
            String query = "SELECT * FROM patients WHERE patientId = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
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
