package peggame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    public PegGameBoard readFile(String path) throws FileNotFoundException{
        try{
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        int size = Integer.valueOf(scanner.nextLine()) + 1;
        PegGameBoard readedFile = new PegGameBoard(size);

        for (int row = 0; row < size; row++){
            String readedLine = scanner.nextLine();
            for (int col = 0; col < size; col++){
                if (readedLine.charAt(col) == 'o'){
                    readedFile.setValue(row, col, 'o');
                }
                else if (readedLine.charAt(col) == '.'){
                    readedFile.setValue(row, col, '-');
                }
            }
        }
        scanner.close();
        return readedFile;
    }
    catch (FileNotFoundException e){
        return new PegGameBoard(0);
    }
    }
    
}
