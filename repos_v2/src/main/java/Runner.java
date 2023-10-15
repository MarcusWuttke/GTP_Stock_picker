import Forum.PostHandler;

import java.util.Scanner;

public class Runner {

    public Runner(){

    }

    public void runProgram(PostHandler postHandler){
        boolean run = true;

        Scanner scanner = new Scanner(System.in);

        while (run){
            System.out.println("What do you want to do? Options are:" +
                    "\nstop - stops program" +
                    "\nget post - lets you provide a flashback post-id and prints content and date post was made" +
                    "\nsave post - saves a flashback post to database");
            String action = scanner.nextLine();
            System.out.println(action);

            switch(action){
                case "stop":
                    run = false;
                    break;
                case "get post":
                    System.out.println("Provide post-id:");
                    String postId = scanner.nextLine();
                    postHandler.printSinglePost(postId);
                    break;
                case "save post":
                    System.out.println("Provide post-id to be saved:");
                    String postIdToBeSaved = scanner.nextLine();
                    try {
                        postHandler.saveSinglePost(postIdToBeSaved, "ABC");
                    } catch (Exception e){
                        System.out.println(e);
                    }

                    break;
                default:
                    System.out.println("Not viable option");
                    break;
            }

        }

    }
}
