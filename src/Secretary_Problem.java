import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Secretary_Problem {

    public static void main(String[] args) {
        double SCOUTING_RATIO = 0.37;
        List<Integer> applicants = getApplicants();
        int applicantsAutoSkipped = (int) (applicants.size() * SCOUTING_RATIO);
        int maxSoFar = -1;
        int selectedApplicant = -1;
        int bestApplicant = Collections.max(applicants);
        for (int i = 0; i < applicantsAutoSkipped; i++) {
            int currentApplicant = applicants.get(i);
            if (currentApplicant > maxSoFar) {
                maxSoFar = currentApplicant;
            }
            applicants.remove(i);
        }
        for (int i = 0; i < applicants.size(); i++) {
            int currentApplicant = applicants.get(i);
            if (currentApplicant > maxSoFar) {
                selectedApplicant = currentApplicant;
                break;
            } else {
                if (i == applicants.size() - 1) {
                    selectedApplicant = currentApplicant;
                }
            }
        }

        System.out.println("Best applicant: " + bestApplicant + "\nSelected applicant: " + selectedApplicant);
    }

    private static List<Integer> getApplicants() {
        List<Integer> applicants = new LinkedList<>();
        while (applicants.size() < 10) {
            int newApplicant = (int) (Math.random() * 10);
            if (!applicants.contains(newApplicant)) {
                applicants.add(newApplicant);
            }
        }
        return applicants;
    }
}
