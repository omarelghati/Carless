package controllers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.Connection;

import application.SqliteConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class DragDrop implements Initializable {
    @FXML Button terminer;
    @FXML ImageView dragImage;
    @FXML Label warning;

	public String format(String date) {
		String[] splitted =date.split("/");
		String year = splitted[2];
		String month = splitted[1];
		String day = splitted[0];
		return year+"-"+month+"-"+day;
	}
    	public void importF(String file)
    	{
    		try{
    			Connection connection = (Connection) SqliteConnection.connector();
        		XSSFWorkbook wb = new XSSFWorkbook(file);
        	       XSSFSheet sheet = wb.getSheetAt(0);
        	       FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        	       for (Row ligne : sheet) 	{
        	    	   String[] tmp = new String[16];
									 if(checkIfRowIsEmpty(ligne)) break;
									if(ligne.getRowNum()!=0){
        	    		   for (Cell cell : ligne) {
        	    			   				if(cell.getCellType()==Cell.CELL_TYPE_STRING)
											 tmp[cell.getColumnIndex()]=(String)cell.getStringCellValue().trim();
        	    			   				if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
        	    			   					{cell.setCellType(Cell.CELL_TYPE_STRING);
   											 tmp[cell.getColumnIndex()]=(String)cell.getStringCellValue().trim();
											 }
										 }
        	    	        		 String s1=tmp[0];
									 String s2= tmp[1];
        	    	        		 String s3=tmp[2];
        	    	        		 String s4=tmp[3];
        	    	        		 String s5=tmp[4];
        	    	        		 String s6=tmp[5];
        	    	        		 String s7=tmp[6];
        	    	        		 String s8=tmp[7];
        	    	        		 Double d8 = Double.parseDouble(tmp[8]);
        	    	        		 Double s9 = Double.parseDouble(tmp[9]);
        	    	        		 Double s10 = Double.valueOf(tmp[10]);
        	    	        		 Double s11 = Double.parseDouble(tmp[11]);
        	    	        		 String s12 = tmp[12];
        	    	        		 String s13 = tmp[13];
        	    	        		 Double s14 = Double.parseDouble(tmp[14]);
        	    	        		 System.out.println(s2+"   "+s3);
        	    	        		 String s15=tmp[15];
        	    	        		 String query ="INSERT INTO `assurance`(`etatAssr`, `DateDebut`, `datefin`) VALUES('oui','"+s7+"','"+s8+"')";
        	    	        		 System.out.println(connection.createStatement().executeUpdate(query));
        	    	        		 String query2 ="SELECT `id_assurance` FROM `assurance` WHERE DateDebut = '"+s7+"'and Datefin='"+s8+"'";
        	    	        		 ResultSet re= connection.createStatement().executeQuery(query2);
        	    	        		 re.next();
        	    	        		 int res=re.getInt(1);
        	    	        		 query = "INSERT INTO Vehicule VALUES('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"',"+res+",'"+d8+"','"+s9+"','"+s10+"','"+s11+"','"+s12+"','"+s13+"','"+s14+"','"+s15+"')";
        	    	        		 System.out.println(connection.createStatement().executeUpdate(query));
            	 	   }
        	     }
        	       //wb.close();
    		} catch(NumberFormatException e)
    		{
    			e.printStackTrace();
    			System.err.println("format exception");
    		}catch(SQLException e2)
    		{
    			System.err.println("sql exception !");
    			e2.printStackTrace();
    		}
    		catch(IOException e3)
    		{
    			System.err.println("io exception");
    		}
    	}
    	String filePath =null;
    	public void dragdropped(DragEvent event)  {
    	try{
        	Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;

                for (File file:db.getFiles()) {
                    filePath = file.getAbsolutePath();
    				filePath=filePath.replace("\\", "/");
    				System.out.println("file "+filePath);

                }
          }
            event.setDropCompleted(success);
            event.consume();
        }catch(Exception E){
        	System.err.println("hna le probl�me");
        }
    	}

    	public void dragover(DragEvent event) {
    		Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
    	}
    	public void exitfunc() throws SQLException, IOException
    	{
    		importF(filePath);
			Stage s = (Stage) terminer.getScene().getWindow();
			s.close();
    	}
	private boolean checkIfRowIsEmpty(Row row) {
		if (row == null) {
			return true;
		}
		if (row.getLastCellNum() <= 0) {
			return true;
		}
		boolean isEmptyRow = true;
		for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
			Cell cell = row.getCell(cellNum);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK ) {
				isEmptyRow = false;
			}
		}
		return isEmptyRow;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}