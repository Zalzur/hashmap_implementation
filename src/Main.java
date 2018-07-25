public class Main {

    public static void main(String[] args) {
        HashMap testMap = new HashMap();
        testMap.add("asd", 5);
        testMap.add("kecske", 18);
        testMap.add("elemer", 15);
        System.out.println(testMap.getValue("kecske"));
    }
}
