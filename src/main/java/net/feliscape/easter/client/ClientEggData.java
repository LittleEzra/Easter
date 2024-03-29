package net.feliscape.easter.client;

public class ClientEggData {
    private static int easterEggs;
    private static int goldenEggs;

    public static void set(int easterEggs, int goldenEggs){
        ClientEggData.easterEggs = easterEggs;
        ClientEggData.goldenEggs = goldenEggs;
    }

    public static int getEasterEggs(){
        return easterEggs;
    }
    public static int getGoldenEggs(){
        return goldenEggs;
    }
}
