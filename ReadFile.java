package peggame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    public PegGameSquare readFile(String path) throws FileNotFoundException{
        try{
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        int size = Integer.valueOf(scanner.nextLine());
        PegGameSquare readedFile = new PegGameSquare(size);

        for (int row = 0; row <= size; row++){
            String readedLine = scanner.nextLine();
            for (int col = 0; col <= size; col++){
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
        throw new FileNotFoundException();
    }
    }
    
}
