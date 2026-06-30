class UniversityMember {
    public void attendLecture() {
        System.out.println("Attending a lecture.");
    }
}

interface Researcher {
    void conductLabResearch();
}

class UndergraduateStudent extends UniversityMember {

}

class GraduateResearchAssistant extends UniversityMember implements Researcher {
    public void conductLabResearch() {
        System.out.println("Publishing papers and running experiments.");
    }
}

public class Main5 {
    public static void main(String[] args) {
        UniversityMember undergrad = new UndergraduateStudent();
        GraduateResearchAssistant grad = new GraduateResearchAssistant();

        System.out.println("Undergraduate");
        undergrad.attendLecture();

        System.out.println("Graduate Research Assistant");
        grad.attendLecture();
        grad.conductLabResearch();
    }
}
