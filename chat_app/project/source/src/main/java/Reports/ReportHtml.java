package Reports;

import ModelProviders.*;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportHtml implements IReport {
    private String location = null;
    IMessageProvider messageProvider = new MessageProvider();
    IRoomProvider roomProvider = new RoomProvider();
    IUserProvider userProvider = new UserProvider();
    int name = 1;
    public ReportHtml(String location){
        this.location = location;
    }

    @Override
    public void generateRaport() {
        int p = 1;
        String html = "<div><h1>Rooms and conversations REPORT: " + getDate() + " </h1>";
        List<String> roomIDs = roomProvider.gerRoomdsID();
        for(int j = 0; j < roomIDs.size(); j++){
            String[] c = roomIDs.get(j).split("\\s+");
            for(int k = 0; k < c.length; k++) System.out.println(c[k]);
            html += "<p><h" + p + "> Room no:" + c[0] + ":" + userProvider.getUsernameById(Integer.parseInt(c[1])) + "," + userProvider.getUsernameById(Integer.parseInt(c[2])) + " </h" + p + ">";

            List<String> ConvId = messageProvider.getAllMessagesByRoom(Integer.parseInt(c[0])); //iau fiecare camera cu conversatiile ei
            for (int i = 0; i < ConvId.size(); i++) {
                String[] s = ConvId.get(i).split("\\s+");

                int id = Integer.parseInt(s[0]);
                String name =  userProvider.getUsernameById(id);

                s[0] = name;
                String str = String.join(" ", s);
               html += str + "</p>";
            }
            html+= "</div>";
        }

        File f = new File(location + name++ +".html");
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(html);
            bw.close();
            JOptionPane.showMessageDialog(null, "Report generated");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Report generating failed");
        }
    }

    public String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


}
