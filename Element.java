public class Element {
    String name;
    char section;
    char used;
    int score;

    public Element(String name, char section, char used, int score) {
        this.name = name;
        this.section = section;
        this.used = used;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public char getUsed() {
        return used;
    }

    public void setUsed(char used) {
        this.used = used;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public char getSection() {
        return section;
    }

    public void setSection(char section) {
        this.section = section;
    }
}