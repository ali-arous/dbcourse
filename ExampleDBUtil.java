// Please don't copy paste the entire code! 
// Only copy what you need to.
package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ُEng. Ali Arous
 */
public class ExampleDBUtil {
    private static Connection conn=null;

    public static void connect(){
        //    
        String url="jdbc:sqlite:Hello.db";
        try{
            conn = DriverManager.getConnection(url);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void createNewTable(){

        String sql="CREATE TABLE students("
                + " id integer PRIMARY KEY AUTOINCREMENT,"
                + "name text NOT NULL,"
                + "avg real);";
        try{
            Statement st=conn.createStatement();
            st.execute(sql);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public static void insert(String name,double avg){
        String sql="INSERT INTO students(name,avg) VALUES(?,?)";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setDouble(2, avg);
            pst.executeUpdate();
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
    }
    public static void selectAll(){
        String sql="SELECT id,name,avg FROM students";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.println(
                        rs.getInt("id")+"\t"
                        +rs.getString("name")+"\t"
                        +rs.getDouble("avg")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void updateStudentAvg(int id, double newAvg){
        String sql= "UPDATE students SET avg = ? where id = ?;";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1, newAvg);
            pst.setInt(2, id);
            pst.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void deleteStudent(int id){
        String sql="Delete from students where id = ?;";
        try{
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
}
