package main.java.controller;

import main.java.model.*;
import main.java.view.View;

import java.io.IOException;
import java.util.*;

public class GameController {
    public static Soldier player = new Soldier();
    public static int timer = 0;
    public static boolean moonTrigger = true;
    private String currentRoom = RoomMovement.currentRoom;
    private HashMap<String, List<Werewolf>> monsterMap = getMonsterMap(currentRoom);
    private static final ResourceBundle bundle = ResourceBundle.getBundle("main.resources.strings");


    public void userChoice() throws IOException {
        boolean werewolfCanAttack = true;




        while (player.getHealth() > 0 && timer < 24) {
            try {
                Random ran = new Random();

                String[] werewolfAttack = {bundle.getString("werewolf_attack1"), bundle.getString("werewolf_attack2"), bundle.getString("werewolf_attack3")};
                String werewolfAttackResponse = werewolfAttack[ran.nextInt(werewolfAttack.length)];
                if (timer>0 && (timer%7==0 || timer%8==0)) {
                    monsterMap.values().forEach(monsters -> {
                        monsters.forEach(monster -> {
                            monster.setAttackPower(15);
                        });
                    });
                }
                else {
                    monsterMap.values().forEach(monsters -> {
                        monsters.forEach(monster -> {
                            monster.setAttackPower(10);
                        });
                    });
                }
                currentRoom = RoomMovement.currentRoom;
                if (!monsterMap.get(currentRoom).isEmpty() && werewolfCanAttack) {
                    Werewolf wolf = monsterMap.get(currentRoom).get(0);
                    wolf.attack(player);
                    sleep(300);
                    System.out.println(werewolfAttackResponse);
                    System.out.println(bundle.getString("health_status1") + player.getHealth()+"!\n");
                    sleep(750);
                    werewolfCanAttack = false;
                }
                /* Will end the loop if players health hits 0 or timer runs out. */
                if (player.getHealth() <= 0 || timer >= 24) {
                    break;
                }
                if (timer>19){
                    System.out.println(bundle.getString("hours_status1") + (72-(timer*3)) + bundle.getString("hours_status2"));
                    sleep(750);
                }

                View.menu();
                Room room = RoomMovement.roomSwitcher;
                Response r1 = InputScanner.getValidResponse();
                for (int i = 0; i < 50; ++i) System.out.println();

                if (r1.getVerb().equalsIgnoreCase("use") && currentRoom.equalsIgnoreCase("Time Portal")){
                    if (r1.getNoun().equalsIgnoreCase("blood sample")){
                        player.pickup("Trophy");
                        break;
                    }
                }
                switch (r1.getVerb()) {
                    case "go":
                        moonTrigger = true;
                        werewolfCanAttack = true;
                        RoomMovement.switchRooms(r1.getLocation());
                        room = RoomMovement.roomSwitcher;
                        System.out.println(bundle.getString("go1")+ room.getName() + ".");
                        sleep(750);
                        System.out.println(room.getDescription() + "\n");
                        sleep(750);
                        timer++;

                        break;
                    case "pickup":
                        if (player.getInventory().size() > 2) {
                            System.out.println(bundle.getString("pickup1"));

                        } else if (room.getItems().contains(r1.getNoun())) {
                            player.pickup(r1.getNoun());
                            room.getItems().remove(r1.getNoun());
                            System.out.println(bundle.getString("pickup2") + r1.getNoun() + bundle.getString("pickup3"));
                        } else {
                            System.out.println(bundle.getString("pickup4"));
                        }
                        sleep(500);
                        werewolfCanAttack = false;
                        break;
                    case "look":
                        System.out.println("\n"+room.getDescription());
                        sleep(500);
                        System.out.println(bundle.getString("look1"));
                        sleep(500);
                        System.out.println(bundle.getString("look2"));
                        sleep(500);
                        if (room.getItems().size() < 1) {
                            System.out.println(bundle.getString("look3"));
                        } else {
                            for (String key : room.getItems()) {
                                sleep(1000);
                                System.out.println(bundle.getString("look4") + key + "!");
                            }
                        }
                        sleep(1000);
                        werewolfCanAttack = false;
                        System.out.println("\n");
                        break;
                    case "use":
                        if (player.getInventory().contains(r1.getNoun())) {
                            player.useItems(r1.getNoun());
                            sleep(1000);
                        } else {
                            System.out.println(bundle.getString("use1"));
                            sleep(1000);
                        }
                        werewolfCanAttack = false;
                        break;
                    case "attack":
                        if (monsterMap.get(currentRoom).isEmpty()) {
                            System.out.println(bundle.getString("attack1"));
                            break;
                        }
                        Werewolf w1 = monsterMap.get(currentRoom).get(0);
                        player.attack(w1);
                        if (w1.getHealth() <= 0) {
                            monsterMap.get(currentRoom).remove(0);

                            if (w1.getInventory().size() >0){
                            for (String item : w1.getInventory()){
                                String[] werewolfKingDead = {bundle.getString("werewolfKing_dead1"), bundle.getString("werewolfKing_dead2"), bundle.getString("werewolfKing_dead3")};
                                String werewolfKing_deadResponse = werewolfKingDead[ran.nextInt(werewolfKingDead.length)];
                                System.out.println(werewolfKing_deadResponse);
                                room.getItems().add(item); }
                            }
                            else {
                                String[] werewolfDead = {bundle.getString("werewolf_dead1"), bundle.getString("werewolf_dead2"), bundle.getString("werewolf_dead3")};
                                String werewolf_deadResponse = werewolfDead[ran.nextInt(werewolfDead.length)];
                                System.out.println(werewolf_deadResponse);
                            }
                            sleep(1000);
                        }
                        werewolfCanAttack = true;
                        break;

                    case "inventory":
                        if (player.getInventory().size() < 1) {
                            System.out.println(bundle.getString("inventory_0"));
                        } else {
                            System.out.println(bundle.getString("inventory_items"));
                            for (String key : player.getInventory()) {
                                sleep(300);
                                System.out.println(key);
                            }
                        }
                        sleep(500);
                        werewolfCanAttack = false;
                        break;
                    case "help":
                        werewolfCanAttack = false;
                        System.out.println(bundle.getString("help_menu1"));
                        System.out.println(bundle.getString("help_menu2"));
                        System.out.println(bundle.getString("help_menu3"));
                        Scanner scanner = new Scanner(System.in);
                        if (scanner.hasNextLine()) {
                            for (int i = 0; i < 50; ++i) System.out.println();
                            break;
                        }
                    case "quit":
                        System.out.println(bundle.getString("quit_menu1"));
                        System.exit(0);
                        break;

                    default:
                        System.out.println(bundle.getString("invalid_input1"));
                        werewolfCanAttack = false;
                        break;
                }

            } catch (NullPointerException e) {
                System.out.println(bundle.getString("invalid_input2"));
                break;
            }


        }
    }

    public static HashMap<String, List<Werewolf>> getMonsterMap(String room) {
        Random random = new Random();
        HashMap<String, Room> allMap = RoomMovement.getAllRooms();
        HashMap<String, List<Werewolf>> monsterMap = new HashMap<>();
        for (String key : allMap.keySet()) {
            monsterMap.put(key, new LinkedList<Werewolf>());
            if (key.equals("Throne Room")) {
                monsterMap.get(key).add(new WerewolfKing());
            }
            if (random.nextBoolean() && !key.equals(room)) {
                monsterMap.get(key).add(new Werewolf());
            }
        }
        return monsterMap;
    }

    public void sleep(int timer) {
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
