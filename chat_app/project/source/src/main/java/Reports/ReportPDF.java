package Reports;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ModelProviders.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;

public class ReportPDF implements IReport {
    String location = null;
    int name = 1;

    IMessageProvider messageProvider = new MessageProvider();
    IRoomProvider roomProvider = new RoomProvider();
    IUserProvider userProvider = new UserProvider();

    public ReportPDF(String location){
        this.location = location;
    }
    @Override
    public void generateRaport() {
        Document document = new Document();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(location + name++ +".pdf"));
            document.open();

            document.add(new Paragraph("Rooms and conversations REPORT: " + getDate()));
            document.add(new Paragraph(" "));
            String string = "";
            String s2 = "";

            List<String> roomIDs = roomProvider.gerRoomdsID();
            for(int j = 0; j < roomIDs.size(); j++){
                String[] c = roomIDs.get(j).split("\\s+");
                for(int k = 0; k < c.length; k++) System.out.println(c[k]);
                string = "Room no:" + c[0] + ":" + userProvider.getUsernameById(Integer.parseInt(c[1])) + "," + userProvider.getUsernameById(Integer.parseInt(c[2]));
                document.add(new Paragraph(string));
                document.add(new Paragraph(" "));
                List<String> ConvId = messageProvider.getAllMessagesByRoom(Integer.parseInt(c[0])); //iau fiecare camera cu conversatiile ei
                for (int i = 0; i < ConvId.size(); i++) {
                    String[] s = ConvId.get(i).split("\\s+");

                    int id = Integer.parseInt(s[0]);
                    String name =  userProvider.getUsernameById(id);

                    s[0] = name;
                    String str = String.join(" ", s);
                    s2 = str;
                    document.add(new Paragraph(s2));
                }
                document.add(new Paragraph(" "));
            }
            document.add(new Paragraph(" "));

            document.close();
            writer.close();
        } catch (DocumentException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Report NOT generated");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Report NOT generated");
        }
    }
    public String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
