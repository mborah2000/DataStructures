import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LifeGuards {
    public static void main(String[] args){

        for(int file = 1 ; file <= 10 ; file++) {
            try{
                BufferedReader reader = new BufferedReader(new FileReader(new File(file + ".in")));
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));

                int count = -1;
                int guards = 0;
                String currentLine = reader.readLine();
                guards = Integer.parseInt(currentLine);
                int[][] shifts = new int[guards][2];

                while ((currentLine = reader.readLine()) != null) {
                    if(currentLine.isEmpty()) break;
                    count++;
                    String[] elements = currentLine.split("\\s+");
                    shifts[count][0] = Integer.parseInt(elements[0]);
                    shifts[count][1] = Integer.parseInt(elements[1]);
                }

                System.out.println("File : " + file);
                int totalCover = 0;
                int[] lonewolf = new int[guards];
                int overlap = 0;
                Set<Integer> pair = null;
                Map<Set<Integer>,Integer> map = new HashMap<>();
                for(int i = 0; i < shifts.length ; i++){
                    lonewolf[i] = shifts[i][1] - shifts[i][0];
                    for(int j = 0 ; j < shifts.length ; j++){
                        if(i != j){
                            pair = new HashSet<>();
                            if (shifts[j][1] < shifts[i][1] && shifts[j][0] < shifts[i][0] && shifts[j][1] > shifts[i][0]){
                                lonewolf[i] = lonewolf[i] - (shifts[j][1] - shifts[i][0]);
                                pair.add(i);
                                pair.add(j);
                                if(!map.containsKey(pair)){
                                    map.put(pair,shifts[j][1] - shifts[i][0]);
                                } else if (map.get(pair) == 0) {
                                    map.put(pair,shifts[j][1] - shifts[i][0]);
                                }
                            } else if (shifts[j][1] > shifts[i][1] && shifts[j][0] < shifts[i][1] && shifts[j][0] > shifts[i][0]){
                                lonewolf[i] = lonewolf[i] - (shifts[i][1] - shifts[j][0]);
                                pair.add(i);
                                pair.add(j);
                                if(!map.containsKey(pair)){
                                    map.put(pair,shifts[i][1] - shifts[j][0]);
                                } else if (map.get(pair) == 0) {
                                    map.put(pair,shifts[i][1] - shifts[j][0]);
                                }
                            } else if (shifts[j][1] > shifts[i][1] && shifts[j][0] < shifts[i][0]){
                                lonewolf[i] = 0;
                                pair.add(i);
                                pair.add(j);
                                if(!map.containsKey(pair)){
                                    map.put(pair,shifts[i][1] - shifts[i][0]);
                                } else if (map.get(pair) == 0) {
                                    map.remove(pair);
                                    map.put(pair,shifts[i][1] - shifts[i][0]);
                                }
                            } else if (shifts[i][1] > shifts[j][1] && shifts[i][0] < shifts[j][0]){
                                lonewolf[i] = lonewolf[i] - (shifts[j][1] - shifts[j][0]);
                                pair.add(i);
                                pair.add(j);
                                if(!map.containsKey(pair)){
                                    map.put(pair,shifts[j][1] - shifts[j][0]);
                                } else if (map.get(pair) == 0) {
                                    map.put(pair,shifts[j][1] - shifts[j][0]);
                                }
                            }
                        }
                    }
                    totalCover = totalCover + lonewolf[i];
                }
                for(int value : map.values()) {
                    overlap = overlap + value ;
                }
                totalCover = totalCover + overlap;
                System.out.println("overlap : " + overlap);
                System.out.println("totalCover : " + totalCover);
                int result = 0;
                for(int lone : lonewolf){
                    result = Math.max(result, totalCover - lone);
                }
                System.out.println("MaxCoverage: " + result);
                System.out.println("-----------");
                writer.println(result);
                reader.close();
                writer.close();
            }catch(FileNotFoundException f){
                f.printStackTrace();
            }catch(IOException io){
                io.printStackTrace();
            }catch(OutOfMemoryError ome){
                continue;
            }
        }
    }
}