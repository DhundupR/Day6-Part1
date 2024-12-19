







import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterOutputStream;
import java.util.*;
















public class Main {
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/Input.txt");
        String[][] map = new String[fileData.size()][fileData.get(0).length()];
        String[][] copLocations = new String[1][2];
        int amtOfCops = 0;
        for(int i = 0; i < fileData.size(); i++){
            String[] vals = fileData.get(i).split("");
            for(int k = 0; k < vals.length; k++ ){
                map[i][k] = vals[k];
                if(vals[k].equals("^")){
                    copLocations[0][0] = String.valueOf(i);
                    copLocations[0][1] = String.valueOf(k);
                }




            }
        }
        System.out.println(map[6][4]);
        System.out.println(map.length);


        String currentPos = map[copLocations[0].length][copLocations.length];
        System.out.println("");
        String[][] newMap = move(map,copLocations);

        for(int i = 0; i < fileData.size(); i++){
            String[] vals = fileData.get(i).split("");
            for(int k = 0; k < vals.length; k++ ){
                if(map[i][k] == "X" ){
                    amtOfCops++;
                }

            }
        }
        amtOfCops++;
        System.out.println(amtOfCops);






    }




    public static String[][] move(String[][] map, String[][] copLocation){
        boolean collsion = false;
        boolean end = false;
        int amt = 0;
        while(!end){
            String currentPos = ((map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])]));
            System.out.println(amt);

            for(int i = 0; i < map.length; i++){
                System.out.println(Arrays.toString(map[i]));
            }
            System.out.println("");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }






            switch (currentPos){
                case("^") -> {
                    copLocation[0][0] = String.valueOf(Integer.parseInt(copLocation[0][0]) - 1);
                    if(safe(map,copLocation)){
                        amt++;
                        map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])] = "^";
                        map[Integer.parseInt(copLocation[0][0])+1][Integer.parseInt(copLocation[0][1])] = "X";
                    } else {
                        try {
                            map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])] = "#";
                            map[Integer.parseInt(copLocation[0][0])+1][Integer.parseInt(copLocation[0][1])] = ">";
                            copLocation[0][0] = String.valueOf(Integer.parseInt(copLocation[0][0]) + 1);
                        }catch (Exception e){
                            return map;
                        }
                    }
                }


                case("|") -> {
                    copLocation[0][0] = String.valueOf(Integer.parseInt(copLocation[0][0]) + 1);
                    if(safe(map,copLocation)){
                        amt++;
                        map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])] = "|";
                        map[Integer.parseInt(copLocation[0][0])-1][Integer.parseInt(copLocation[0][1])] = "X";
                    } else {
                        try {
                            map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])] = "#";
                            map[Integer.parseInt(copLocation[0][0])-1][Integer.parseInt(copLocation[0][1])] = "<";
                            copLocation[0][0] = String.valueOf(Integer.parseInt(copLocation[0][0]) - 1);


                        }catch (Exception e){
                            return map;
                        }
                    }
                }


                case(">") -> {
                    copLocation[0][1] = String.valueOf(Integer.parseInt(copLocation[0][1]) + 1);
                    if(safe(map,copLocation)){
                        amt++;
                        map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])] = ">";
                        map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])-1] = "X";
                    } else {
                        try {map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])] = "#";
                            map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])-1] = "|";
                            copLocation[0][1] = String.valueOf(Integer.parseInt(copLocation[0][1]) - 1);
                        }catch (Exception e){
                            return map;
                        }
                    }
                }


                case("<") -> {
                    copLocation[0][1] = String.valueOf(Integer.parseInt(copLocation[0][1]) - 1);
                    if(safe(map,copLocation)){
                        amt++;
                        map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])] = "<";
                        map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])+1] = "X";
                    } else {
                        try {
                            map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])] = "#";
                            map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])+1] = "^";
                            copLocation[0][1] = String.valueOf(Integer.parseInt(copLocation[0][1]) + 1);
                        }catch (Exception e){
                            return map;
                        }
                    }
                }




            }
        }
        return map;
    }


    public static boolean safe(String[][] map,String[][] copLocation){
        try{
            String currentPos = map[Integer.parseInt(copLocation[0][0])][Integer.parseInt(copLocation[0][1])];
            if(Objects.equals(currentPos, "#")){
                return false;
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }












    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}












