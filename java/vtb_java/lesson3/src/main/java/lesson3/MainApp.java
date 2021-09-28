package lesson3;

public class MainApp {
    public static void main(String[] args) {
        String[][] array = {{"1", "2a"}, {"1", "3"}};
        try {
            System.out.println(sum(array));
        } catch (MyArrayDataException | MyArraySizeException e){
            e.printStackTrace();
            System.out.println("ignored");
        }
    }


    private static int sum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if(array.length != 2)
            throw new MyArraySizeException();

        int sum = 0;
        for (int i = 0; i < array.length; ++i){
            if(array[i].length != 2){
                throw new MyArraySizeException();
            }
            for (int j = 0; j < array[i].length; ++j){
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e){
                    throw new MyArrayDataException("Data in (" + i + ", " + j + ") = " + array[i][j]);
                }
            }
        }
        return sum;
    }
}
