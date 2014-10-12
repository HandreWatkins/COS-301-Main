 import java.io.FileWriter;
 import java.io.IOException;

 public class GenerateCsv
 {


    private static void generateCsvFile(String fileName)
    {
           try
           {
                FileWriter writer = new FileWriter(fileName);

                writer.append("Email");
                writer.append(',');
                writer.append("Name");
                writer.append('\n');

               /* for (User user in users) {
                     writer.append(user.getEmail());
                     writer.append(',');
                     writer.append(user.getName());
                     writer.append('\n');
                }*/

                writer.flush();
                writer.close();
           } catch(IOException e) {
                 e.printStackTrace();
           } 
      }
 }