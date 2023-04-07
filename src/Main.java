import java.util.*;
//Xianyang Zhan 04/04/2023
public class Main {
    public static void main(String[] args) {
        ArrayList<Stack<Integer>> list = new ArrayList<>();
        Initiate(list); //Put 27 Stacks into the list, each Stack has 5 elements from 1 to 9, each integer has 15 duplicates
        ArrayList<ArrayList<Stack<Integer>>> board = new ArrayList<>(3); //2d arraylist, storing stacks
        InitiateBoard(board,list); //This is a 2d board, each stack in it has a location based on row-clo (9-3)
        //System.out.println(board);
        ArrayList<Integer> table = new ArrayList<>(); //Initiate execute table
        boolean gameEnd = false;
        Scanner scan = new Scanner(System.in);
        while(!gameEnd){
            System.out.println("=================================");
            int endCounter = 0;
            for(int i=0;i<9;i++){
                for(int j=0; j<3; j++){
                    int num = -1;
                    if(!board.get(j).get(i).isEmpty()){
                        num = board.get(j).get(i).peek();
                    }else{
                        endCounter++;
                    }
                    System.out.printf("%-16s",num);
                }
                System.out.println();
            }
            System.out.println("=================================");
            if(endCounter == 27){
                System.out.println("You win the game!!!!!!!!!!!!");
                break;
            }
            System.out.println("The execute table: "+table.toString());
            System.out.println("Please choose row (input 1~9): ");
            int row = scan.nextInt();
            if(row < 1 || row >9){
                System.out.println("Invalid row number! please try again");
                continue;
            }
            System.out.println("Please choose column (input 1~3): ");
            int col = scan.nextInt();
            if(col < 1 || col >3){
                System.out.println("Invalid col number! Please try again");
                continue;
            }
            if(board.get(col-1).get(row-1).isEmpty()){
                System.out.println("The position is empty, please try another position!");
                continue;
            }
            table.add(putTable(board,row,col));
            gameEnd = checkTable(table);
            if(gameEnd){
                System.out.println("The execute table is: "+table);
                System.out.println("You didn't make it to cancel out the elements in the table. Since the table is at capacity, you lose the game!");
            }
        }
        //System.out.println(list);
    }

    private static void Initiate(ArrayList<Stack<Integer>> list){
        ArrayList<Integer> numList = new ArrayList<>();
        for(int m=1; m<=9; m++){
            for(int n=0; n<15 ; n++){
                numList.add(m);
            }
        }
        //////////////////
        for(int i=0; i<27; i++){
            Stack<Integer> tempStack = new Stack<>();
            for(int j=0; j<5; j++){
                Random rand = new Random();
                int index = rand.nextInt(numList.size());
                tempStack.push(numList.get(index));
                numList.remove(index);
            }
            list.add(tempStack);
        }
    }

    private static void InitiateBoard(ArrayList<ArrayList<Stack<Integer>>> board, ArrayList<Stack<Integer>> list) {
        int index = 0;
        for(int i=0; i<3; i++){
            board.add(new ArrayList<Stack<Integer>>());
            for(int j=0; j<9; j++){
                board.get(i).add(list.get(index));
                index++;
            }
        }
    }

    private static int putTable(ArrayList<ArrayList<Stack<Integer>>> board, int row, int col) {
        return board.get(col-1).get(row-1).pop();
    }

    private static boolean checkTable(ArrayList<Integer> table) {
        for(int i=0; i<table.size(); i++){
            int num = table.get(i);
            int count = 0;
            for(int j=0; j<table.size(); j++){
                if(table.get(j) == num){
                    count ++;
                }
                if(count == 3) {
                    table.remove(table.indexOf(num));
                    table.remove(table.indexOf(num));
                    table.remove(table.indexOf(num));
                }
            }
        }
        if(table.size()>=7){
            return true;
        } else{
            return false;
        }
    }

}