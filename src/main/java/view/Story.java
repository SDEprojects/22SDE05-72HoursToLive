package main.java.view;


import java.util.ResourceBundle;
import java.util.Scanner;

public class Story {
    Scanner scanner = new Scanner(System.in);
    public static int difficulty;

    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");

    public void titleScreen() {

        String menu = "===========================================================================================================================================\n";
        String infoMenu = "======================================================================\n";
        String titleBanner =

                "\n███████╗███████╗██╗   ██╗███████╗███╗   ██╗████████╗██╗   ██╗    ████████╗██╗    ██╗ ██████╗     ██╗  ██╗ ██████╗ ██╗   ██╗██████╗ ███████╗\n" +
                        "██╔════╝██╔════╝██║   ██║██╔════╝████╗  ██║╚══██╔══╝╚██╗ ██╔╝    ╚══██╔══╝██║    ██║██╔═══██╗    ██║  ██║██╔═══██╗██║   ██║██╔══██╗██╔════╝\n" +
                        "███████╗█████╗  ██║   ██║█████╗  ██╔██╗ ██║   ██║    ╚████╔╝        ██║   ██║ █╗ ██║██║   ██║    ███████║██║   ██║██║   ██║██████╔╝███████╗\n" +
                        "╚════██║██╔══╝  ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║   ██║     ╚██╔╝         ██║   ██║███╗██║██║   ██║    ██╔══██║██║   ██║██║   ██║██╔══██╗╚════██║\n" +
                        "███████║███████╗ ╚████╔╝ ███████╗██║ ╚████║   ██║      ██║          ██║   ╚███╔███╔╝╚██████╔╝    ██║  ██║╚██████╔╝╚██████╔╝██║  ██║███████║\n" +
                        "╚══════╝╚══════╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝   ╚═╝      ╚═╝          ╚═╝    ╚══╝╚══╝  ╚═════╝     ╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝\n" +
                        "                                                                                                                                           \n";

        String infoBanner =
                " ██████╗  █████╗ ███╗   ███╗███████╗    ██╗███╗   ██╗███████╗ ██████╗ \n" +
                        "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██║████╗  ██║██╔════╝██╔═══██╗\n" +
                        "██║  ███╗███████║██╔████╔██║█████╗      ██║██╔██╗ ██║█████╗  ██║   ██║\n" +
                        "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║██║╚██╗██║██╔══╝  ██║   ██║\n" +
                        "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ██║██║ ╚████║██║     ╚██████╔╝\n" +
                        " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝    ╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝ \n" +
                        "                                                                      \n";


        System.out.println(menu + titleBanner + menu);
        sleep(1); //change to 3000
        System.out.println(bundle.getString("title_screen"));
        while (true) {
            String intro = scanner.next();
            if (intro.equalsIgnoreCase("play")) {
                System.out.println(bundle.getString("start_game"));
                sleep(1000);
                break;
            } else if (intro.equalsIgnoreCase("help")) {
                System.out.println(infoMenu + "\n" + infoBanner + infoMenu);
                sleep(1800);
                System.out.println(bundle.getString("help_intro"));
                System.out.println(bundle.getString("help_menu1"));
                System.out.println(bundle.getString("help_menu2"));
                sleep(1500);
                System.out.println(bundle.getString("help_menu3"));
                String readString = scanner.nextLine();
                if (scanner.hasNextLine()) {
                    titleScreen();
                    break;
                }
            } else if (intro.equalsIgnoreCase("quit")) {
                System.out.println("quit_menu1");
                sleep(1000);
                System.exit(0);
            } else {
                System.out.println(bundle.getString("invalid_input3"));
            }

        }
    }
    public void selectDifficulty(){
        while(true){
            System.out.println(bundle.getString("select_level"));
            String choice = scanner.next();
            if(choice.equalsIgnoreCase("easy")){
                System.out.println(bundle.getString("level_easy"));
                difficulty = 1;
                sleep(1000);
                break;
            } else if(choice.equalsIgnoreCase("medium")){
                System.out.println(bundle.getString("level_medium"));
                sleep(1000);
                difficulty = 5;
                break;
            } else if(choice.equalsIgnoreCase("hard")){
                System.out.println(bundle.getString("level_hard"));
                sleep(1000);
                difficulty = 10;
                break;
            } else if(choice.equalsIgnoreCase("impossible")){
                System.out.println(bundle.getString("level_impossible"));
                sleep(1000);
                difficulty = 15;
                break;
            } else {
                System.out.println(bundle.getString("invalid_input4"));
            }
        }

    }


    public void introText() {

        String storyBanner = "███████╗████████╗ ██████╗ ██████╗ ██╗   ██╗██╗     ██╗███╗   ██╗███████╗\n" +
                "██╔════╝╚══██╔══╝██╔═══██╗██╔══██╗╚██╗ ██╔╝██║     ██║████╗  ██║██╔════╝\n" +
                "███████╗   ██║   ██║   ██║██████╔╝ ╚████╔╝ ██║     ██║██╔██╗ ██║█████╗  \n" +
                "╚════██║   ██║   ██║   ██║██╔══██╗  ╚██╔╝  ██║     ██║██║╚██╗██║██╔══╝  \n" +
                "███████║   ██║   ╚██████╔╝██║  ██║   ██║   ███████╗██║██║ ╚████║███████╗\n" +
                "╚══════╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝╚═╝  ╚═══╝╚══════╝\n" +
                "                                                                        \n";


        while (true) {

            String menu = "========================================================================\n";
            System.out.println("\n"+menu + "\n" + storyBanner + menu);
            System.out.println(bundle.getString("intro_text1"));
            String intro = scanner.next();
            if (intro.equalsIgnoreCase("skip")) {

                System.out.println(bundle.getString("skip_intro"));
                sleep(1425);
                System.out.println(bundle.getString("start_game"));
                sleep(1425);
                for (int i = 0; i < 50; ++i) System.out.println();
                break;
            } else if (intro.equalsIgnoreCase("read")) {

                textStream(bundle.getString("story_one"), 120);
                sleep(650);
                textStream(bundle.getString("story_two"), 120);
                textStream(bundle.getString("story_slowTwo"), 210);
                sleep(650);
                textStream(bundle.getString("story_three"), 120);
                sleep(650);
                textStream(bundle.getString("story_four"), 120);
                sleep(650);
                textStream(bundle.getString("story_five"), 120);
                textStream("forever.\n\n", 390);
                System.out.println(bundle.getString("start_game"));
                sleep(1000);
                for (int i = 0; i < 50; ++i) System.out.println();
                break;
            } else {
                System.out.println(bundle.getString("invalid_input2"));
            }
        }
    }


    private String textStream(String text, int speed) {
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            sleep(speed);
        }
        return text;
    }

    private void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

